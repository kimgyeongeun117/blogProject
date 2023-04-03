package com.kim.blog.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kim.blog.dao.PostDAO;
import com.kim.blog.dao.ReplyDAO;
import com.kim.blog.dto.BoardDTO;
import com.kim.blog.dto.ReplyDTO;


@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostController() {
    	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String board_id = request.getParameter("board_id");
		HttpSession session = request.getSession();
		String action =request.getParameter("action");
		
		PostDAO dao = new PostDAO();
		ReplyDAO replydao = new ReplyDAO();
		
		if(action.equals("view")) {
			int updateResult = dao.update(Integer.parseInt(board_id)); 
		}
		
		BoardDTO dto =  dao.select(Integer.parseInt(board_id));
		ArrayList<ReplyDTO> replyList = replydao.select(Integer.parseInt(board_id));
		
		request.setAttribute("title", dto.getTitle());
		request.setAttribute("description", dto.getDescription());
		request.setAttribute("board_id", board_id);
		request.setAttribute("board_user_id", dto.getUser_id());
		request.setAttribute("category_id", dto.getCategory_id());
		request.setAttribute("createdAt", dto.getCreatedAt());
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("replyList", replyList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("delete in");
		request.setCharacterEncoding("UTF-8");
		String board_id = request.getParameter("board_id");
		HttpSession session = request.getSession();
		
		PostDAO dao = new PostDAO();
		ReplyDAO replydao = new ReplyDAO();
		BoardDTO dto =  dao.select(Integer.parseInt(board_id));
		ArrayList<ReplyDTO> replyList = replydao.select(Integer.parseInt(board_id));
		
		request.setAttribute("title", dto.getTitle());
		request.setAttribute("description", dto.getDescription());
		request.setAttribute("board_id", board_id);
		request.setAttribute("board_user_id", dto.getUser_id());
		request.setAttribute("category_id", dto.getCategory_id());
		request.setAttribute("createdAt", dto.getCreatedAt());
		request.setAttribute("user_id", session.getAttribute("user_id"));
		request.setAttribute("replyList", replyList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
		dispatcher.forward(request, response);
	}

}
