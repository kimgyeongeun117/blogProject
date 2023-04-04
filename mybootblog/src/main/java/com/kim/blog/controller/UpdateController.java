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
import com.kim.blog.dao.WriteDAO;
import com.kim.blog.dto.BoardDTO;
import com.kim.blog.dto.CategoryDTO;
import com.kim.blog.service.UpdateService;

@WebServlet("/updateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		UpdateService updateService = new UpdateService();

		int user_id = (int)session.getAttribute("user_id");
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		BoardDTO board = updateService.selectbyuser(user_id, board_id);
		ArrayList<CategoryDTO> categoryList = updateService.replySelect();
		
		request.setAttribute("board", board);
		request.setAttribute("categoryList",categoryList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
