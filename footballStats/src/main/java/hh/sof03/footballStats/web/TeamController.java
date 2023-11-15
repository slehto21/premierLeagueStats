package hh.sof03.footballStats.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
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

	@GetMapping("/teamlist")
	public String teamList(Model model) {
		//Kaikkien joukkueiden esittäminen sivulla
		Iterable<Team> teamsIterable = teamRepository.findAll();
		List<Team> teams = new ArrayList<>();
		teamsIterable.forEach(teams::add);
		// Poistetaan "No Team" listalta
		teams = teams.stream().filter(team -> !"No Team".equals(team.getName())).collect(Collectors.toList());
		model.addAttribute("teams", teams);
		return "teamlist";
	}
	
	@GetMapping("/teamlist/wins")
	public String teamListByWins(Model model) {
		//Joukkueet voittojen mukaan
		Iterable<Team> teamsIterable = teamRepository.findAll();
		List<Team> teams = new ArrayList<>();
		teamsIterable.forEach(teams::add);
		teams = teams.stream().filter(team -> !"No Team".equals(team.getName())).collect(Collectors.toList());
		List<Team> sortedTeams = teams.stream()
				.sorted((t1, t2) -> Integer.compare(t2.getTeamStats().getWins(), t1.getTeamStats().getWins()))
	            .collect(Collectors.toList());
		model.addAttribute("teams", sortedTeams);
		return"teamlistsorted";
	}
	
	@GetMapping("/teamlist/draws")
	public String teamListByDraws(Model model) {
		//Joukkueet tasapelien mukaan
		Iterable<Team> teamsIterable = teamRepository.findAll();
		List<Team> teams = new ArrayList<>();
		teamsIterable.forEach(teams::add);
		teams = teams.stream().filter(team -> !"No Team".equals(team.getName())).collect(Collectors.toList());
		List<Team> sortedTeams = teams.stream()
				.sorted((t1, t2) -> Integer.compare(t2.getTeamStats().getDraws(), t1.getTeamStats().getDraws()))
	            .collect(Collectors.toList());
		model.addAttribute("teams", sortedTeams);
		return"teamlistsorted";
	}
	
	@GetMapping("/teamlist/losses")
	public String teamListByLosses(Model model) {
		//Joukkueet häviöiden mukaan
		Iterable<Team> teamsIterable = teamRepository.findAll();
		List<Team> teams = new ArrayList<>();
		teamsIterable.forEach(teams::add);
		teams = teams.stream().filter(team -> !"No Team".equals(team.getName())).collect(Collectors.toList());
		List<Team> sortedTeams = teams.stream()
				.sorted((t1, t2) -> Integer.compare(t2.getTeamStats().getLosses(), t1.getTeamStats().getLosses()))
	            .collect(Collectors.toList());
		model.addAttribute("teams", sortedTeams);
		return"teamlistsorted";
	}

	@GetMapping("/deleteteam/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTeam(@PathVariable("id") Long teamId) {
		//Joukkueen poistaminen ID:n perusteella
		Team team = teamRepository.findById(teamId).orElse(null);

		if (team != null) {
			List<Player> playersInTeam = playerRepository.findByTeam(team);

			// Asetetaan pelaajien joukkueeksi "No Team"
			for (Player player : playersInTeam) {
				player.setTeam(teamRepository.findByName("No Team").get(0));
				playerRepository.save(player);
			}
		}
		// Poistetaan joukkue ja sen tilastot
		TeamStats teamStats = team.getTeamStats();
		tStatsRepository.delete(teamStats);
		teamRepository.delete(team);
		return "redirect:/teamlist";
	}

	@GetMapping("/addteam")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addTeam(Model model) {//Joukkueen lisääminen
		model.addAttribute("team", new Team());
		return "addteam";
	}

	@PostMapping("/saveteam")
	public String saveTeam(@Valid @ModelAttribute Team team, BindingResult bindingResult, Model model) {
		// Olemassa olevan joukkueen muokkaaminen, jotta teamStats tauluun ei tule
		// ylimääräsiä tilastoja, joilla ei ole teamId attribuuttia
		if (team.getId() != null) {
			if (bindingResult.hasErrors()) { // Jos tulee validointi error niin ohjataan käyttäjä takaisin editteam
				Team existingTeam = teamRepository.findById(team.getId()).orElse(null);
				model.addAttribute("header", existingTeam);
				return "editteam";
			} else {
				// Jos erroria ei tule niin etsitään muokattava joukkue tietokannasta
				Team existingTeam = teamRepository.findById(team.getId()).orElse(null);
				if (existingTeam != null) {
					// Joukkuetietojen pävitys
					existingTeam.setName(team.getName());
					existingTeam.setCity(team.getCity());
					existingTeam.setYearFounded(team.getYearFounded());
					// Tilastojen päivitys
					TeamStats existingStats = existingTeam.getTeamStats();
					existingStats.setWins(team.getTeamStats().getWins());
					existingStats.setLosses(team.getTeamStats().getLosses());
					existingStats.setDraws(team.getTeamStats().getDraws());
					// Tallenus tietokantaan
					tStatsRepository.save(existingStats);
					teamRepository.save(existingTeam);
				}
			}
		} else {
			if (bindingResult.hasErrors()) { // Jos validation error niin ohjataan käyttäjä addteam
				return "addteam";
			} else { // Jos ei erroria niin lisätään joukkue tietokantaan
				// Tallennetaan tilastot ensin teamRepositoryyn, jotta teamId olisi luotu eikä
				// teamStats jää ilman teamId
				TeamStats teamStats = team.getTeamStats();
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
		//Joukkueen muokkaaminen ID:n perusteella
		Team existingTeam = teamRepository.findById(teamId).orElse(null);
		model.addAttribute("team", existingTeam);
		return "editteam";
	}

	@GetMapping("/showteam/{id}")
	public String showTeam(@PathVariable("id") Long teamId, Model model) {
		//Näytetään yksittäinen joukkue, sen tilastot ja pelaajat
		Team team = teamRepository.findById(teamId).orElse(null);
		List<Player> players = new ArrayList<>();
		players.addAll(team.getPlayers());
		model.addAttribute("team", team);
		model.addAttribute("players", players);
		return "team";
	}
}
