package hh.sof03.footballStats.domain;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="users")
@RepositoryRestResource(exported = false)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@Column(name="password", nullable=false)
	private String passwordHash;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="role", nullable=false)
	private String role;
	
	@Column(name="username", nullable=false, unique=true)
	private String username;

	//Constructors
	public User() {	
		
	}

	public User(String passwordHash, String email, String role, String username) {
		super();
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
		this.username = username;
	}


	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

}

