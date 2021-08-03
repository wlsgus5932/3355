package com.project.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.entity.Login;
import com.project.entity.Users;
import com.project.mybatisDAO.AccountDao;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	AccountDao dao;
	
	@Override
	public void accountReg(Users users) {
		dao.addUser(users.getUserId(),users.getUserPw(),users.getUserEmail(), "N");
	}
	
	
	
	@Override
	public String checkEmail(String userEmailAddr) {
		Random random = new Random();
		String key="";
		
		for(int i=0; i<3; i++) {
			int index = random.nextInt(25)+65;
			key+=(char)index;
		}
		int numIndex = random.nextInt(9999)+1000;
		
		key += numIndex;
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg,true,"UTF-8");
			messageHelper.setSubject("3355 인증번호");
	        messageHelper.setText("인증번호 : " + key);
	        messageHelper.setTo(userEmailAddr);
            msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(userEmailAddr));
            javaMailSender.send(msg);
	        
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		/*
		 * SimpleMailMessage message = new SimpleMailMessage();
		 * message.setTo(userEmailAddr);
		 * 
		 * 
		 * 
		 * message.setSubject("3355 인증번호"); message.setText("인증번호 : " + key);
		 * javaMailSender.send(message);
		 */
		
		return key;
	}

	
	
	
	
	@Override
	public String viewUserEmail(String userId) {
		return dao.viewUserEmail(userId);
	}



	@Override
	public int checkUserId(String userId) {
		int cnt = dao.viewUser(userId, "N");
		System.out.println(cnt);
		return cnt;
	}
	
	@Override
	public void setPw(Login login) {
		dao.setUserPw(login.getUserId(), login.getUserPw());
	}



	@Override
	public int userLogin(Login login) {
		int result = dao.loginUser(login.getUserId(), login.getUserPw());
		return result;
	}



	@Override
	public void addSocialUser(String userId, String userEmail, String social) {
		dao.addSocialUser(userId, userEmail, social);
	}



	@Override
	public int viewSocialUser(String userId, String userEmail, String social) {
		return dao.viewSocialUser(userId, userEmail, social);
	}
	
	
	
	
	

}
