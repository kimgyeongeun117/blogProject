package com.kim.blog.dto;

public class BoardDTO {

	private int id;
	private int user_id;
	private String title;
	private String description;
	private int category_id;
	private String createdAt;
	
	public BoardDTO(int id, int user_id, String title, String description, int category_id,String createdAt) {
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.description = description;
		this.category_id = category_id;
		this.createdAt = createdAt;
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
