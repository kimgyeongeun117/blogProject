<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	session.invalidate(); // �α׾ƿ� -> ���� �޸𸮿��� ���� 
	response.sendRedirect("login.jsp"); // �α׾ƿ� ���� ������ �̵� 
%>