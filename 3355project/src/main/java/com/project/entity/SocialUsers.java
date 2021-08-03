package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class SocialUsers {

	private String userId;
	private String userEmail;
	
	public String getUserId() {
		return userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	
	@Override
	public String toString() {
		return "SocialUsers [userId=" + userId + ", userEmail=" + userEmail + "]";
	}
	
	
}
