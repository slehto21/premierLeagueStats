package hh.sof03.footballStats;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.footballStats.web.PlayerController;
import hh.sof03.footballStats.web.PlayerRestController;
import hh.sof03.footballStats.web.TeamController;
import hh.sof03.footballStats.web.TeamRestController;
import hh.sof03.footballStats.web.UserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FootballStatsApplicationTests {
	@Autowired
	PlayerController playerController;
	@Autowired
	PlayerRestController playerRestController;
	@Autowired
	TeamController teamController;
	@Autowired
	TeamRestController teamRestController;
	@Autowired
	UserController userController;
	
	@Test
	public void contextLoads() {
		assertThat(playerController).isNotNull();
		assertThat(playerRestController).isNotNull();
		assertThat(teamController).isNotNull();
		assertThat(teamRestController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
