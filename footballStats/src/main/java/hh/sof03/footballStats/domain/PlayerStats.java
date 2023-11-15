package hh.sof03.footballStats.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	
	@OneToOne //One PlayerStats One Player
	@JoinColumn(name="playerId")
	private Player player;
	
	//Constructors
	public PlayerStats(int goals, int assists, int matches, Player player) {
		super();
		this.goals = goals;
		this.assists = assists;
		this.matches = matches;
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
			return "PlayerStats [id=" + id + ", goals=" + goals + ", assists=" + assists + 
					", matches=" + matches + ", player= " + this.getPlayer() + "]";
		}
		else {
			return "PlayerStats [id=" + id + ", goals=" + goals + ", assists=" + assists +
				", matches=" + matches + "]";
		}
	}
	
	
	
}
