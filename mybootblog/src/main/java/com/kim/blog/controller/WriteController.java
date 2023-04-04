package com.kim.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kim.blog.dao.BoardDAO;
import com.kim.blog.dao.UserDAO;
import com.kim.blog.dto.BoardDTO;
import com.kim.blog.dto.CategoryDTO;
import com.kim.blog.service.WriteService;


@WebServlet("/writeController")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public WriteController() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriteService writeService = new WriteService();
		
		ArrayList<CategoryDTO> list = writeService.selectCategory();
		request.setAttribute("list",list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
