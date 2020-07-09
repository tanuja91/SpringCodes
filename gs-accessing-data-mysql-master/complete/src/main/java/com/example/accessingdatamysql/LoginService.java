package com.example.accessingdatamysql;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

	public boolean validateUser(String userid, String password) {
		System.out.println("Reached o Login Service");
		return userid.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin");
	}
	
	
}
