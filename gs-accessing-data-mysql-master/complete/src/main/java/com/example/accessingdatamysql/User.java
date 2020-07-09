package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "invoice")
public class User {
	private String name;
	private String email;
	private int CDU_ID;
	
	public User(int CDU_ID, String name, String email) {
		super();
		this.CDU_ID = CDU_ID;
		this.name = name;
		this.email = email;
	}
	
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	
    
   
	@Column(name = "Vendor_Name", nullable = false)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email_id", nullable = false)
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	@Id
	@Column(name = "CDU_ID", nullable = false)
	public int getCDU_ID() {
		return CDU_ID;
	}

	public void setCDU_ID(int CDU_ID) {
		this.CDU_ID = CDU_ID;
	}



	@Override
	public String toString() {
		return "User [name=" + name + ", CDU_ID=" + CDU_ID + ", email=" + email + ",]";
	}
}