package com.kim.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kim.blog.dto.BoardDTO;
import com.kim.blog.utils.DBHelper;


public class BoardDAO implements IBoardDAO{
	
	private Connection conn;
	private DBHelper dbHelper;
	
	public BoardDAO() {
		dbHelper = new DBHelper();
		conn = dbHelper.getConnection();
	}

	@Override
	public ArrayList<BoardDTO> select() {
		ArrayList<BoardDTO> list = new ArrayList<>();
		
		String strQuery = "select * from board; ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String createdAt = rs.getString("createdAt");
				int category_id = rs.getInt("category_id");
				
				BoardDTO dto = new BoardDTO(id,user_id,title,description,category_id,createdAt);
				System.out.println(dto);
				list.add(dto);
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

	@Override
	public int insert(int user_id,String title, String description,int category_id) {
		int resultCount = 0;
		String queryStr = "INSERT into board(user_id,title,description,category_id) values (?,?,?,?); ";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setInt(1, user_id);
			pStmt.setString(2, title);
			pStmt.setString(3, description);
			pStmt.setInt(4, category_id);
			
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

	@Override
	public int update(int id, String title, String description,int category_id) {
		int resultRow = 0;
		String queryStr = "update board set title = ?, description = ? where id=? and category_id=?; ";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setString(1, title);
			pStmt.setString(2, description);
			pStmt.setInt(3, id);
			pStmt.setInt(4, category_id);
			
			resultRow = pStmt.executeUpdate();
			System.out.println("업데이트");
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

	@Override
	public int delete(int id) {
		int resultRow = 0;
		String queryStr = "delete from board where id = ?; ";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setInt(1, id);
			
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
