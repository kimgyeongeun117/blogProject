package com.kim.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kim.blog.dto.ReplyDTO;
import com.kim.blog.utils.DBHelper;

public class ReplyDAO implements IReplyDAO{
	
	private Connection conn;
	private DBHelper dbHelper;
	
	public ReplyDAO() {
		dbHelper = new DBHelper();
		conn = dbHelper.getConnection();
	}
	
	public int insert(int board_id,String userName, String content,int user_id) {
		int resultCount = 0;
		String queryStr = "INSERT INTO reply(board_id,userName,content,user_id) VALUES (?,?,?,?); ";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setInt(1, board_id);
			pStmt.setString(2, userName);
			pStmt.setString(3, content);
			pStmt.setInt(4, user_id);
			
			resultCount = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			try {
				pStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return resultCount;
	}
	
	public ArrayList<ReplyDTO> select(int board_id) {
		ArrayList<ReplyDTO> list = new ArrayList<>();
		
		String strQuery = "SELECT * FROM reply WHERE board_id = ?; ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			pStmt.setInt(1, board_id);
			
			rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int inboard_id = rs.getInt("board_id");
				String userName = rs.getString("userName");
				String content = rs.getString("content");
				String createdAt = rs.getString("createdAt");
				
				ReplyDTO inDto = new ReplyDTO(id,user_id,inboard_id,userName,content,createdAt);
				list.add(inDto);
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
		
		return list;
	}
	
	public int delete(int id,int user_id) {
		int resultRow = 0;
		String queryStr = "DELETE FROM reply WHERE id = ? AND user_id = ?; ";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setInt(1, id);
			pStmt.setInt(2, user_id);
			
			resultRow =  pStmt.executeUpdate();
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
