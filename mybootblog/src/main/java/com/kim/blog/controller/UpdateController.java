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

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		BoardDAO dao = new BoardDAO();
		WriteDAO categoryDao = new WriteDAO();
		int user_id = (int)session.getAttribute("user_id");
		
		String action = request.getParameter("action");
		if(action.equals("updatebefore")) {
			int board_id = Integer.parseInt(request.getParameter("board_id"));
			
			BoardDTO board = dao.selectbyuser(user_id, board_id);
			ArrayList<CategoryDTO> categoryList = categoryDao.select();
			
			request.setAttribute("board", board);
			request.setAttribute("categoryList",categoryList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("update")) {
			request.setCharacterEncoding("UTF-8");
			int board_id = Integer.parseInt(request.getParameter("board_id"));
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int category_id = Integer.parseInt(request.getParameter("category_id"));
			System.out.println(user_id+","+category_id+","+title+","+description);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			int responseCount = dao.update(board_id,user_id, title, description, category_id);
			System.out.println(responseCount);
			if(responseCount!=0) {
				response.sendRedirect("IndexController");
			}else {
				out.print("<script>alert('글 수정에 실패했습니다'); location.href='IndexController'</script>");
			}
		}
		
	}

}
