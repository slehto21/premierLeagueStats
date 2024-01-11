package hh.sof03.footballStats.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Year;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;
import hh.sof03.footballStats.domain.TeamStats;
import hh.sof03.footballStats.domain.TeamStatsRepository;

@Component
public class TeamApiCall {
	
	Logger logger = LoggerFactory.getLogger(TeamApiCall.class);
	
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private TeamStatsRepository teamStatsRepository;
	
	public void main() {
		doApiCall();
	}
	
	@Value("${my.api.key}")
	private String apiKey;
	
	private void doApiCall() {
		//Api call that returns Premier League standing
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://v3.football.api-sports.io/standings?league=39&season=2023"))
				.header("x-rapidapi-key", apiKey)
				.header("x-rapidapi-host", "v3.football.api-sports.io")
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient()
					.send(request, HttpResponse.BodyHandlers.ofString());

			String jsonResponse = response.body();
			logger.info(response.body());
			// Parse and display standings data
			parseStandingsData(jsonResponse);
		} catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }	
	}

	private void parseStandingsData(String jsonResponse) {
		
	    JSONObject jsonObject = new JSONObject(jsonResponse);

	    if (jsonObject.has("response")) {
	        JSONArray standingsArray = jsonObject.getJSONArray("response");

	        if (standingsArray.length() > 0) {
	            JSONArray teamsArray = standingsArray.getJSONObject(0)
	                    .getJSONObject("league")
	                    .getJSONArray("standings")
	                    .getJSONArray(0);

	            // Iterating over every team 
	            for (int i = 0; i < teamsArray.length(); i++) {
	                JSONObject teamData = teamsArray.getJSONObject(i);

	                String teamName = teamData.getJSONObject("team").getString("name");
	                String city = ""; 
	                String logoUrl = "";
	                String stadium = "";
	                int foundingYear = 0; 
	                int capacity = 0;
	                
	                int teamId = teamData.getJSONObject("team").getInt("id");

	                if (teamId != 0) {    
	                    // API call that gives more information about team
	                	JSONObject teamInfoObject = getTeamInfo(teamId);

	                    if (teamInfoObject != null && teamInfoObject.has("response")) {
	                        JSONArray teamInfoArray = teamInfoObject.getJSONArray("response");

	                        if (teamInfoArray.length() > 0) {
	                            JSONObject teamInfo = teamInfoArray.getJSONObject(0);

	                            city = teamInfo.getJSONObject("venue").getString("city");
	                            stadium = teamInfo.getJSONObject("venue").getString("name");
	                            logoUrl = teamInfo.getJSONObject("team").getString("logo");
	                            foundingYear = teamInfo.getJSONObject("team").getInt("founded");
	                            capacity = teamInfo.getJSONObject("venue").getInt("capacity");
	                        }
	                    }
	                }
	                
	                int points = teamData.getInt("points");
	                int wins = teamData.getJSONObject("all").getInt("win");
	                int draws = teamData.getJSONObject("all").getInt("draw");
	                int losses = teamData.getJSONObject("all").getInt("lose");
	                int rank = teamData.getInt("rank");
	                int goalsFor = teamData.getJSONObject("all").getJSONObject("goals").getInt("for");
	                int goalsAgainst = teamData.getJSONObject("all").getJSONObject("goals").getInt("against");
	                int goalDiff = teamData.getInt("goalsDiff");
	                String form = teamData.getString("form");
	                							
	                Year teamYear = Year.of(foundingYear);
	                
	                //Save data to database
	                Team team = new Team(teamName, city, stadium,capacity, logoUrl, teamYear);
	                logger.info("Team: " + team);
	                teamRepository.save(team);
	                TeamStats teamStats = new TeamStats(wins, draws, losses, points, rank, 
	                		goalsFor, goalsAgainst, goalDiff, form,  team);
	                logger.info("Stats: " + teamStats);
	                teamStatsRepository.save(teamStats);
	            }
	        }
	    }
	}
	
	private JSONObject getTeamInfo(int teamId) {
	    try {
	        // Delay for 8 seconds to make sure that we don't hit rate limit
	        Thread.sleep(8000);

	        // API call that gives more information about the team
	        String teamInfoAPI = "https://v3.football.api-sports.io/teams?id=" + teamId;

	        HttpRequest teamInfoRequest = HttpRequest.newBuilder()
	                .uri(URI.create(teamInfoAPI))
	                .header("x-rapidapi-key", apiKey)
	                .header("x-rapidapi-host", "v3.football.api-sports.io")
	                .method("GET", HttpRequest.BodyPublishers.noBody())
	                .build();

	        HttpResponse<String> teamInfoResponse = HttpClient.newHttpClient()
	                .send(teamInfoRequest, HttpResponse.BodyHandlers.ofString());

	        return new JSONObject(teamInfoResponse.body());
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
