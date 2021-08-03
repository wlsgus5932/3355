package com.project.service;

import com.project.entity.Login;
import com.project.entity.Users;

public interface AccountService {
	
	public void accountReg(Users users);
	public String checkEmail(String userEmailAddr);
	public int checkUserId(String userId);
	public int userLogin(Login login);
	public void addSocialUser(String userId, String userEmail, String social);
	public int viewSocialUser(String userId, String userEmail, String social);
	public String viewUserEmail(String userId);
	public void setPw(Login login);
}
