package com.kim.blog.dao;

import java.util.ArrayList;

import com.kim.blog.dto.ReplyDTO;

public interface IReplyDAO {

	public int insert(int board_id,String userName, String content,int user_id);
	public ArrayList<ReplyDTO> select(int board_id);
	public int delete(int id,int user_id);
}
