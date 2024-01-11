package hh.sof03.footballStats.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamPlayersDTO;
import hh.sof03.footballStats.domain.TeamRepository;
import hh.sof03.footballStats.domain.TeamStats;
import hh.sof03.footballStats.domain.TeamStatsRepository;

@CrossOrigin
@Controller
public class TeamRestController {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private TeamStatsRepository tStatsRepository;


	// Json list that contains all teams
	@GetMapping(value="/teams")
	public @ResponseBody List<Team> teamListRest() {
		return (List<Team>) teamRepository.findAll();
	}

	// Team's information, stats and players 
	@GetMapping(value="/teams/{id}")
	public @ResponseBody TeamPlayersDTO teamById(@PathVariable("id") Long teamId) {
		Team team = teamRepository.findById(teamId).orElse(null);
		
		if(team != null) {
			TeamPlayersDTO teamPlayersDTO = new TeamPlayersDTO(
					team.getId(),
					team.getName(),
					team.getCity(),
					team.getStadium(),
					team.getCapacity(),
					team.getLogoUrl(),
					team.getYearFounded(),
					team.getPlayers(),
					team.getTeamStats()
					);
			return teamPlayersDTO;
		}
		else {
			return null;
		}
	}

	// Saving new team
	@PostMapping(value="/teams")
	public @ResponseBody Team savePlayerRest(@RequestBody Team team, @RequestBody TeamStats teamStats) {
		teamRepository.save(team);
		tStatsRepository.save(teamStats);
		team.setTeamStats(teamStats);
		return teamRepository.save(team);
	}

}
