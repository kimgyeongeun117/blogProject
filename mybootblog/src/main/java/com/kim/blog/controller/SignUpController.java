package com.kim.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kim.blog.service.SignUpService;

@WebServlet("/signUpController")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int resultCount = 0;
		SignUpService signUpService = new SignUpService();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 같은 이메일,이름의 사용자가 없다면 생성 허가
		if (signUpService.selectUser(name, email) == null) {
			resultCount = signUpService.insertUser(name, email, password, phoneNumber);
			if (resultCount != 0) {
				response.sendRedirect("loginController");
			} else {
				out.print("<script>alert('계정 생성에 실패했습니다'); location.href='signUpController'</script>");
			}
		}else {
			out.print("<script>alert('이미 존재하는 아이디 입니다.'); location.href='signUpController'</script>");
		}

	}

}
