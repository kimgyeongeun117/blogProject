package com.kim.blog.dao;

import java.util.ArrayList;

import com.kim.blog.dto.BoardDTO;


public interface IBoardDAO {
	
	ArrayList<BoardDTO> select();
	ArrayList<BoardDTO> selectbysearch(String title);
	ArrayList<BoardDTO> limitSelect(int page);
	BoardDTO selectbyuser(int user_id,int id);
	int insert(String userName, int user_id,String title, String description,int category_id);
	int update(int id,int user_id, String title, String description,int category_id);
	int delete(int id,int user_id);
}
