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

@WebServlet("/boardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
	}

	// delete
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		BoardDAO dao = new BoardDAO();

		String board_id = request.getParameter("board_id");
		String action = request.getParameter("action");
		int user_id = (int) session.getAttribute("user_id");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(action.equals("delete")) {
			int resultCount = dao.delete(Integer.parseInt(board_id), user_id);
			
			if (resultCount != 0) {
				out.print("<script>alert('삭제에 성공 했습니다'); location.href='indexController'</script>");
			} else {
				out.print("<script>alert('삭제 실패 했습니다'); location.href='indexController'</script>");
			}
		}
	}

	// post, put
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("user_id");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String action = request.getParameter("action");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		BoardDAO boardDao = new BoardDAO();
		PrintWriter out = response.getWriter();

		if (action.equals("insert")) {
			int responseCount = 0;
			UserDAO userDao = new UserDAO();
			
			String userName = userDao.selectbyuserid(user_id);
			responseCount = boardDao.insert(userName, user_id, title, description, category_id);
			
			if (responseCount != 0) {
				response.sendRedirect("indexController");
			} else {
				out.print("<script>alert('글 작성에 실패했습니다'); location.href='writeController'</script>");
			}
		} else if (action.equals("update")) {
			int board_id = Integer.parseInt(request.getParameter("board_id"));
			int responseCount = boardDao.update(board_id,user_id, title, description, category_id);
			
			if(responseCount!=0) {
				response.sendRedirect("indexController");
			}else {
				out.print("<script>alert('글 수정에 실패했습니다'); location.href='indexController'</script>");
			}
		}
	}

}
