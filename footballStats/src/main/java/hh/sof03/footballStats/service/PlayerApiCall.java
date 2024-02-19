package hh.sof03.footballStats.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
import hh.sof03.footballStats.domain.PlayerStats;
import hh.sof03.footballStats.domain.PlayerStatsRepository;
import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;

@Component
public class PlayerApiCall {
	
	Logger logger = LoggerFactory.getLogger(PlayerApiCall.class);

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private PlayerStatsRepository playerStatsRepository;
	@Autowired
	private TeamRepository teamRepository;
	
	@Value("${my.api.key}")
	private String apiKey;

	public void main() {
		doApiCall();
	}

	private void doApiCall() {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://v3.football.api-sports.io/players?league=39&season=2023"))
				.header("x-rapidapi-key", apiKey)
				.header("x-rapidapi-host", "v3.football.api-sports.io")
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

			String jsonResponse = response.body();
			logger.info("jsonResponse: " + jsonResponse);
			savePlayers(jsonResponse);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void savePlayers(String jsonResponse) {
		JSONObject jsonObject = new JSONObject(jsonResponse);
		int currPage = jsonObject.getJSONObject("paging").getInt("current");
		int totalPages = jsonObject.getJSONObject("paging").getInt("total");

		// Iterating through all pages
		while (currPage <= totalPages) {

			JSONArray playersArray = jsonObject.getJSONArray("response");
			logger.info("playersArray: " + playersArray);

			int resultsPerPage = jsonObject.getInt("results");
			// Iterating through all players in the page
			for (int i = 0; i < resultsPerPage; i++) {
				JSONObject playerIformation = playersArray.getJSONObject(i).getJSONObject("player");
				System.out.println("playerInformation: " + playerIformation);
				String name = null;
				if(!playerIformation.isNull("name")) {
					name = playerIformation.getString("name");
				}
				String firstName = null; 
				if(!playerIformation.isNull("firstname")) {
					firstName = playerIformation.getString("firstname");
				}
				String lastName = null;
				if (!playerIformation.isNull("lastname")) {
					lastName = playerIformation.getString("lastname");
				}
				int age = 0;
				if(!playerIformation.isNull("age")) {
					age = playerIformation.getInt("age");
				}
				String bday = null;
				if(!playerIformation.getJSONObject("birth").isNull("date")) {
					bday = playerIformation.getJSONObject("birth").getString("date");
				}
				String nationality = null; 
				if (!playerIformation.isNull("nationality")) {
					nationality = playerIformation.getString("nationality");
				}
				String height = null;
				if (!playerIformation.isNull("height")) {
					height = playerIformation.getString("height");
				}
				String weight = null;
				if (!playerIformation.isNull("weight")) {
					weight = playerIformation.getString("weight");
				}
				String playerPhoto = null;
				if (!playerIformation.isNull("photo")) {
					playerPhoto = playerIformation.getString("photo");
				}

				JSONArray playerStatsArray = playersArray.getJSONObject(i).getJSONArray("statistics");
				logger.info("playerStatsInfo: " + playerStatsArray);

				String teamName = "Not available";
				int matches = 0;
				int minutes = 0;
				String position = "Not available";
				String rating = "0";
				int shots = 0;
				int shotsOnTarget = 0;
				int goals = 0;
				int assists = 0;
				int saves = 0;
				int yellows = 0;
				int reds = 0;
				boolean hasPlayed = false;
				for (int j = 0; j < playerStatsArray.length(); j++) {
					JSONObject playerStatsInfo = playerStatsArray.getJSONObject(j);
					// Only get info for players with matches
					// Check if key is null
					if (!playerStatsInfo.getJSONObject("games").isNull("appearences")
							&& playerStatsInfo.getJSONObject("games").getInt("appearences") > 0) {
						if (!playerStatsInfo.getJSONObject("team").isNull("name")) {
							teamName = playerStatsInfo.getJSONObject("team").getString("name");
						}
						matches += playerStatsInfo.getJSONObject("games").getInt("appearences");
						if (!playerStatsInfo.getJSONObject("games").isNull("minutes")) {
							minutes += playerStatsInfo.getJSONObject("games").getInt("minutes");
						}
						if (!playerStatsInfo.getJSONObject("games").isNull("position")) {
							position = playerStatsInfo.getJSONObject("games").getString("position");
						}
						if (!playerStatsInfo.getJSONObject("games").isNull("rating")) {
							if (rating.equalsIgnoreCase("0")) {
								rating = playerStatsInfo.getJSONObject("games").getString("rating");
							} else {
								rating = rating + "+" + playerStatsInfo.getJSONObject("games").getString("rating");
							}
						}
						if (!playerStatsInfo.getJSONObject("shots").isNull("total")) {
							shots += playerStatsInfo.getJSONObject("shots").getInt("total");
						}
						if (!playerStatsInfo.getJSONObject("shots").isNull("on")) {
							shotsOnTarget += playerStatsInfo.getJSONObject("shots").getInt("on");
						}
						if (!playerStatsInfo.getJSONObject("goals").isNull("total")) {
							goals += playerStatsInfo.getJSONObject("goals").getInt("total");
						}
						if (!playerStatsInfo.getJSONObject("goals").isNull("assists")) {
							assists += playerStatsInfo.getJSONObject("goals").getInt("assists");
						}
						if (!playerStatsInfo.getJSONObject("goals").isNull("saves")) {
							saves += playerStatsInfo.getJSONObject("goals").getInt("saves");
						}
						if (!playerStatsInfo.getJSONObject("cards").isNull("yellow")) {
							yellows += playerStatsInfo.getJSONObject("cards").getInt("yellow");
						}
						if (!playerStatsInfo.getJSONObject("cards").isNull("red")) {
							reds += playerStatsInfo.getJSONObject("cards").getInt("red");
						}
						hasPlayed = true;
					} else {
						continue;
					}
				}
				// Save only players with matches and check that player has name
				if (hasPlayed && name != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate birthday = null; 
					if (bday != null) {
						birthday = LocalDate.parse(bday, formatter);
					}

					List<Team> teams = teamRepository.findByName(teamName);
					Team team = teams.isEmpty() ? null : teams.get(0);

					if (team == null) {
						// Handle the case when the team is not found
						logger.info("Team not found for name: " + teamName);
						continue;
					}

					Player player = new Player(name, firstName, lastName, age, position, nationality, height, weight,
							playerPhoto, birthday, team);
					logger.info("Player" + player);
					playerRepository.save(player);
					double ratingDouble = 0;
					if (rating.contains("+")) {
					    String[] ratings = rating.split("\\+");
					    for (String part : ratings) {
					        ratingDouble += Double.parseDouble(part);
					    }
					    ratingDouble = ratingDouble / ratings.length;
					} else {
					    ratingDouble = Double.parseDouble(rating); 
					}
					DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
					symbols.setDecimalSeparator('.');
					DecimalFormat df = new DecimalFormat("#.##", symbols);
					String roundedRating = df.format(ratingDouble);
					ratingDouble = Double.parseDouble(roundedRating);
					PlayerStats playerStats = new PlayerStats(goals, assists, matches, minutes, ratingDouble, shots,
							shotsOnTarget, saves, yellows, reds, player);
					logger.info("Stats: " + playerStats);
					playerStatsRepository.save(playerStats);
					
				} else {
					logger.info("hasPlayed: " + hasPlayed);
				}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			// Get new page
			logger.info("currPage: " + currPage);
			currPage++;
			if (currPage <= totalPages) {
				jsonObject = newPage(currPage);
			}
		}
	}
	
	private JSONObject newPage(int page) {
	    String apiUrl = "https://v3.football.api-sports.io/players?league=39&season=2023&page=" + page;
	    logger.info("apiUrl: " + apiUrl);

	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(apiUrl))
	            .header("x-rapidapi-key", apiKey)
	            .header("x-rapidapi-host", "v3.football.api-sports.io")
	            .method("GET", HttpRequest.BodyPublishers.noBody())
	            .build();

	    try {
	        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	        String jsonResponse = response.body();
	        logger.info("jsonResponse: " + jsonResponse);
	        return new JSONObject(jsonResponse);
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	        return new JSONObject(); 
	    }
	}
	
}
