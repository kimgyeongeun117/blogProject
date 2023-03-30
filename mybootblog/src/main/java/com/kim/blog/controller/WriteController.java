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

import com.kim.blog.dao.BoardDAO;
import com.kim.blog.dto.BoardDTO;
import com.kim.blog.dto.CategoryDTO;
import com.kim.blog.service.WriteService;


@WebServlet("/WriteController")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public WriteController() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String user_id = request.getParameter("user_id");
		WriteService writeService = new WriteService();
		System.out.println("get");
		
//		request.setAttribute("user_id",user_id);
		ArrayList<CategoryDTO> list = writeService.selectCategory();
		request.setAttribute("list",list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/write.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int responseCount = 0;
		BoardDAO dao = new BoardDAO();
		//아이디 임시 지정
		int user_id = 1;
		String category_id = request.getParameter("category_id");
		String action = request.getParameter("action");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(action+","+title+","+description+","+category_id);
		if(action.equals("insert")) {
			responseCount = dao.insert(user_id,title,description,Integer.parseInt(category_id));
			if(responseCount!=0) {
				response.sendRedirect("IndexController");
			}else {
				out.print("<script>alert('글 작성에 실패했습니다'); location.href='write.jsp'</script>");
			}
		}
	}

}
