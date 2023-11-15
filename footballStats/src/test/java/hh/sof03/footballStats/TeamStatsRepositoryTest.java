package hh.sof03.footballStats;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.footballStats.domain.Team;
import hh.sof03.footballStats.domain.TeamRepository;
import hh.sof03.footballStats.domain.TeamStats;
import hh.sof03.footballStats.domain.TeamStatsRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeamStatsRepositoryTest {
	
	@Autowired
	private TeamStatsRepository teamStatsRepository;
	@Autowired
	private TeamRepository teamRepository;
	
	@Test
	public void testFindByTeam() {
		//Olemassa olevan joukkueen tilastojen haku
		Team chelsea = teamRepository.findByName("Chelsea").get(0);
		TeamStats foundStats = teamStatsRepository.findByTeam(chelsea);
		assertThat(foundStats).isNotNull();
        assertThat(foundStats.getWins()).isEqualTo(3);
        assertThat(foundStats.getDraws()).isEqualTo(5);
        assertThat(foundStats.getLosses()).isEqualTo(4);
        assertThat(foundStats.getTeam()).isEqualTo(chelsea);
		
		//Kuvitteellisen joukkueen tilastojen haku
        List<Team> hjkList = teamRepository.findByName("HJK");
        TeamStats nonExistentTeamStats = hjkList.isEmpty() ? null : teamStatsRepository.findByTeam(hjkList.get(0));
        assertThat(nonExistentTeamStats).isNull();
	}
	
	@Test
	public void testCreateAndDeleteTeamStats() {
		//Uusien teamStats luonti ja tallennus
		TeamStats teamStats = new TeamStats(1,3,6,null);
		teamStatsRepository.save(teamStats);
		assertThat(teamStats.getId()).isNotNull();
		
		//TeamStats poisto
		teamStatsRepository.delete(teamStats);
		TeamStats deletedTeamStats = teamStatsRepository.findById(teamStats.getId()).orElse(null);
		assertThat(deletedTeamStats).isNull();
	}

}
