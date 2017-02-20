package ulink.constructor;

public class User {
	private String email;
	//private String username;
	private String password;
	private String roles;
	
	
	
	public User(String email, String password, String roles) {
		super();
		//this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
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
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
