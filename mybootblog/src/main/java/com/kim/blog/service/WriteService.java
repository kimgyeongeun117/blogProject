package com.kim.blog.service;

import java.util.ArrayList;

import com.kim.blog.dao.WriteDAO;
import com.kim.blog.dto.CategoryDTO;

public class WriteService {
	WriteDAO dao;
	
	public WriteService() {
		dao = new WriteDAO();
	}
	
	public ArrayList<CategoryDTO> selectCategory(){
		ArrayList<CategoryDTO> list = dao.select();
		
		return list;
	}
}
