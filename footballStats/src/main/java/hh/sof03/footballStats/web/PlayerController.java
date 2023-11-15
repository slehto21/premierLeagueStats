package hh.sof03.footballStats.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping("/playerlist")
	public String playerList(Model model) {
		//Kaikkien tietokannassa olevien pelaajien välitys sivulle
		//Järjestetään pelaajat satunnaiseen järjestykseen
		Iterable<Player> playersIterable = playerRepository.findAll();
		List<Player> players = new ArrayList<>();
		playersIterable.forEach(players::add);
		Collections.shuffle(players);
		model.addAttribute("players", players);
		return "playerlist";
	}
	
	@GetMapping("/playerlist/goals")
	public String playerlistByGoals(Model model) {
		Iterable<Player> playersIterable = playerRepository.findAll();
		List<Player> players = new ArrayList<>();
		playersIterable.forEach(players::add);
		List<Player> sortedPlayers = players.stream()
	            .sorted((p1, p2) -> Integer.compare(p2.getPlayerStats().getGoals(), p1.getPlayerStats().getGoals()))
	            .collect(Collectors.toList());
		model.addAttribute("players", sortedPlayers);
		return "playerlistsorted";
	}
	
	@GetMapping("/playerlist/assists")
	public String playerlistByAssists(Model model) {
		Iterable<Player> playersIterable = playerRepository.findAll();
		List<Player> players = new ArrayList<>();
		playersIterable.forEach(players::add);
		List<Player> sortedPlayers = players.stream()
	            .sorted((p1, p2) -> Integer.compare(p2.getPlayerStats().getAssists(), p1.getPlayerStats().getAssists()))
	            .collect(Collectors.toList());
		model.addAttribute("players", sortedPlayers);
		return "playerlistsorted";
	}
	
	@GetMapping("/playerlist/matches")
	public String playerlistByMatches(Model model) {
		Iterable<Player> playersIterable = playerRepository.findAll();
		List<Player> players = new ArrayList<>();
		playersIterable.forEach(players::add);
		List<Player> sortedPlayers = players.stream()
	            .sorted((p1, p2) -> Integer.compare(p2.getPlayerStats().getMatches(), p1.getPlayerStats().getMatches()))
	            .collect(Collectors.toList());
		model.addAttribute("players", sortedPlayers);
		return "playerlistsorted";
	}

	@GetMapping("/deleteplayer/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletePlayer(@PathVariable("id") Long playerId) {
		//Pelaajan poistaminen indeksin perusteella, poistetaan ensin tilastot ja sen jälkeen pelaaja
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
	public String addPlayer(Model model) { //Uuden pelaajan lisääminen
		//Järjestetään joukkueet aakkosten mukaan ja laitetaan "No Team" listan ensimmäiseksi
		//Annetaan joukkueet ja tyhjä pelaaja olio thymeleafiin
		Iterable<Team> teamsIterable = teamRepository.findAll();
	    List<Team> teamsInOrder = new ArrayList<>();
	    Team noTeam = null;
	    for (Team team : teamsIterable) {
	    	if ("No Team".equals(team.getName())) {
	            noTeam = team;
	        }
	    	else {
	    		teamsInOrder.add(team);
	    	}
	    	
	    }
	    Collections.sort(teamsInOrder, Comparator.comparing(Team::getName));
	    teamsInOrder.add(0, noTeam);
	    
		
		model.addAttribute("teams", teamsInOrder);
		model.addAttribute("player", new Player());
		return "addplayer";
	}

	@PostMapping("/saveplayer")
	public String savePlayer(@Valid Player player, BindingResult bindingResult, Model model) {

		if (player.getId() != null) {
			// Olemassa olevan pelaajan päivitys

			if (bindingResult.hasErrors()) { // Jos validation error niin ohjataan käyttäjä editplayer templateen
				Player existingPlayer = playerRepository.findById(player.getId()).orElse(null);
				model.addAttribute("teams", teamRepository.findAll());
				model.addAttribute("header", existingPlayer);
				return "editplayer";
			} else {
				// Jos erroria ei ole, niin etsitään olemassa oleva pelaaja tietokannasta
				Player existingPlayer = playerRepository.findById(player.getId()).orElse(null);
				if (existingPlayer != null) {
					// Tietojen päivitys
					existingPlayer.setFirstName(player.getFirstName());
					existingPlayer.setLastName(player.getLastName());
					existingPlayer.setbDay(player.getbDay());
					existingPlayer.setPosition(player.getPosition());
					existingPlayer.setTeam(player.getTeam());
					// Tilastojen päivistys
					PlayerStats existingStats = existingPlayer.getPlayerStats();
					existingStats.setAssists(player.getPlayerStats().getAssists());
					existingStats.setGoals(player.getPlayerStats().getGoals());
					existingStats.setMatches(player.getPlayerStats().getMatches());
					// Tallenus tietokantaan
					pStatsRepository.save(existingStats);
					playerRepository.save(existingPlayer);

				}
			}
		} else {
			// Uuden pelaajan tallentaminen
			if (bindingResult.hasErrors()) { // Jos validation error niin ohjataan käyttäjä addplayer templateen
				model.addAttribute("teams", teamRepository.findAll());
				return "addplayer";
			} else {
				//Tallennetaan ensiksi pelaajan tilastot, jonka jälkeen pelaaja ja sitten vielä päivitetään 
				//tilastoihin oikea Player ID
				PlayerStats playerStats = player.getPlayerStats();
		        pStatsRepository.save(playerStats);
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
		//Pelajaan tietojen muokkaus pelaalan ID:n perusteella
		Player existingPlayer = playerRepository.findById(playerId).orElse(null);
		model.addAttribute("player", existingPlayer);
		model.addAttribute("teams", teamRepository.findAll());
		return "editplayer";
	}

	@GetMapping("/showplayer/{id}")
	public String showPlayer(@PathVariable("id") Long playerId, Model model) {
		//Yksittäisen pelaajan näyttäminen
		Player player = playerRepository.findById(playerId).orElse(null);
		model.addAttribute("player", player);
		return "player";
	}

}
