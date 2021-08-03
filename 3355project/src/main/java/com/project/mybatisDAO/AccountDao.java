package com.project.mybatisDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.entity.Users;

@Mapper
public interface AccountDao {
	public List<Users> userList();
	public int viewUser(String userId, String social);
	public void addUser(String userId, String userPw, String userEmail, String social);
	public void deleteUser(String userId);
	public int loginUser(String userId, String userPw);
	public void addSocialUser(String userId, String userEmail, String social);
	public int viewSocialUser(String userId, String userEmail, String social);
	public String viewUserEmail(String userId);
	public void setUserPw(String userId, String userPw);
}
