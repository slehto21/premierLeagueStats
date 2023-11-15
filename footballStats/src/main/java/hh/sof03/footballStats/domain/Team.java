package hh.sof03.footballStats.domain;

import java.time.Year;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//Vältetään ifinite loop
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=2, max=30, message="Size must be between 2-30")
	private String name; 
	
	@NotNull
	@Size(min=2, max=30, message="Size must be between 2-30")
	private String city;
	
	@NotNull
	@PastOrPresent(message="Founding year cannot be in the future")
	private Year yearFounded;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team") //One team many players
	@JsonIgnoreProperties({"playerStats", "bDay", "team"}) //Json datassa ei näytetä näitä
	private List<Player> players;
	
	@OneToOne(mappedBy = "team")
	@JsonIgnoreProperties({"team"})
	private TeamStats teamStats;
	
	//Constructors
	public Team(String name, String city, Year yearFounded) {
		super();
		this.name = name;
		this.city = city;
		this.yearFounded = yearFounded;
	}
	
	public Team() {
		
	}

	//Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	//toString
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", city=" + city + ", yearFounded=" + yearFounded + "]";
	}
	
	
	
}
