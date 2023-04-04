package com.kim.blog.dao;

import com.kim.blog.dto.UserDTO;

public interface IUserDAO {
	
	public int insert(String name,String email, String password,String phoneNumber);
	public UserDTO select(String name,String email);
	public String selectbyuserid(int id);
}
