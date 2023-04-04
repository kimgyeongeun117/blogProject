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

@WebServlet("/replyController")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyController() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ReplyDAO dao = new ReplyDAO();
		
		int responseCount = 0;
		String action = request.getParameter("action");
		int user_id = (int)session.getAttribute("user_id");
		String board_id = request.getParameter("board_id");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(action.equals("delete")) {
			String reply_id = request.getParameter("reply_id");
			responseCount = dao.delete(Integer.parseInt(reply_id), user_id);
			
			if(responseCount!=0) {
				response.sendRedirect("postController?action=null&board_id="+board_id);
			}else {
				out.print("<script>alert('댓글 삭제에 실패 했습니다'); location.href='postController'</script>");
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int responseCount = 0;
		
		ReplyDAO replyDao = new ReplyDAO();
		UserDAO userDao = new UserDAO();
		
		String action = request.getParameter("action");
		int user_id = (int)session.getAttribute("user_id");
		String content = request.getParameter("content");
		String board_id = request.getParameter("board_id");
		String userName = userDao.selectbyuserid(user_id);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(action.equals("insert")) {
			responseCount = replyDao.insert(Integer.parseInt(board_id),userName,content,user_id);
			if(responseCount!=0) {
				response.sendRedirect("postController?action=null&board_id="+board_id);
			}else {
				out.print("<script>alert('댓글 작성에 실패했습니다'); location.href='postController'</script>");
			}
		}
	}

}
