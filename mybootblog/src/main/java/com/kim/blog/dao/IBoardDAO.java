package com.kim.blog.dao;

import java.util.ArrayList;

import com.kim.blog.dto.BoardDTO;


public interface IBoardDAO {
	
	ArrayList<BoardDTO> select();
	int insert(int user_id, String title, String description,int category_id);
	int update(int id, String title, String description,int category_id);
	int delete(int id);
}
