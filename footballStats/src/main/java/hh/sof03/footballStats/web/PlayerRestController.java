package hh.sof03.footballStats.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
import hh.sof03.footballStats.domain.PlayerStats;
import hh.sof03.footballStats.domain.PlayerStatsRepository;
import hh.sof03.footballStats.domain.PlayersInTeamDTO;
import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;

@CrossOrigin
@Controller
public class PlayerRestController {
	
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private PlayerStatsRepository pStatsRepository;
	@Autowired
	private TeamRepository teamRepository;
	
	//Json list that contains all players
	@GetMapping(value="/players")
	public @ResponseBody List<Player> playerListRest() {
		return (List<Player>)playerRepository.findAll();
	}
	
	//Player by Id
	@GetMapping(value="/players/{id}")
	public @ResponseBody Optional<Player> playerByIdRest(@PathVariable("id")Long playerId){
		return playerRepository.findById(playerId);
	}
	
	//All players with stats from one team 
	@GetMapping(value="players/team/{id}")
	public @ResponseBody PlayersInTeamDTO playersInTeam(@PathVariable("id")Long teamId) {
		Team team = teamRepository.findById(teamId).orElse(null);
		if(team != null) {
		List<Player> players = playerRepository.findByTeam(team);
		PlayersInTeamDTO playersInTeamDTO = new PlayersInTeamDTO(
				team.getName(),
				team.getId(),
				players);
		return playersInTeamDTO;
		}
		else {
			return null;
		}
	}
	
	//Saving new player
	@PostMapping(value="/players")
	public @ResponseBody Player savePlayerRest(@RequestBody Player player, @RequestBody PlayerStats playerStats) {
		playerRepository.save(player);
		pStatsRepository.save(playerStats);
		player.setPlayerStats(playerStats);
			
		return playerRepository.save(player);
	}
}
