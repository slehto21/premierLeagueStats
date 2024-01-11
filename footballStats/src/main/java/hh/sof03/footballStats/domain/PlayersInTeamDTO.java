package hh.sof03.footballStats.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PlayersInTeamDTO {
	
	private String teamName;
	
	private Long id;
	
	@JsonIgnoreProperties({"team"})
	private List<Player> players;

	//Constructors
	public PlayersInTeamDTO(String teamName, Long id, List<Player> players) {
		this.teamName = teamName;
		this.id = id;
		this.players = players;
	} 
	
	public PlayersInTeamDTO() {
		
	}
	
	//Getters & Setters
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
