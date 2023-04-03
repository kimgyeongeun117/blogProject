package com.kim.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kim.blog.dao.ReplyDAO;
import com.kim.blog.dao.UserDAO;

@WebServlet("/ReplyController")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyController() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int responseCount = 0;
		
		ReplyDAO dao = new ReplyDAO();
		UserDAO userdao = new UserDAO();
		
		String action = request.getParameter("action");
		int user_id = (int)session.getAttribute("user_id");
		String content = request.getParameter("content");
		String board_id = request.getParameter("board_id");
		String userName = userdao.selectbyuserid(user_id);
		
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("board_id", board_id);
		request.setAttribute("action", "redo");
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher = request.getRequestDispatcher("PostController");
		
		if(action.equals("insert")) {
			responseCount = dao.insert(Integer.parseInt(board_id),userName,content,user_id);
			System.out.println(responseCount);
			if(responseCount!=0) {
				dispatcher.forward(request, response);
			}else {
				out.print("<script>alert('댓글 작성에 실패했습니다'); location.href='PostController'</script>");
			}
		}else if(action.equals("delete")) {
			String reply_id = request.getParameter("reply_id");
			responseCount = dao.delete(Integer.parseInt(reply_id), user_id);
			System.out.println(responseCount);
			if(responseCount!=0) {
				dispatcher.forward(request, response);
			}else {
				out.print("<script>alert('댓글 삭제에 실패 했습니다'); location.href='PostController'</script>");
			}
		}
		
		
	}

}
