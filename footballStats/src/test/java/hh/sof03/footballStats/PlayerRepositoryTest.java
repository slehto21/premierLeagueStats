package hh.sof03.footballStats;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.footballStats.domain.Player;
import hh.sof03.footballStats.domain.PlayerRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;
	
	SimpleDateFormat bDayFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	@Test
	public void findByPlayer() throws ParseException {
		//Olemassa olevan pelaajan haku
		Player player = playerRepository.findByFirstNameAndLastName("Reece", "James").get(0);
		
		assertThat(player).isNotNull();
		assertThat(player.getbDay()).isEqualToIgnoringHours(bDayFormat.parse("08.12.1999"));		
		assertThat(player.getFirstName()).isEqualTo("Reece");
		assertThat(player.getLastName()).isEqualTo("James");
		assertThat(player.getPosition()).isEqualTo("DEF");
		
		//Kuvitteellisen pelaajan haku
		List<Player> playerList = playerRepository.findByFirstNameAndLastName("Tommy", "Cash");
		Player nonExistentPlayer = playerList.isEmpty() ? null : playerList.get(0); 
        assertThat(nonExistentPlayer).isNull();
	}
	
	@Test
	public void testCreateAndDeleteTeamStats() throws ParseException {
		//Uuden Player luonti ja tallennus
		Player player = new Player("Tommy", "Cash", "MID", bDayFormat.parse("10.11.1997"), null);
		playerRepository.save(player);
		assertThat(player.getId()).isNotNull();
		assertThat(player.getbDay()).isEqualToIgnoringHours(bDayFormat.parse("10.11.1997"));
		assertThat(player.getFirstName()).isEqualTo("Tommy");
		assertThat(player.getLastName()).isEqualTo("Cash");
		assertThat(player.getPosition()).isEqualTo("MID");
		
		//Player poisto
		playerRepository.delete(player);
		Player deletedPlayer = playerRepository.findById(player.getId()).orElse(null);
		assertThat(deletedPlayer).isNull();
	}
}
