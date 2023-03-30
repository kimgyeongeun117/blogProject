package com.kim.blog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostController() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title"); 
		String description = request.getParameter("description");
		String user_id = request.getParameter("user_id");
		String category_id = request.getParameter("category_id");
		String board_id = request.getParameter("board_id");
		String createdAt = request.getParameter("createdAt");
		
		
		request.setAttribute("title", title);
		request.setAttribute("description", description);
		request.setAttribute("board_id", board_id);
		request.setAttribute("user_id", user_id);
		request.setAttribute("category_id", category_id);
		request.setAttribute("createdAt", createdAt);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
