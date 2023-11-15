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

import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;

@CrossOrigin
@Controller
public class TeamRestController {

	@Autowired
	private TeamRepository teamRepository;

	// Player luokka muutetaan JSON playerlistaksi ja lähetetään webselaimeen
	// vastauksena
	@GetMapping(value="/teams")
	public @ResponseBody List<Team> playerListRest() {
		return (List<Team>) teamRepository.findAll();
	}

	// Saadaan Team Id:n perusteella
	@GetMapping(value="/team/{id}")
	public @ResponseBody Optional<Team> findPlayerRest(@PathVariable("id") Long teamId) {
		return teamRepository.findById(teamId);
	}

	// Uuden joukkueen tallennus
	@PostMapping(value="/teams")
	public @ResponseBody Team savePlayerRest(@RequestBody Team team) {
		return teamRepository.save(team);
	}

}
