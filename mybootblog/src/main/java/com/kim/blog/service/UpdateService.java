package com.kim.blog.service;

import java.util.ArrayList;

import com.kim.blog.dao.BoardDAO;
import com.kim.blog.dao.WriteDAO;
import com.kim.blog.dto.BoardDTO;
import com.kim.blog.dto.CategoryDTO;

public class UpdateService {
	BoardDAO boardDao;
	WriteDAO writeDao;

	public UpdateService() {
		boardDao = new BoardDAO();
		writeDao = new WriteDAO();
	}

	public BoardDTO selectbyuser(int user_id, int id) {
		BoardDTO dto = boardDao.selectbyuser(user_id,id);

		if (dto != null) {
			return dto;
		} else {
			return null;
		}
	}
	
	public ArrayList<CategoryDTO> replySelect(){
		ArrayList<CategoryDTO> list = writeDao.select();
		
		if (list != null) {
			return list;
		} else {
			return null;
		}
	}

}
