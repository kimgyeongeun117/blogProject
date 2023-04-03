package com.kim.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kim.blog.dto.BoardDTO;
import com.kim.blog.utils.DBHelper;

public class PostDAO {
	
	private Connection conn;
	private DBHelper dbHelper;
	
	public PostDAO() {
		dbHelper = new DBHelper();
		conn = dbHelper.getConnection();
	}
	
	public BoardDTO select(int id) {
		BoardDTO dto = null;
		
		String strQuery = "select * from board where id = ?; ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			pStmt.setInt(1, id);
			
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("userName");
				int inId = rs.getInt("id");
				int inUser_id = rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String createdAt = rs.getString("createdAt");
				int category_id = rs.getInt("category_id");
				int views = rs.getInt("views");
				
				BoardDTO inDto = new BoardDTO(inId,userName,inUser_id,title,description,category_id,createdAt,views);
				dto = inDto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public int update(int id) {
		int resultRow = 0;
		String queryStr = "update board set views = views + 1  "
				+ " where id=? ";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setInt(1, id);
			
			resultRow = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultRow;
	}
	
}
