package hh.sof03.footballStats.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class TeamStats {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@PositiveOrZero(message="Wins cannot be negative")
	private int wins;
	
	@PositiveOrZero(message="Draws cannot be negative")
	private int draws;
	
	@PositiveOrZero(message="Losses cannot be negative")
	private int losses;
	
	@OneToOne //One TeamStats One Team
	@JoinColumn(name="teamId")
	private Team team;
	
	//Constructors
	public TeamStats(int wins, int draws, int losses, Team team) {
		super();
		this.wins = wins;
		this.draws = draws;
		this.losses = losses;
		this.team = team;
	} 
	
	public TeamStats() {
		
	}

	//Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	//toString
	@Override
	public String toString() {
		if(this.getTeam() != null) {
			return "TeamStats [id=" + id + ", wins=" + wins + ", draws=" + draws +
					", losses=" + losses + ", team= " + this.getTeam() + "]";
		}
		else {
			return "TeamStats [id=" + id + ", wins=" + wins + ", draws=" + draws +
				", losses=" + losses + "]";
		}
	}
	
	
	
	
}
