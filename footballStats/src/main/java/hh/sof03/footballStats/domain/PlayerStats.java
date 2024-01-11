package hh.sof03.footballStats.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class PlayerStats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@PositiveOrZero(message="Goals cannot be negative")
	private int goals; 
	
	@PositiveOrZero(message="Assists cannot be negative")
	private int assists;
	
	@PositiveOrZero(message="Matches cannot be negative")
	private int matches;
	
	@PositiveOrZero(message="Minutes cannot be negative")
	private int minutes;
	
	@DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "Rating must be at most 10")
	private double rating;
	
	@PositiveOrZero(message="Shots cannot be negative")
	private int shots;
	
	@PositiveOrZero(message="Shots on taget cannot be negative")
	private int shotsOnTarget;
	
	@PositiveOrZero(message="Saves cannot be negative")
	private int saves;
	
	@PositiveOrZero(message="Yellows cannot be negative")
	private int yellows;
	
	@PositiveOrZero(message="Reds cannot be negative")
	private int reds;
	
	@OneToOne 
	@JoinColumn(name="playerId")
	private Player player;
	
	//Constructors
	public PlayerStats(int goals, int assists, int matches, int minutes, double rating, int shots,
			int shotsOnTarget, int saves, int yellows, int reds, Player player) {
		super();
		this.goals = goals;
		this.assists = assists;
		this.matches = matches;
		this.minutes = minutes;
		this.rating = rating;
		this.shots = shots;
		this.shotsOnTarget = shotsOnTarget;
		this.saves = saves;
		this.yellows = yellows;
		this.reds = reds;
		this.player = player;
	}
	
	public PlayerStats() {
		
	}
	//Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public int getShotsOnTarget() {
		return shotsOnTarget;
	}

	public void setShotsOnTarget(int shotsOnTarget) {
		this.shotsOnTarget = shotsOnTarget;
	}

	public int getSaves() {
		return saves;
	}

	public void setSaves(int saves) {
		this.saves = saves;
	}

	public int getYellows() {
		return yellows;
	}

	public void setYellows(int yellows) {
		this.yellows = yellows;
	}

	public int getReds() {
		return reds;
	}

	public void setReds(int reds) {
		this.reds = reds;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	//toString
	@Override
	public String toString() {
		if(this.getPlayer()!= null) {
		return "PlayerStats [id=" + id + ", goals=" + goals + ", assists=" + assists + ", matches=" + matches
				+ ", minutes=" + minutes + ", rating=" + rating + ", shots=" + shots + ", shotsOnTarget="
				+ shotsOnTarget + ", saves=" + saves + ", yellows=" + yellows + ", reds=" + reds + ", player=" + player + "]";
		}
		else {
			return "PlayerStats [id=" + id + ", goals=" + goals + ", assists=" + assists + ", matches=" + matches
					+ ", minutes=" + minutes + ", rating=" + rating + ", shots=" + shots + ", shotsOnTarget="
					+ shotsOnTarget + ", saves=" + saves + ", yellows=" + yellows + ", reds=" + reds + "]";
		}
	}
	
	

	
	
	
	
	
}
