package com.kim.blog.service;

import java.util.ArrayList;

import com.kim.blog.dao.BoardDAO;
import com.kim.blog.dto.BoardDTO;

public class IndexService {
	BoardDAO dao;

	public IndexService() {
		dao = new BoardDAO();
	}

	public ArrayList<BoardDTO> selectbysearch(String title) {
		ArrayList<BoardDTO> dto = dao.selectbysearch(title);

		if (dto != null) {
			return dto;
		} else {
			return null;
		}
	}

	public ArrayList<BoardDTO> limitSelect(int page) {
		ArrayList<BoardDTO> dto = dao.limitSelect(page);

		if (dto != null) {
			return dto;
		} else {
			return null;
		}
	}
	
	public ArrayList<BoardDTO> select(){
		ArrayList<BoardDTO> dto = dao.select();
		if (dto != null) {
			return dto;
		} else {
			return null;
		}
	}
}
