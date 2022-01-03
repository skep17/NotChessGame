package pack.DAO;

import java.time.LocalDateTime;

public class User {
	
	private String username;
	private String email;
	private String password;
	private LocalDateTime registeredOn;
	private LocalDateTime unRegisteredOn;
	private boolean isActive;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(LocalDateTime registeredOn) {
		this.registeredOn = registeredOn;
	}

	public LocalDateTime getUnRegisteredOn() {
		return unRegisteredOn;
	}

	public void setUnRegisteredOn(LocalDateTime unRegisteredOn) {
		this.unRegisteredOn = unRegisteredOn;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public User(String username, String email, String password, LocalDateTime registeredOn,
			LocalDateTime unRegisteredOn, boolean isActive) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.registeredOn = registeredOn;
		this.unRegisteredOn = unRegisteredOn;
		this.isActive = isActive;
	}
}