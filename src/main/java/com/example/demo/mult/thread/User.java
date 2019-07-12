package com.example.demo.mult.thread;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {
	
	private Long userId;
	
	private String pwd;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public User setPwd(String pwd) {
		this.pwd = pwd;
		return this;
	}

	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", pwd=" + pwd + "]";
	}
	
	
	public static void main(String[] args) {
		//$2a$10$M/AilybYGtu71DWIs8ah1OyxhO18VxCinKs8065HcHmqltSf2RGLO
		PasswordEncoder ENCODER = new BCryptPasswordEncoder();
		System.out.println(ENCODER.encode("e10adc3949ba59abbe56e057f20f883e"));
		//System.out.println(ENCODER.matches("e10adc3949ba59abbe56e057f20f883e", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjI4OTM5NTUsInVzZXJfbmFtZSI6IjEyMDMyMjAiLCJqdGkiOiI3ODgzZTFmOS1hN2E4LTQ4ZTctYmQ0Ny1kOTYzYmNiODdkZWEiLCJjbGllbnRfaWQiOiJ4aF93ZWIiLCJzY29wZSI6WyJzZXJ2ZXIiXX0.s5W9YTY0heh4f25i4nSyKOqtkMn7Zco2pzBZS1T7uyA"));
	}
	
	
	
	

}
