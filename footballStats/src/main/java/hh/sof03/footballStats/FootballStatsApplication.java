package hh.sof03.footballStats;

import java.text.SimpleDateFormat;
import java.time.Year;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
import hh.sof03.footballStats.domain.PlayerStats;
import hh.sof03.footballStats.domain.PlayerStatsRepository;
import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;
import hh.sof03.footballStats.domain.TeamStats;
import hh.sof03.footballStats.domain.TeamStatsRepository;
import hh.sof03.footballStats.domain.User;
import hh.sof03.footballStats.domain.UserRepository;

@SpringBootApplication
public class FootballStatsApplication {
	private static final Logger log = LoggerFactory.getLogger(FootballStatsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FootballStatsApplication.class, args);
	}

	@Bean
	public CommandLineRunner Demo(PlayerRepository playerRepository, TeamRepository teamRepository,
			PlayerStatsRepository pStatsRepository, TeamStatsRepository tStatsRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("Save teams");
			teamRepository.save(new Team("Chelsea", "London", Year.of(1905)));
			teamRepository.save(new Team("Manchester City", "Manchester", Year.of(1880)));
			teamRepository.save(new Team("Liverpool", "Liverpool", Year.of(1892)));
			teamRepository.save(new Team("Manchester United", "Manchester", Year.of(1878)));
	        teamRepository.save(new Team("Arsenal", "London", Year.of(1886)));
			teamRepository.save(new Team("No Team", "No City", Year.of(0)));

			log.info("Save players");
			SimpleDateFormat bDay = new SimpleDateFormat("dd.MM.yyyy");
			// Chelsea players
			playerRepository.save(new Player("Reece", "James", "DEF", bDay.parse("08.12.1999"),teamRepository.findByName("Chelsea").get(0)));
			playerRepository.save(new Player("Enzo", "Fernandez", "MID", bDay.parse("17.01.2001"), teamRepository.findByName("Chelsea").get(0)));
			playerRepository.save(new Player("Raheem", "Sterling", "ATT", bDay.parse("08.12.1994"), teamRepository.findByName("Chelsea").get(0)));
			playerRepository.save(new Player("Ben", "Chilwell", "DEF", bDay.parse("21.12.1996"), teamRepository.findByName("Chelsea").get(0)));
			playerRepository.save(new Player("Thiago", "Silva", "DEF", bDay.parse("22.09.1984"), teamRepository.findByName("Chelsea").get(0)));

			// Manchester City players
			playerRepository.save(new Player("Kevin", "De Bruyne", "MID", bDay.parse("28.06.1991"), teamRepository.findByName("Manchester City").get(0)));
			playerRepository.save(new Player("Phil", "Foden", "MID", bDay.parse("28.05.2000"),teamRepository.findByName("Manchester City").get(0)));
			playerRepository.save(new Player("Ruben", "Dias", "DEF", bDay.parse("14.05.1997"), teamRepository.findByName("Manchester City").get(0)));
			playerRepository.save(new Player("Erling", "Haaland", "ATT", bDay.parse("21.07.2000"), teamRepository.findByName("Manchester City").get(0)));
			playerRepository.save(new Player("Ederson", "Moraes", "GK", bDay.parse("17.08.1993"), teamRepository.findByName("Manchester City").get(0)));

			// Liverpool players
			playerRepository.save(new Player("Diogo", "Jota", "ATT", bDay.parse("04.12.1996"),teamRepository.findByName("Liverpool").get(0)));
			playerRepository.save(new Player("Virgil", "van Dijk", "DEF", bDay.parse("08.07.1991"), teamRepository.findByName("Liverpool").get(0)));
			playerRepository.save(new Player("Mohamed", "Salah", "ATT", bDay.parse("15.06.1992"), teamRepository.findByName("Liverpool").get(0)));
			playerRepository.save(new Player("Trent", "Alexander-Arnold", "DEF", bDay.parse("07.10.1998"), teamRepository.findByName("Liverpool").get(0)));
			playerRepository.save(new Player("Alisson", "Becker", "GK", bDay.parse("02.10.1992"), teamRepository.findByName("Liverpool").get(0)));

			// Manchester United players
			playerRepository.save(new Player("Bruno", "Fernandes", "MID", bDay.parse("08.09.1994"), teamRepository.findByName("Manchester United").get(0)));
			playerRepository.save(new Player("Harry", "Maguire", "DEF", bDay.parse("05.03.1993"), teamRepository.findByName("Manchester United").get(0)));
			playerRepository.save(new Player("Marcus", "Rashford", "ATT", bDay.parse("31.10.1997"), teamRepository.findByName("Manchester United").get(0)));
			playerRepository.save(new Player("Mason", "Mount", "MID", bDay.parse("10.01.1999"), teamRepository.findByName("Manchester United").get(0)));
			playerRepository.save(new Player("Andre", "Onana", "GK", bDay.parse("02.04.1996"), teamRepository.findByName("Manchester United").get(0)));

			// Arsenal players
			playerRepository.save(new Player("Eddie", "Nketiah", "ATT", bDay.parse("30.05.1999"), teamRepository.findByName("Arsenal").get(0)));
			playerRepository.save(new Player("Bukayo", "Saka", "MID", bDay.parse("05.09.2001"), teamRepository.findByName("Arsenal").get(0)));
			playerRepository.save(new Player("Gabriel", "Magalhães", "DEF", bDay.parse("19.12.1997"), teamRepository.findByName("Arsenal").get(0)));
			playerRepository.save(new Player("Thomas", "Partey", "MID", bDay.parse("13.06.1993"), teamRepository.findByName("Arsenal").get(0)));
			playerRepository.save(new Player("David", "Raya", "GK", bDay.parse("15.09.1995"), teamRepository.findByName("Arsenal").get(0)));


			log.info("Save player stats");
			// Chelsea players
			pStatsRepository.save(new PlayerStats(5, 5, 10, playerRepository.findByFirstNameAndLastName("Reece", "James").get(0)));
			pStatsRepository.save(new PlayerStats(7, 1, 10, playerRepository.findByFirstNameAndLastName("Enzo", "Fernandez").get(0)));
			pStatsRepository.save(new PlayerStats(4, 6, 8, playerRepository.findByFirstNameAndLastName("Raheem", "Sterling").get(0)));
			pStatsRepository.save(new PlayerStats(1, 0, 5, playerRepository.findByFirstNameAndLastName("Ben", "Chilwell").get(0)));
			pStatsRepository.save(new PlayerStats(1, 1, 9, playerRepository.findByFirstNameAndLastName("Thiago", "Silva").get(0)));

			// Manchester City players
			pStatsRepository.save(new PlayerStats(8, 2, 12, playerRepository.findByFirstNameAndLastName("Kevin", "De Bruyne").get(0)));
			pStatsRepository.save(new PlayerStats(6, 7, 15, playerRepository.findByFirstNameAndLastName("Phil", "Foden").get(0)));
			pStatsRepository.save(new PlayerStats(6, 3, 9, playerRepository.findByFirstNameAndLastName("Ruben", "Dias").get(0)));
			pStatsRepository.save(new PlayerStats(22, 3, 15, playerRepository.findByFirstNameAndLastName("Erling", "Haaland").get(0)));
			pStatsRepository.save(new PlayerStats(2, 1, 6, playerRepository.findByFirstNameAndLastName("Ederson", "Moraes").get(0)));

			// Liverpool players
			pStatsRepository.save(new PlayerStats(15, 3, 13, playerRepository.findByFirstNameAndLastName("Diogo", "Jota").get(0)));
			pStatsRepository.save(new PlayerStats(5, 4, 7, playerRepository.findByFirstNameAndLastName("Virgil", "van Dijk").get(0)));
			pStatsRepository.save(new PlayerStats(18, 7, 8, playerRepository.findByFirstNameAndLastName("Mohamed", "Salah").get(0)));
			pStatsRepository.save(new PlayerStats(8, 6, 11, playerRepository.findByFirstNameAndLastName("Trent", "Alexander-Arnold").get(0)));
			pStatsRepository.save(new PlayerStats(0, 0, 16, playerRepository.findByFirstNameAndLastName("Alisson", "Becker").get(0)));

			// Manchester United players
			pStatsRepository.save(new PlayerStats(10, 8, 16, playerRepository.findByFirstNameAndLastName("Bruno", "Fernandes").get(0)));
			pStatsRepository.save(new PlayerStats(6, 3, 13, playerRepository.findByFirstNameAndLastName("Harry", "Maguire").get(0)));
			pStatsRepository.save(new PlayerStats(12, 7, 9, playerRepository.findByFirstNameAndLastName("Marcus", "Rashford").get(0)));
			pStatsRepository.save(new PlayerStats(5, 3, 7, playerRepository.findByFirstNameAndLastName("Mason", "Mount").get(0)));
			pStatsRepository.save(new PlayerStats(3, 0, 10, playerRepository.findByFirstNameAndLastName("Andre", "Onana").get(0)));

			// Arsenal players
			pStatsRepository.save(new PlayerStats(14, 5, 9, playerRepository.findByFirstNameAndLastName("Eddie", "Nketiah").get(0)));
			pStatsRepository.save(new PlayerStats(8, 4, 8, playerRepository.findByFirstNameAndLastName("Bukayo", "Saka").get(0)));
			pStatsRepository.save(new PlayerStats(10, 2, 6, playerRepository.findByFirstNameAndLastName("Gabriel", "Magalhães").get(0)));
			pStatsRepository.save(new PlayerStats(6, 5, 12, playerRepository.findByFirstNameAndLastName("Thomas", "Partey").get(0)));
			pStatsRepository.save(new PlayerStats(0, 0, 6, playerRepository.findByFirstNameAndLastName("David", "Raya").get(0)));

			log.info("Save team stats");
			tStatsRepository.save(new TeamStats(3, 5, 4, teamRepository.findByName("Chelsea").get(0)));
			tStatsRepository.save(new TeamStats(8, 2, 2, teamRepository.findByName("Manchester City").get(0)));
			tStatsRepository.save(new TeamStats(6, 5, 1, teamRepository.findByName("Liverpool").get(0)));
			tStatsRepository.save(new TeamStats(9, 2, 1, teamRepository.findByName("Arsenal").get(0)));
			tStatsRepository.save(new TeamStats(2, 8, 3, teamRepository.findByName("Manchester United").get(0)));

			// Create users: admin/admin user/user 
			userRepository.save(new User("$2a$10$g6ilWicFRLenYZs1WUmsuOKrf57Ellu.3XeEjsnum7db4scqSj.je", "user@gmail.com","USER", "user"));
			userRepository.save(new User("$2a$10$p456Kx1S04M6kcU7UuCxxOFgSk5ilapdL4byoLQHUoRxL2NnQ3r7.", "admin@gmail.com","ADMIN", "admin"));

//			log.info("Fetch all teams");
//			for (Team team : teamRepository.findAll()) {
//				log.info(team.toString());
//			}
//
//			log.info("Fetch all players");
//			for (Player player : playerRepository.findAll()) {
//				log.info(player.toString());
//			}
		};

	}

}
