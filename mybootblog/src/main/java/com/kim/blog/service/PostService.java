package com.kim.blog.service;

import java.util.ArrayList;

import com.kim.blog.dao.PostDAO;
import com.kim.blog.dao.ReplyDAO;
import com.kim.blog.dto.BoardDTO;
import com.kim.blog.dto.ReplyDTO;

public class PostService {
	PostDAO postDao;
	ReplyDAO replyDao;
	
	public PostService() {
		postDao = new PostDAO();
		replyDao = new ReplyDAO();
	}
	
	public BoardDTO select(int id) {
		BoardDTO dto = postDao.select(id);
		
		if (dto != null) {
			return dto;
		} else {
			return null;
		}
	}
	
	public int update(int id) {
		int resultCount = postDao.update(id);
		
		return resultCount;
	}
	
	public ArrayList<ReplyDTO> replyselect(int board_id){
		ArrayList<ReplyDTO> list = replyDao.select(board_id);
		
		if (list != null) {
			return list;
		} else {
			return null;
		}
	}
}
