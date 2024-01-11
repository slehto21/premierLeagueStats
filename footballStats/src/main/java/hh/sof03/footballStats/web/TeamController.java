package hh.sof03.footballStats.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
import hh.sof03.footballStats.domain.PlayerStatsRepository;
import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;
import hh.sof03.footballStats.domain.TeamStats;
import hh.sof03.footballStats.domain.TeamStatsRepository;
import jakarta.validation.Valid;

@Controller
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private TeamStatsRepository tStatsRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private PlayerStatsRepository pStatsRepository;

	//Sort teams by certain attribute
	//Default sort by rank
	@GetMapping("/teamlist")
    public String teamList(@RequestParam(required = false, defaultValue = "asc") String rank, Model model) {
        List<Team> teams;

        if ("asc".equalsIgnoreCase(rank)) {
            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
        } else if ("desc".equalsIgnoreCase(rank)) {
            teams = teamRepository.findAllByOrderByTeamStatsRankDesc();
        } else {
            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
        }

        model.addAttribute("teams", teams);
        model.addAttribute("rank", rank);

        return "teamlist";
    }
	
	@GetMapping("/teamlist/name")
    public String teamListByName(@RequestParam(required = false) String order, Model model) {
        List<Team> teams;

        if ("asc".equalsIgnoreCase(order)) {
            teams = teamRepository.findAllByOrderByNameAsc();
        } else if ("desc".equalsIgnoreCase(order)) {
            teams = teamRepository.findAllByOrderByNameDesc();
        } else {
            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
        }

        model.addAttribute("teams", teams);
        model.addAttribute("order", order);

        return "teamlist";
    }
	
	@GetMapping("/teamlist/wins")
	public String teamListByWins(@RequestParam(required = false) String order, Model model) {
		List<Team> teams;
		
		 if ("asc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsWinsAsc();
	        } else if ("desc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsWinsDesc();
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", teams);
		 model.addAttribute("order", order);
		return"teamlist";
	}
	
	@GetMapping("/teamlist/draws")
	public String teamListByDraws(@RequestParam(required = false) String order, Model model) {
		List<Team> teams;
		
		 if ("asc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsDrawsAsc();
	        } else if ("desc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsDrawsDesc();
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", teams);
		 model.addAttribute("order", order);
		return"teamlist";
	}
	
	@GetMapping("/teamlist/losses")
	public String teamListByLosses(@RequestParam(required = false) String order, Model model) {
		List<Team> teams;
		
		 if ("asc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsLossesAsc();
	        } else if ("desc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsLossesDesc();
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", teams);
		 model.addAttribute("order", order);
		return"teamlist";
	}
	
	@GetMapping("/teamlist/goalsfor")
	public String teamListByGoalsFor(@RequestParam(required = false) String order, Model model) {
		List<Team> teams;
		
		 if ("asc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsGoalsForAsc();
	        } else if ("desc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsGoalsForDesc();
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", teams);
		 model.addAttribute("order", order);
		return"teamlist";
	}
	
	@GetMapping("/teamlist/goalsagainst")
	public String teamListByGoalsAgainst(@RequestParam(required = false) String order, Model model) {
		List<Team> teams;
		
		 if ("asc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsGoalsAgainstAsc();
	        } else if ("desc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsGoalsAgainstDesc();
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", teams);
		 model.addAttribute("order", order);
		return"teamlist";
	}
	
	@GetMapping("/teamlist/goaldiff")
	public String teamListByGoalDiff(@RequestParam(required = false) String order, Model model) {
		List<Team> teams;
		
		 if ("asc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsGoalDiffAsc();
	        } else if ("desc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsGoalDiffDesc();
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", teams);
		 model.addAttribute("order", order);
		return"teamlist";
	}
	
	@GetMapping("/teamlist/points")
	public String teamListByPoints(@RequestParam(required = false) String order, Model model) {
		List<Team> teams;
		
		 if ("asc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsPointsAsc();
	        } else if ("desc".equalsIgnoreCase(order)) {
	            teams = teamRepository.findAllByOrderByTeamStatsPointsDesc();
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", teams);
		 model.addAttribute("order", order);
		return"teamlist";
	}
	
	@GetMapping("/teamlist/form")
	public String teamListByForm(@RequestParam(required = false) String order, Model model) {
		List<Team> teams = teamRepository.findAllByOrderByNameAsc();
		HashMap<String, Integer> teamsAndPoints = new HashMap<String, Integer>();
		//Go through all team and calculate points 
		for(int i = 0; i < teams.size() ; i++) {
			Team team = teams.get(i);
			String form = team.getTeamStats().getForm();
		    int pointsFromForm = 0;
		    for(int j = 0; j < form.length(); j++) {
		    	if(form.charAt(j) == 'W') {
		    		pointsFromForm += 3;
		    	}
		    	else if(form.charAt(j) == 'D') {
		    		pointsFromForm += 1;
		    	}
		    }
		    teamsAndPoints.put(team.getName(), pointsFromForm);
		}
		
		//A list of entries from the HashMap
		List<Map.Entry<String, Integer>> entryList = new ArrayList<>(teamsAndPoints.entrySet());
		
		//Sort the list of entries by points
		Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
		
		//Iterating through the sorted list and adding corresponding teams to sortedTeams
		 List<Team> sortedTeams = new ArrayList<>();
	        for (Map.Entry<String, Integer> entry : entryList) {
	            String teamName = entry.getKey();
	            Team team = teamRepository.findByName(teamName).get(0);
	            sortedTeams.add(team);
	        }
	        
	        
	        if ("asc".equalsIgnoreCase(order)) {
	        	Collections.reverse(sortedTeams);
	        } else if ("desc".equalsIgnoreCase(order)) {
	        	
	        } else {
	            teams = teamRepository.findAllByOrderByTeamStatsRankAsc();
	        }
		 
		 model.addAttribute("teams", sortedTeams);
		 model.addAttribute("order", order);
		return"teamlist";
	}

	@GetMapping("/deleteteam/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTeam(@PathVariable("id") Long teamId) {
		//Delete team and team's every player
		Team team = teamRepository.findById(teamId).orElse(null);

		if (team != null) {
			List<Player> playersInTeam = playerRepository.findByTeam(team);
			for (Player player : playersInTeam) {
				pStatsRepository.delete(player.getPlayerStats());
				playerRepository.delete(player);
			}
		}
		TeamStats teamStats = team.getTeamStats();
		tStatsRepository.delete(teamStats);
		teamRepository.delete(team);
		return "redirect:/teamlist";
	}

	@GetMapping("/addteam")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addTeam(Model model) {
		model.addAttribute("team", new Team());
		return "addteam";
	}

	@PostMapping("/saveteam")
	public String saveTeam(@Valid @ModelAttribute Team team, BindingResult bindingResult, Model model) {
		//Editing existingteam's information
		if (team.getId() != null) {
			if (bindingResult.hasErrors()) { // If validation error
				Team existingTeam = teamRepository.findById(team.getId()).orElse(null);
				model.addAttribute("header", existingTeam);
				return "editteam";
			} else {
				//Find team from database
				Team existingTeam = teamRepository.findById(team.getId()).orElse(null);
				if (existingTeam != null) {
					//Update info and save updates
					existingTeam.setName(team.getName());
					existingTeam.setCity(team.getCity());
					existingTeam.setStadium(team.getStadium());
					existingTeam.setCapacity(team.getCapacity());
					existingTeam.setLogoUrl(team.getLogoUrl());
					existingTeam.setYearFounded(team.getYearFounded());
					
					TeamStats existingStats = existingTeam.getTeamStats();
					existingStats.setWins(team.getTeamStats().getWins());
					existingStats.setLosses(team.getTeamStats().getLosses());
					existingStats.setPoints(team.getTeamStats().getPoints());
					existingStats.setRank(team.getTeamStats().getRank());
					existingStats.setGoalsFor(team.getTeamStats().getGoalsFor());
					existingStats.setGoalsAgainst(team.getTeamStats().getGoalsAgainst());
					existingStats.setGoalDiff(team.getTeamStats().getGoalDiff());
					existingStats.setForm(team.getTeamStats().getForm().toUpperCase());

					tStatsRepository.save(existingStats);
					teamRepository.save(existingTeam);
				}
			}
		} else {
			//Saving new team
			if (bindingResult.hasErrors()) { // If validation error
				return "addteam";
			} else {
				TeamStats teamStats = team.getTeamStats();
				String form = team.getTeamStats().getForm().toUpperCase();
				teamStats.setForm(form);
				tStatsRepository.save(teamStats);
				team.setTeamStats(teamStats);
				teamRepository.save(team);
				teamStats.setTeam(team);
				tStatsRepository.save(teamStats);
			}
		}
		return "redirect:/teamlist";
	}

	@GetMapping("/editteam/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editTeam(@PathVariable("id") Long teamId, Model model) {
		//Edit team by ID
		Team existingTeam = teamRepository.findById(teamId).orElse(null);
		model.addAttribute("team", existingTeam);
		return "editteam";
	}

	@GetMapping("/showteam/{id}")
	public String showTeam(@PathVariable("id") Long teamId, Model model) {
		//Show team's page
		Team team = teamRepository.findById(teamId).orElse(null);
		List<Player> players = new ArrayList<>();
		players.addAll(team.getPlayers());
		model.addAttribute("team", team);
		model.addAttribute("players", players);
		return "team";
	}
}
