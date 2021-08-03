package com.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Login;
import com.project.entity.SocialUsers;
import com.project.entity.Users;
import com.project.service.AccountService;
import com.project.service.JwtService;
import com.project.social.service.NaverAPI;

@RestController
@RequestMapping("/api/user")
public class AccountController {
	
	@Autowired
	private NaverAPI naver;
	
	@Autowired
	private AccountService service;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/accountReg")
	public void accountReg(@RequestBody Users users) {
		System.out.println(users);
		service.accountReg(users);
	}
	
	@GetMapping("/checkEmail")
	public String checkEmail(String userEmailAddr) {
		System.out.println(userEmailAddr);
		return service.checkEmail(userEmailAddr);
	}
	
	@GetMapping("/sendEmail")
	public String sendEmail(String userId) {
		System.out.println(userId);
		return service.checkEmail(service.viewUserEmail(userId));
	}
	
	@GetMapping("/checkUserId")
	public int checkUserId(String userId) {
		System.out.println(userId);
		return service.checkUserId(userId);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> userLogin(@RequestBody Login login, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(login);
		int result =  service.userLogin(login);
		String token;
		
		if(result==1) {
			token = jwtService.create(login);
			res.setHeader("jwt-auth-token", token);
			resultMap.put("status", true);
			resultMap.put("data", login);
			
			status = HttpStatus.ACCEPTED;
		} else {
			System.out.println("로그인 실패");
			resultMap.put("message", "로그인실패");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@PostMapping("/{social}")
	public ResponseEntity<Map<String, Object>> socialLogin(@PathVariable("social") String social, @RequestBody SocialUsers user, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		if(social.equals("kakao")) System.out.println("카카오 로그인 접근됨"); 
		else if(social.equals("naver"))System.out.println("네이버 로그인 접근됨");
		else if(social.equals("google"))System.out.println("구글 로그인 접근됨");
		
		System.out.println(user);
		int result =  service.viewSocialUser(user.getUserId(), user.getUserEmail(), social);
		String token;
		
		if(result==1) {
			token = jwtService.create(user);
			res.setHeader("jwt-auth-token", token);
			resultMap.put("status", true);
			resultMap.put("data", user);
			
			status = HttpStatus.ACCEPTED;
		} else {
			service.addSocialUser(user.getUserId(), user.getUserEmail(), social);
			token = jwtService.create(user);
			res.setHeader("jwt-auth-token", token);
			resultMap.put("status", true);
			resultMap.put("data", user);
			
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/naver/service")
	public String naverService(HttpServletRequest req) {
		String token = req.getHeader("Authorization");
		System.out.println(token);
		String url = "https://openapi.naver.com/v1/nid/me";
		
		Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", token);
        String responseBody = naver.get(url,requestHeaders);
		
        System.out.println(responseBody);
        
        return responseBody;
		
	}
	
	@PostMapping("/setPw")
	public void setPw(@RequestBody Login login) {
		service.setPw(login);
	}
	
}
