package hh.sof03.footballStats.domain;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignUpForm {
	
    @NotEmpty
    @Size(min=5, max=30, message="Username size must be between 5-30")
    private String username;

    @NotEmpty
    @Size(min=7, max=30, message="Password size must between 7-30")
    private String password;

    @NotEmpty
    @Size(min=7, max=30, message="Password size must between 7-30")
    private String passwordCheck;
    
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty
    private String role = "USER";

    //Getters & Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    
    
}

