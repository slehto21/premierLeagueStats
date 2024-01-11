package hh.sof03.footballStats.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	 List<Player> findByFirstNameAndLastName(String firstName, String lastName);
	 List<Player> findByName(String name);
	 List<Player> findByTeam(Team team);
	 List<Player> findAllByOrderByNameAsc();
	 List<Player> findAllByOrderByNameDesc();
	 List<Player> findAllByOrderByTeamNameAsc();
	 List<Player> findAllByOrderByTeamNameDesc();
	 List<Player> findAllByOrderByPositionAsc();
	 List<Player> findAllByOrderByPositionDesc();
	 List<Player> findAllByOrderByPlayerStatsGoalsAsc();
	 List<Player> findAllByOrderByPlayerStatsGoalsDesc();
	 List<Player> findAllByOrderByPlayerStatsAssistsAsc();
	 List<Player> findAllByOrderByPlayerStatsAssistsDesc();
	 List<Player> findAllByOrderByPlayerStatsMatchesAsc();
	 List<Player> findAllByOrderByPlayerStatsMatchesDesc();
	 List<Player> findAllByOrderByPlayerStatsMinutesAsc();
	 List<Player> findAllByOrderByPlayerStatsMinutesDesc();
	 List<Player> findAllByOrderByPlayerStatsRatingAsc();
	 List<Player> findAllByOrderByPlayerStatsRatingDesc();
	 List<Player> findAllByOrderByPlayerStatsShotsAsc();
	 List<Player> findAllByOrderByPlayerStatsShotsDesc();
	 List<Player> findAllByOrderByPlayerStatsShotsOnTargetAsc();
	 List<Player> findAllByOrderByPlayerStatsShotsOnTargetDesc();
	 List<Player> findAllByOrderByPlayerStatsSavesAsc();
	 List<Player> findAllByOrderByPlayerStatsSavesDesc();
	 List<Player> findAllByOrderByPlayerStatsYellowsAsc();
	 List<Player> findAllByOrderByPlayerStatsYellowsDesc();
	 List<Player> findAllByOrderByPlayerStatsRedsAsc();
	 List<Player> findAllByOrderByPlayerStatsRedsDesc();
}