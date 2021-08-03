package com.project.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Getter
@ToString
public class Users {
	private String userId;
	private String userPw;
	private String userEmail;
	private String mailAddr;
	
	
	
	public String getUserId() {
		return userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public String getUserEmail() {
		return userEmail;
	}
	
	public Users() {}

	public Users(String userId, String userPw, String userEmail, String mailAddr) {
		this.userId = userId;
		this.userPw = userPw;
		this.userEmail = userEmail + "@" + mailAddr;
	}
	
}
