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

import com.kim.blog.dto.UserDTO;
import com.kim.blog.service.LoginService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		LoginService loginService = new LoginService();
		UserDTO userDto = null;
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		userDto = loginService.selectUser(email, password);
		String logstatus = null;
		if(userDto != null) {
			String username = userDto.getName();
			String userPassword = userDto.getPassword();
			int user_id = userDto.getId();
			request.setAttribute("action", "login");
			session.setAttribute("username", username);
			session.setAttribute("password", userPassword);
			session.setAttribute("user_id", user_id);
			logstatus = "login";
			session.setAttribute("logstatus", logstatus);
			
			response.sendRedirect("IndexController");
		}else {
			out.print("<script>alert('로그인에 실패했습니다'); location.href='login.jsp'</script>");
		}
		
	}

}
