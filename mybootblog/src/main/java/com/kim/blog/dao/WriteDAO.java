package com.kim.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kim.blog.dto.CategoryDTO;
import com.kim.blog.utils.DBHelper;

public class WriteDAO {
	
	private Connection conn;
	private DBHelper dbHelper;
	
	public WriteDAO() {
		dbHelper = new DBHelper();
		conn = dbHelper.getConnection();
	}
	
	public ArrayList<CategoryDTO> select() {
		ArrayList<CategoryDTO> list = new ArrayList<>();
		String strQuery = "select * from category; ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				CategoryDTO dto = new CategoryDTO(id, name);
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
	
}
