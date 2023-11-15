package hh.sof03.footballStats.domain;

import org.springframework.data.repository.CrudRepository;

public interface TeamStatsRepository extends CrudRepository<TeamStats, Long> {
	TeamStats findByTeam(Team team);
}
