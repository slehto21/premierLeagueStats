package hh.sof03.footballStats;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;
import hh.sof03.footballStats.domain.PlayerStats;
import hh.sof03.footballStats.domain.PlayerStatsRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlayerStatsRepositoryTest {
	
	@Autowired
	private PlayerStatsRepository playerStatsRepository;
	@Autowired
	private PlayerRepository playerRepository;
	
	@Test
	public void findByPlayer() {
		//Olemassa olevan pelaajan tilastojen haku
		Player james = playerRepository.findByFirstNameAndLastName("Reece", "James").get(0);
		PlayerStats foundStats = playerStatsRepository.findById(james.getId()).orElse(null);
		assertThat(foundStats).isNotNull();
		assertThat(foundStats.getAssists()).isEqualTo(5);
		assertThat(foundStats.getGoals()).isEqualTo(2);
		assertThat(foundStats.getMatches()).isEqualTo(10);
		
		//Kuvitteellisen pelaajan tilastojen haku
		List<Player> player = playerRepository.findByFirstNameAndLastName("Tommy", "Cash");
        PlayerStats nonExistentPlayerStats = player.isEmpty() ? null : player.get(0).getPlayerStats();
        assertThat(nonExistentPlayerStats).isNull();
	}
	
	@Test
	public void testCreateAndDeleteTeamStats() {
		//Uusien PlayerStats luonti ja tallennus
		PlayerStats playerStats = new PlayerStats(1,2,3,null);
		playerStatsRepository.save(playerStats);
		assertThat(playerStats.getId()).isNotNull();
		assertThat(playerStats.getAssists()).isEqualTo(2);
		assertThat(playerStats.getGoals()).isEqualTo(1);
		assertThat(playerStats.getMatches()).isEqualTo(3);
		
		//PlayerStats poisto
		playerStatsRepository.delete(playerStats);
		PlayerStats deletedPlayerStats = playerStatsRepository.findById(playerStats.getId()).orElse(null);
		assertThat(deletedPlayerStats).isNull();
	}
}
