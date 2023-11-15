package hh.sof03.footballStats.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	 List<Player> findByFirstNameAndLastName(String firstName, String lastName);
	 List<Player> findByTeam(Team team);

}
