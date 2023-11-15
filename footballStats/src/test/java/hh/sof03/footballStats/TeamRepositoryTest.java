package hh.sof03.footballStats;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeamRepositoryTest {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Test
	public void findByTeamName() {
		//Olemassa oleva joukkue
		Team team = teamRepository.findByName("Liverpool").get(0);
		assertThat(team).isNotNull();
		assertThat(team.getName()).isEqualTo("Liverpool");
		assertThat(team.getCity()).isEqualTo("Liverpool");
		assertThat(team.getYearFounded()).isEqualTo(Year.of(1892));
		
		//Kuvitteellinen joukkue
		List<Team> sjkList = teamRepository.findByName("SJK");
		Team nonExistentTeam = sjkList.isEmpty() ? null : sjkList.get(0);
		assertThat(nonExistentTeam).isNull();
	}
	
	@Test
	public void createAndDeleteTeam() {
		// Uuden Team luonti ja tallennus
		Team team = new Team("HJK", "Helsinki", Year.of(2023));
		teamRepository.save(team);
		assertThat(team.getId()).isNotNull();
		assertThat(team.getCity()).isEqualTo("Helsinki");
		assertThat(team.getName()).isEqualTo("HJK");
		assertThat(team.getYearFounded()).isEqualTo(Year.of(2023));
		
		// Team poisto
		teamRepository.delete(team);
		Team deletedTeam = teamRepository.findById(team.getId()).orElse(null);
		assertThat(deletedTeam).isNull();
	}

}
