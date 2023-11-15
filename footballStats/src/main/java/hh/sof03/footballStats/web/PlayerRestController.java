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

@CrossOrigin
@Controller
public class PlayerRestController {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	//Player luokka muutetaan JSON playerlistaksi ja lähetetään webselaimeen vastauksena
	@GetMapping(value="/players")
	public @ResponseBody List<Player> playerListRest() {
		return (List<Player>)playerRepository.findAll();
	}
	
	//Saadaan player Id:n perusteella
	@GetMapping(value="/player/{id}")
	public @ResponseBody Optional<Player> findPlayerRest(@PathVariable("id")Long playerId){
		return playerRepository.findById(playerId);
	}
	
	//Uuden pelaajan tallennus
	@PostMapping(value="/players")
	public @ResponseBody Player savePlayerRest(@RequestBody Player player) {
		return playerRepository.save(player);
	}

}
