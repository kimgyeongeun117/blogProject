package com.kim.blog.dto;

public class BoardDTO {

	private int id;
	private String userName;
	private int user_id;
	private String title;
	private String description;
	private int category_id;
	private String createdAt;
	private int views;

	public BoardDTO(int id, String userName, int user_id, String title, String description, int category_id,
			String createdAt,int views) {
		this.id = id;
		this.userName = userName;
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.category_id = category_id;
		this.createdAt = createdAt;
		this.views = views;
	}
	
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [id=" + id + ", user_id=" + user_id + ", title=" + title + ", description=" + description
				+ ", category_id=" + category_id + "]";
	}
}
