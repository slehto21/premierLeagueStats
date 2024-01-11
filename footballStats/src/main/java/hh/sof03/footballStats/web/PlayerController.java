package hh.sof03.footballStats.web;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
import hh.sof03.footballStats.domain.PlayerStats;
import hh.sof03.footballStats.domain.PlayerStatsRepository;
import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;
import jakarta.validation.Valid;

@Controller
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private PlayerStatsRepository pStatsRepository;
	@Autowired
	private TeamRepository teamRepository;

	//Sort players by certain attribute
	//Default sort by goals
	@GetMapping("/playerlist")
	public String playerList(@RequestParam(required = false, defaultValue = "desc") String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/name")
	public String playerListByName(@RequestParam(required = false) String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByNameAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByNameDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/team")
	public String playerListByTeam(@RequestParam(required = false) String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByTeamNameAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByTeamNameDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/position")
	public String playerListByPosition(@RequestParam(required = false) String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByPositionAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByPositionDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/assists")
	public String playerListByAssists(@RequestParam(required = false) String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByPlayerStatsAssistsAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByPlayerStatsAssistsDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/yellows")
	public String playerListByYellows(@RequestParam(required = false) String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByPlayerStatsYellowsAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByPlayerStatsYellowsDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/reds")
	public String playerListByReds(@RequestParam(required = false) String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByPlayerStatsRedsAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByPlayerStatsRedsDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/matches")
	public String playerListByMatches(@RequestParam(required = false) String order, Model model) {
		List<Player> players;
		
		if("asc".equalsIgnoreCase(order)) {
			players = playerRepository.findAllByOrderByPlayerStatsMatchesAsc();
		}
		else if ("desc".equalsIgnoreCase(order)){
			players = playerRepository.findAllByOrderByPlayerStatsMatchesDesc();
		}
		else {
			players = playerRepository.findAllByOrderByPlayerStatsGoalsDesc();
		}
		model.addAttribute("players", players);
		model.addAttribute("order", order);
		return "playerlist";
	}

	@GetMapping("/deleteplayer/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletePlayer(@PathVariable("id") Long playerId) {
		//Removing player by ID
		Player player = playerRepository.findById(playerId).orElse(null);
		if (player != null) {
			PlayerStats playerStats = player.getPlayerStats();
			if (playerStats != null) {
				pStatsRepository.delete(playerStats);
			}
			playerRepository.delete(player);
		}
		return "redirect:/playerlist";
	}

	@GetMapping("/addplayer")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addPlayer(Model model) { 
		List<Team> teams = teamRepository.findAllByOrderByNameAsc();
		model.addAttribute("teams", teams);
		model.addAttribute("player", new Player());
		return "addplayer";
	}

	@PostMapping("/saveplayer")
	public String savePlayer(@Valid Player player, BindingResult bindingResult, Model model) {
		 LocalDate currDate = LocalDate.now();
		if (player.getId() != null) {
			//Updating existing player

			if (bindingResult.hasErrors()) { // If validation error 
				Player existingPlayer = playerRepository.findById(player.getId()).orElse(null);
				model.addAttribute("teams", teamRepository.findAll());
				model.addAttribute("header", existingPlayer);
				return "editplayer";
			} else {
				// No errors, update player's information
				Player existingPlayer = playerRepository.findById(player.getId()).orElse(null);
				if (existingPlayer != null) {
					existingPlayer.setName(player.getName());
					existingPlayer.setFirstName(player.getFirstName());
					existingPlayer.setLastName(player.getLastName());
					existingPlayer.setAge(Period.between(player.getbDay(), currDate).getYears());
					existingPlayer.setNationality(player.getNationality());
					existingPlayer.setHeight(player.getHeight() + " cm");
					existingPlayer.setWeight(player.getWeight() + " kg");
					existingPlayer.setbDay(player.getbDay());
					existingPlayer.setPosition(player.getPosition());
					existingPlayer.setPlayerPhoto(player.getPlayerPhoto());
					existingPlayer.setTeam(player.getTeam());
				
					PlayerStats existingStats = existingPlayer.getPlayerStats();
					existingStats.setAssists(player.getPlayerStats().getAssists());
					existingStats.setGoals(player.getPlayerStats().getGoals());
					existingStats.setMatches(player.getPlayerStats().getMatches());
					existingStats.setMinutes(player.getPlayerStats().getMinutes());
					existingStats.setRating(player.getPlayerStats().getRating());
					existingStats.setShots(player.getPlayerStats().getShots());
					existingStats.setShotsOnTarget(player.getPlayerStats().getShotsOnTarget());
					existingStats.setSaves(player.getPlayerStats().getSaves());
					existingStats.setYellows(player.getPlayerStats().getYellows());
					existingStats.setReds(player.getPlayerStats().getReds());
					
					pStatsRepository.save(existingStats);
					playerRepository.save(existingPlayer);

				}
			}
		} else {
			// Saving new player
			if (bindingResult.hasErrors()) { // If validation error
				model.addAttribute("teams", teamRepository.findAll());
				return "addplayer";
			} else {
				//Save player to database
				PlayerStats playerStats = player.getPlayerStats();
		        pStatsRepository.save(playerStats);
		        
		        String height = player.getHeight();
		        height = height + " cm";
		        player.setHeight(height);
		        
		        String weight = player.getWeight();
		        weight = weight + " kg";
		        player.setWeight(weight);
		        
		        int age = Period.between(player.getbDay(), currDate).getYears();
		        player.setAge(age);
		        
		        player.setPlayerStats(playerStats);
		        playerRepository.save(player);
		        
		        playerStats.setPlayer(player);
		        pStatsRepository.save(playerStats);
			}
		}
		return "redirect:/playerlist";
	}

	@GetMapping("/editplayer/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editPlayer(@PathVariable("id") Long playerId, Model model) {
		//Edit player by ID
		Player existingPlayer = playerRepository.findById(playerId).orElse(null);
		model.addAttribute("player", existingPlayer);
		model.addAttribute("teams", teamRepository.findAll());
		return "editplayer";
	}

	@GetMapping("/showplayer/{id}")
	public String showPlayer(@PathVariable("id") Long playerId, Model model) {
		//Show single player's page
		Player player = playerRepository.findById(playerId).orElse(null);
		model.addAttribute("player", player);
		return "player";
	}

}
