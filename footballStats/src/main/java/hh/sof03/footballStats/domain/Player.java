package hh.sof03.footballStats.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min=2, max=30, message="Size must be between 2-30")
	private String name;
	
	@Size(min=2, max=30, message="Size must be between 2-30")
	private String firstName;
	
	@Size(min=2, max=30, message="Size must be between 2-30")
	private String lastName;
	
	private int age;
	
	private String nationality;
	
	private String height;

	private String weight;
	
	private String playerPhoto;
	
	private String position;
	
	@Past(message="Birthday must be in the past")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate bDay;
	
	@ManyToOne
	@JsonIgnoreProperties({"teamStats", "city", "yearFounded", "logoUrl", "stadium", "capacity", "players"}) 
	@JoinColumn(name="teamId")
	private Team team;
	
	@OneToOne(mappedBy = "player")
	@JsonIgnoreProperties({"player"})
	private PlayerStats playerStats;
	
	//Constructors
	public Player(String name, String firstName, String lastName, int age, String position, String nationality,
			 String height, String weight, String playerPhoto, LocalDate bDay, Team team) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.position = position;
		this.nationality = nationality;
		this.height = height;
		this.weight = weight;
		this.playerPhoto = playerPhoto;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LocalDate getbDay() {
		return bDay;
	}

	public void setbDay(LocalDate bDay) {
		this.bDay = bDay;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPlayerPhoto() {
		return playerPhoto;
	}

	public void setPlayerPhoto(String playerPhoto) {
		this.playerPhoto = playerPhoto;
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
		return "Player [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
				+ age + ", nationality=" + nationality + ", height=" + height + ", weight=" + weight + ", playerPhoto="
				+ playerPhoto + ", position=" + position + ", bDay=" + bDay + ", team=" + team + "]";
		}
		else {
			return "Player [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
					+ age + ", nationality=" + nationality + ", height=" + height + ", weight=" + weight + ", playerPhoto="
					+ playerPhoto + ", position=" + position + ", bDay=" + bDay + "]";
		}
	}

	
	
	
}
