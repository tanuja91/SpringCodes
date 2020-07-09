package com.example.accessingdatamysql;

public class Cred {

	String email;
	String password;
	public Cred(){
		super();
		// TODO Auto-generated constructor stub
	}
	public Cred(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Cred [email=" + email + ", password=" + password + "]";
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
}
