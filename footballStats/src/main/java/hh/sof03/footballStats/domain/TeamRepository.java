package hh.sof03.footballStats.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
	 List<Team> findByName(String name);
	 List<Team> findAllByOrderByTeamStatsRankAsc();
	 List<Team> findAllByOrderByTeamStatsRankDesc();
	 List<Team> findAllByOrderByNameAsc();
	 List<Team> findAllByOrderByNameDesc();
	 List<Team> findAllByOrderByTeamStatsWinsAsc();
	 List<Team> findAllByOrderByTeamStatsWinsDesc();
	 List<Team> findAllByOrderByTeamStatsLossesAsc();
	 List<Team> findAllByOrderByTeamStatsLossesDesc();
	 List<Team> findAllByOrderByTeamStatsDrawsAsc();
	 List<Team> findAllByOrderByTeamStatsDrawsDesc();
	 List<Team> findAllByOrderByTeamStatsGoalsForAsc();
	 List<Team> findAllByOrderByTeamStatsGoalsForDesc();
	 List<Team> findAllByOrderByTeamStatsGoalsAgainstAsc();
	 List<Team> findAllByOrderByTeamStatsGoalsAgainstDesc();
	 List<Team> findAllByOrderByTeamStatsGoalDiffAsc();
	 List<Team> findAllByOrderByTeamStatsGoalDiffDesc();
	 List<Team> findAllByOrderByTeamStatsPointsAsc();
	 List<Team> findAllByOrderByTeamStatsPointsDesc();
	 List<Team> findAllByOrderByTeamStatsFormAsc();
}
