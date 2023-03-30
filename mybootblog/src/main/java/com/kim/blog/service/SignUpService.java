package com.kim.blog.service;

import com.kim.blog.dao.SignUpDAO;
import com.kim.blog.dto.UserDTO;

public class SignUpService {
	
	SignUpDAO dao;

	public SignUpService() {
		dao = new SignUpDAO();
	}
	
	public int insertUser(String name,String email,String password,String phoneNumber){
		int resultCount = 0;
		
		// 아이디 생성 제약 걸기
		if(email.contains("@")&&email.contains(".")) {
			resultCount = dao.insert(name,email,password,phoneNumber);
		}
		
		return resultCount;
	}
	
	public UserDTO selectUser(String name,String email) {
		
		UserDTO dto = dao.select(name, email);
		
		if(dto!=null) {
			return dto;
		}else {
			return null;
		}
	}
	
}
