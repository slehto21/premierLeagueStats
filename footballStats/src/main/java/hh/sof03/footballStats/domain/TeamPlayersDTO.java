package hh.sof03.footballStats.domain;

import java.time.Year;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TeamPlayersDTO {
	
	private Long teamId;
	
	private String name;
	
	private String city;
	
	private String stadium;
	
	private int capacity; 
	
	private String logoUrl;
	
	private Year yearFounded;
	
	@JsonIgnoreProperties("team")
	private TeamStats teamStats;
	
	@JsonIgnoreProperties({"playerStats", "team"})
	private List<Player> players;

	//Constructors
	public TeamPlayersDTO(Long teamId, String name, String city, String stadium, int capacity, String logoUrl,
			Year yearFounded, List<Player> players, TeamStats teamStats) {
		this.teamId = teamId;
		this.name = name;
		this.city = city;
		this.stadium = stadium;
		this.capacity = capacity;
		this.logoUrl = logoUrl;
		this.yearFounded = yearFounded;
		this.players = players;
		this.teamStats = teamStats;
	}
	
	public TeamPlayersDTO() {
		
	}

	//Getters & Setters
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Year getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(Year yearFounded) {
		this.yearFounded = yearFounded;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public TeamStats getTeamStats() {
		return teamStats;
	}

	public void setTeamStats(TeamStats teamStats) {
		this.teamStats = teamStats;
	}
}
