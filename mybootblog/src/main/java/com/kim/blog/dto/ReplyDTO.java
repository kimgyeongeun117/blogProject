package com.kim.blog.dto;

public class ReplyDTO {
	
	private int id;
	private int user_id;
	private int board_id;
	private String userName;
	private String content;
	private String createdAt;
	
	public ReplyDTO(int id,int user_id, int board_id, String userName, String content, String createdAt) {
		this.id = id;
		this.board_id = board_id;
		this.userName = userName;
		this.content = content;
		this.createdAt = createdAt;
		this.user_id = user_id;
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
