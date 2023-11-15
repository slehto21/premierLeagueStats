package hh.sof03.footballStats.domain;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//Vältetään ifinite loop
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=2, max=30, message="Size must be between 2-30")
	private String firstName;
	
	@NotNull
	@Size(min=2, max=30, message="Size must be between 2-30")
	private String lastName;
	
	private String position;
	
	@Past(message="Birthday must be in the past")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bDay;
	
	@ManyToOne //Many players one team
	@JsonIgnoreProperties({"teamStats", "city", "yearFounded", "players"}) //Json dataan ei tule näitä
	@JoinColumn(name="teamId")
	private Team team;
	
	@OneToOne(mappedBy = "player")
	@JsonIgnoreProperties({"player"})
	private PlayerStats playerStats;
	
	//Constructors
	public Player(String firstName, String lastName, String position, Date bDay, Team team) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.bDay = bDay;
		this.team = team;
	}
	
	public Player() {
		
	}
	
	//Getters & Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getbDay() {
		return bDay;
	}

	public void setbDay(Date bDay) {
		this.bDay = bDay;
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public PlayerStats getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(PlayerStats playerStats) {
		this.playerStats = playerStats;
	}

	//toString
	@Override
	public String toString() {
		if(this.getTeam() != null) {
			return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", postion=" + position
					+ ", bDay=" + bDay + ", team=" + this.getTeam() + "]";
		}
		else {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", postion=" + position
				+ ", bDay=" + bDay + "]";
		}
	}
	
	
	
	
	
}
