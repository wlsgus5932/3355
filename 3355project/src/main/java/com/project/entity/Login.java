package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Login {
	
	private String userId;
	private String userPw;
	
	
	
	public Login(String userId, String userPw) {
		this.userId = userId;
		this.userPw = userPw;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getUserPw() {
		return userPw;
	}
	
}
