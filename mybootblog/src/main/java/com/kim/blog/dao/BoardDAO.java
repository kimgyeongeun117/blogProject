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
	
	//검색창 조회
		@Override
		public ArrayList<BoardDTO> selectbysearch(String title) {
			ArrayList<BoardDTO> list = new ArrayList<>();
			
			String strQuery = "SELECT * FROM board WHERE title LIKE ? ";
			PreparedStatement pStmt = null;
			ResultSet rs = null;
			
			try {
				pStmt = conn.prepareStatement(strQuery);
				pStmt.setString(1, "%"+title+"%");
				
				rs = pStmt.executeQuery();
				
				while(rs.next()) {
					String userName = rs.getString("userName");
					int id = rs.getInt("id");
					int user_id = rs.getInt("user_id");
					String intitle = rs.getString("title");
					String description = rs.getString("description");
					String createdAt = rs.getString("createdAt");
					int category_id = rs.getInt("category_id");
					int views = rs.getInt("views");
					
					BoardDTO dto = new BoardDTO(id,userName,user_id,intitle,description,category_id,createdAt,views);
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
	
	
	//유저 id와 게시글 id로 글정보 받기
	@Override
	public BoardDTO selectbyuser(int user_id,int id) {
		BoardDTO dto = null;
		
		String strQuery = "SELECT * FROM board WHERE user_id = ? AND id = ?; ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			pStmt.setInt(1, user_id);
			pStmt.setInt(2, id);
			
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
	
	public ArrayList<BoardDTO> limitSelect(int page) {
		ArrayList<BoardDTO> list = new ArrayList<>();
		
		String strQuery = "SELECT * FROM board ORDER BY id LIMIT ?, 5; ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			pStmt.setInt(1, page);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("userName");
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String createdAt = rs.getString("createdAt");
				int category_id = rs.getInt("category_id");
				int views = rs.getInt("views");
				
				BoardDTO dto = new BoardDTO(id,userName,user_id,title,description,category_id,createdAt,views);
				
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
	
	
	// 전체 조회
	@Override
	public ArrayList<BoardDTO> select() {
		ArrayList<BoardDTO> list = new ArrayList<>();
		
		String strQuery = "SELECT * FROM board; ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("userName");
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String createdAt = rs.getString("createdAt");
				int category_id = rs.getInt("category_id");
				int views = rs.getInt("views");
				
				BoardDTO dto = new BoardDTO(id,userName,user_id,title,description,category_id,createdAt,views);
				
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
	public int insert(String userName, int user_id,String title, String description,int category_id) {
		int resultCount = 0;
		String queryStr = "INSERT INTO board(userName, user_id,title,description,category_id) VALUES (?,?,?,?,?); ";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setString(1, userName);
			pStmt.setInt(2, user_id);
			pStmt.setString(3, title);
			pStmt.setString(4, description);
			pStmt.setInt(5, category_id);
			
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
	public int update(int id,int user_id, String title, String description,int category_id) {
		int resultRow = 0;
		String queryStr = "UPDATE board SET title = ?, description = ?, category_id = ? "
				+ " WHERE id=? AND user_id=? ";
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setString(1, title);
			pStmt.setString(2, description);
			pStmt.setInt(3, category_id);
			pStmt.setInt(4, id);
			pStmt.setInt(5, user_id);
			
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

	@Override
	public int delete(int id,int user_id) {
		int resultRow = 0;
		String queryStr = "DELETE FROM board WHERE id = ? AND user_id = ?; ";
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
