package hh.sof03.footballStats.domain;

import java.time.Year;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties("players")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=2, max=50, message="Size must be between 2-50")
	private String name; 
	
	@NotNull
	@Size(min=2, max=50, message="Size must be between 2-50")
	private String city;
	
	@NotNull
	@Size(min=2, max=50, message="Size must be between 2-50")
	private String stadium;
	
	@PositiveOrZero(message="Capacity cant be negative")
	private int capacity; 
	
	private String logoUrl;
	
	@NotNull
	@PastOrPresent(message="Founding year cannot be in the future")
	private Year yearFounded;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	private List<Player> players;
	
	@OneToOne(mappedBy = "team")
	@JsonIgnoreProperties({"team"})
	private TeamStats teamStats;
	
	//Constructors
	public Team(String name, String city, String stadium, int capacity, String logoUrl ,Year yearFounded) {
		super();
		this.name = name;
		this.city = city;
		this.stadium = stadium;
		this.capacity = capacity;
		this.logoUrl = logoUrl;
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
	
	//toString
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", city=" + city + ", stadium=" + stadium + ", capacity="
				+ capacity + ", logoUrl=" + logoUrl + ", yearFounded=" + yearFounded + "]";
	}	
}
