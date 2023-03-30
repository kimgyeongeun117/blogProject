package com.kim.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kim.blog.dto.UserDTO;
import com.kim.blog.utils.DBHelper;

public class LoginDAO {

	private Connection conn;
	private DBHelper dbHelper;
	
	public LoginDAO() {
		dbHelper = new DBHelper();
		conn = dbHelper.getConnection();
	}
	
	public UserDTO select(String email,String password) {
		UserDTO result = null;
		
		String strQuery = "select * from user where email=? and password=?  ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			
			rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String userEmail = rs.getString("email");
				String userPassword = rs.getString("password");
				String phoneNumber = rs.getString("phoneNumber");
				String createdAt = rs.getString("createdAt");
				
				UserDTO dto = new UserDTO(id,name,userEmail,userPassword,phoneNumber,createdAt);
				result = dto;
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
		
		return result;
	}
	
}
