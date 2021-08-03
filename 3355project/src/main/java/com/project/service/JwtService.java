package com.project.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.entity.Login;
import com.project.entity.SocialUsers;
import com.project.entity.SocialUsers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	// 로그인 성공시
	public String create(final Login login) {
		System.out.println("time:" +  expireMin);
		final JwtBuilder builder = Jwts.builder();
		
		//Header
		builder.setHeaderParam("typ", "JWT");
		
		//Payload
		builder.setSubject("loginToken")
		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
		.claim("Login",login);
		
		//암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		//직렬화
		final String jwt = builder.compact();
		System.out.println("토큰 발행: " + jwt);
		
		return jwt;
	}
	
	//오버로딩
		public String create(final SocialUsers login) {
			System.out.println("time:" +  expireMin);
			final JwtBuilder builder = Jwts.builder();
			
			//Header
			builder.setHeaderParam("typ", "JWT");
			
			//Payload
			builder.setSubject("loginToken")
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
			.claim("Login",login);
			
			//암호화
			builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
			
			//직렬화
			final String jwt = builder.compact();
			System.out.println("토큰 발행: " + jwt);
			
			return jwt;
		}
	
	//토큰점검
	public void checkValid(final String jwt) {
		System.out.println("토큰확인" + jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	//토큰분석
	public Map<String,Object> get(final String jwt){
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch(final Exception e) {
			System.out.println("오류발생" + e.getStackTrace());
			throw new RuntimeException();
		}
		System.out.println("토큰분석 : " + claims);
		
		return claims.getBody();
	}
	
	
	
}
