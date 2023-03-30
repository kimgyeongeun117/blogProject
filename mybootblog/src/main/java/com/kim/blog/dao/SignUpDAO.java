package com.kim.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kim.blog.dto.UserDTO;
import com.kim.blog.utils.DBHelper;

public class SignUpDAO {
	
	private Connection conn;
	private DBHelper dbHelper;
	
	public SignUpDAO() {
		dbHelper = new DBHelper();
		conn = dbHelper.getConnection();
	}
	
	public int insert(String name,String email, String password,String phoneNumber) {
		int resultCount = 0;
		String queryStr = "INSERT into user(name,email,password,phoneNumber) values (?,?,?,?); ";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(queryStr);
			pStmt.setString(1, name);
			pStmt.setString(2, email);
			pStmt.setString(3, password);
			pStmt.setString(4, phoneNumber);
			
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
	
	public UserDTO select(String name,String email) {
		UserDTO result = null;
		
		String strQuery = "select * from user where name=? and email=?  ";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(strQuery);
			pStmt.setString(1, name);
			pStmt.setString(2, email);
			
			rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("name");
				String userEmail = rs.getString("email");
				String password = rs.getString("password");
				String phoneNumber = rs.getString("phoneNumber");
				String createdAt = rs.getString("createdAt");
				
				UserDTO dto = new UserDTO(id,userName,userEmail,password,phoneNumber,createdAt);
				result = dto;
				System.out.println(result);
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
