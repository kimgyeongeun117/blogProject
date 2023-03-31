package com.kim.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kim.blog.dao.BoardDAO;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("~~~~~~~~~");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		BoardDAO dao = new BoardDAO();
		String board_id =  request.getParameter("board_id");
		int user_id = (int)session.getAttribute("user_id");
		System.out.println(board_id+","+user_id);
		
		int resultCount = dao.delete(Integer.parseInt(board_id) ,user_id);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(resultCount!=0) {
			out.print("<script>alert('삭제에 성공 했습니다'); location.href='IndexController'</script>");
		}else {
			out.print("<script>alert('삭제 실패 했습니다'); location.href='IndexController'</script>");
		}
	}

}
