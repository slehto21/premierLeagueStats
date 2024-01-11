package hh.sof03.footballStats;

import java.time.LocalDate;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
import hh.sof03.footballStats.domain.PlayerStats;
import hh.sof03.footballStats.domain.PlayerStatsRepository;
import hh.sof03.footballStats.domain.TeamRepository;
import hh.sof03.footballStats.domain.TeamStats;
import hh.sof03.footballStats.domain.TeamStatsRepository;
import hh.sof03.footballStats.domain.UserRepository;
import hh.sof03.footballStats.service.PlayerApiCall;
import hh.sof03.footballStats.service.TeamApiCall;
import hh.sof03.footballStats.service.ApiCallTimer;
import hh.sof03.footballStats.domain.Team;

@SpringBootApplication
public class FootballStatsApplication {

	@Autowired
	private ApiCallTimer apiCallTimer;

	public static void main(String[] args) {
		SpringApplication.run(FootballStatsApplication.class, args);
	}

	@Bean
	public CommandLineRunner Demo(PlayerRepository playerRepository, TeamRepository teamRepository,
			PlayerStatsRepository pStatsRepository, TeamStatsRepository tStatsRepository,
			UserRepository userRepository) {
		return (args) -> {
			
			apiCallTimer.main();
			
			
			// For testing
//			teamRepository.save(new Team("Arsenal", "London", "Emirates Stadium", 60361,
//			        "https://media-4.api-sports.io/football/teams/42.png", Year.of(1886)));
//
//			teamRepository.save(new Team("Aston Villa", "Birmingham", "Villa Park", 42785,
//			        "https://media-4.api-sports.io/football/teams/2.png", Year.of(1874)));
//
//			teamRepository.save(new Team("Bournemouth", "Bournemouth", "Vitality Stadium", 11464,
//			        "https://media-4.api-sports.io/football/teams/127.png", Year.of(1899)));
//
//
//			tStatsRepository.save(new TeamStats(10, 5, 5, 35, 4, 30, 20, 10, "WWLWL", teamRepository.findByName("Arsenal").get(0)));
//
//			tStatsRepository.save(new TeamStats(8, 6, 6, 30, 10, 25, 22, 3, "LDWWD", teamRepository.findByName("Aston Villa").get(0)));
//
//			tStatsRepository.save(new TeamStats(7, 7, 6, 28, 12, 22, 20, 2, "WLWLD", teamRepository.findByName("Bournemouth").get(0)));
//			
//			playerRepository.save(new Player("Jose Mourinho", "Jose", "Mourinho", 54, "Defender", "Portugal", "185 cm",
//					"85 kg", "https://img.a.transfermarkt.technology/portrait/big/781-1663951943.jpg?lm=1",
//					LocalDate.of(1968, 1, 8), teamRepository.findByName("Arsenal").get(0)));
//			pStatsRepository.save(new PlayerStats(7, 3, 15, 340, 6.8, 13, 7, 0, 3, 1,
//					playerRepository.findByName("Jose Mourinho").get(0)));
//
//			playerRepository.save(new Player("Santos", "Andrei", "Santos", 32, "Midfielder", "Brazil", "170 cm",
//					"72 kg", "https://img.a.transfermarkt.technology/portrait/big/781-1663951943.jpg?lm=1",
//					LocalDate.of(1991, 6, 4), teamRepository.findByName("Arsenal").get(0)));
//			pStatsRepository.save(
//					new PlayerStats(2, 4, 11, 250, 7.2, 3, 1, 0, 1, 0, playerRepository.findByName("Santos").get(0)));
//
//			playerRepository.save(new Player("C.Disasi", "Ceci", "Disasi", 21, "Goalkeeper", "Senegal", "195 cm",
//					"80 kg", "https://img.a.transfermarkt.technology/portrait/big/781-1663951943.jpg?lm=1",
//					LocalDate.of(2002, 4, 6), teamRepository.findByName("Bournemouth").get(0)));
//			pStatsRepository.save(new PlayerStats(0, 0, 4, 360, 5.4, 0, 0, 6, 1, 0,
//					playerRepository.findByName("C.Disasi").get(0)));
		

		};

	}

}
