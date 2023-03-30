<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	session.invalidate(); // 로그아웃 -> 세션 메모리에서 해제 
	response.sendRedirect("login.jsp"); // 로그아웃 이후 페이지 이동 
%>