package com.kim.blog.service;

import com.kim.blog.dao.LoginDAO;
import com.kim.blog.dto.UserDTO;

public class LoginService {

	LoginDAO dao;
	
	public LoginService() {
		dao = new LoginDAO();
	}
	
	public UserDTO selectUser(String email,String password) {
		UserDTO dto = dao.select(email, password);
		
		if(dto!=null) {
			return dto;
		}else {
			return null;
		}
	}
}
