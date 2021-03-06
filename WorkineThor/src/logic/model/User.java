package logic.model;

public class User {

	private String username= null;
	private String password = null; 
	private boolean logged = false;
	
	public User() {
		this("","");
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.logged = false;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	public void setUsername(String newUsername){
		this.username = newUsername;
	}
	
	public void setPassword(String newPassword){
		this.password = newPassword;
	}
	
	public void setLoggedIn() {
		this.logged = true; 
	}
	
	public void setLoggedOut() {
		this.logged = false;
	}
	
	public boolean getLoggedState() {
		return this.logged;
	}
}