package hh.sof03.footballStats.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

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
	
	@PositiveOrZero
	private int points;
	
	@Positive
	private int rank;
	
	@PositiveOrZero
	private int goalsFor;
	
	@PositiveOrZero
	private int goalsAgainst;
	
	@NotNull
	private int goalDiff;
	
	@Size(min=0, max=5, message="Size must be between 0-5")
	private String form;
	
	@OneToOne
	@JoinColumn(name="teamId")
	private Team team;
	
	//Constructors
	public TeamStats(int wins, int draws, int losses, int points, int rank, int goalsFor,
			int goalsAgainst, int goalDiff, String form, Team team) {
		super();
		this.wins = wins;
		this.draws = draws;
		this.losses = losses;
		this.points = points;
		this.points = points;
		this.rank = rank;
		this.goalsFor = goalsFor;
		this.goalsAgainst = goalsAgainst;
		this.goalDiff = goalDiff;
		this.form = form;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getGoalDiff() {
		return goalDiff;
	}

	public void setGoalDiff(int goalDiff) {
		this.goalDiff = goalDiff;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
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
			return "TeamStats [id=" + id + ", wins=" + wins + ", draws=" + draws + ", losses=" + losses + ", points="
					+ points + ", rank=" + rank + ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst
					+ ", goalDiff=" + goalDiff + ", form=" + form + "]";
		}
		else {
		return "TeamStats [id=" + id + ", wins=" + wins + ", draws=" + draws + ", losses=" + losses + ", points="
				+ points + ", rank=" + rank + ", goalsFor=" + goalsFor + ", goalsAgainst=" + goalsAgainst
				+ ", goalDiff=" + goalDiff + ", form=" + form + ", team=" + team + "]";
		}
	}		
}
