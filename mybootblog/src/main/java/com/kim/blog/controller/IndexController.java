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

import com.kim.blog.dao.BoardDAO;
import com.kim.blog.dao.UserDAO;
import com.kim.blog.dto.BoardDTO;


@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public IndexController() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        BoardDAO dao = new BoardDAO();
        UserDAO userdao = new UserDAO();
        HttpSession session = request.getSession();
        int pageNumber = 0;
        
        if(request.getParameter("pageNumber")!=null) {
        	pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        
        if(request.getParameter("page")!=null) {
        	if(request.getParameter("page").equals("previous")) {
        		pageNumber -= 5;
        	}else if(request.getParameter("page").equals("next")) {
        		pageNumber += 5;
        	}
        }
        
        ArrayList<BoardDTO> result = dao.limitSelect(pageNumber);
        ArrayList<BoardDTO> list = dao.select();
        int listSize = list.size();
        
        request.setAttribute("list", result);
        request.setAttribute("pageNumber", pageNumber);
        request.setAttribute("listSize", listSize);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BoardDAO dao = new BoardDAO();
		String search = request.getParameter("search");
		
		ArrayList<BoardDTO> result = dao.selectbysearch(search);
		request.setAttribute("list", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
