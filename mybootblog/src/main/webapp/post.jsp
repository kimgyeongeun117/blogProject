<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!-- JSTL사용 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String username = (String)session.getAttribute("username");
	String password = (String)session.getAttribute("password");
	int user_id = (int)session.getAttribute("user_id");
	String post_user_id = (String)request.getAttribute("user_id");
	
    if(username == null && password == null) {
    	out.println("<script>alert('로그인이 필요합니다'); location.href='login.jsp'</script>");
    }
    
%>

<!-- 글목록 -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Clean Blog - Blog List</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="IndexController"><%=username %> 게시판</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
						href="IndexController">Home</a></li>
					<!-- <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="about.jsp">About</a></li> -->
					<!-- <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="post.jsp">Post</a></li> -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
						href="WriteController">Write</a></li>
					<% if(session.getAttribute("logstatus") == null) {%>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
						href="login.jsp">login</a></li>
					<% }%>
					<% if(session.getAttribute("logstatus") != null) {%>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
						href="login.jsp">logout</a></li>
					<% }%>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('assets/img/post-bg.jpg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="post-heading">
						<h1>
							<c:out value="${title }" />
						</h1>
						<span class="meta"> Posted by <%=username %> <!-- <a href="#!">Start Bootstrap</a>
                                on August 24, 2023 -->
						</span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Post Content-->
	<article class="mb-4">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<form action="UpdateController?action=updatebefore" method="post">
						<p style="border: 1px solid gray; padding: 10px">
							<c:out  value="${description }" />
						</p>
						<input type="text" style="display: none" name="description"  value="${description }"/>
						<input type="text" style="display: none" name="board_id"  value="${board_id }"/>
						<div>
							<%if(post_user_id.equals(user_id+"")) {%>
							<button class="btn btn-primary text-uppercase" id="submitButton"
								type="submit" style="margin: 20px 0px">수정</button>
							<%} %>
					</form>
					
					<form action="DeleteController"  method="post">
						<%if(post_user_id.equals(user_id+"")) {%>
						<input type="text" style="display: none" name="board_id"  value="${board_id }"/>
						<button class="btn btn-primary text-uppercase" id="submitButton"
							type="submit" style="margin: 20px 0px; background-color: #F82B55">삭제</button>
						<%} %>
					</form>
				</div>
				Placeholder text by <a href="http://spaceipsum.com/">Space Ipsum</a>
				&middot; Images by <a
					href="https://www.flickr.com/photos/nasacommons/">NASA on The
					Commons</a>
				</p>
			</div>
		</div>
		</div>
	</article>
	<!-- Footer-->
	<footer class="border-top">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<ul class="list-inline text-center">
						<li class="list-inline-item"><a href="#!"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-twitter fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li class="list-inline-item"><a href="#!"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
						<li class="list-inline-item"><a href="#!"> <span
								class="fa-stack fa-lg"> <i
									class="fas fa-circle fa-stack-2x"></i> <i
									class="fab fa-github fa-stack-1x fa-inverse"></i>
							</span>
						</a></li>
					</ul>
					<div class="small text-center text-muted fst-italic">Copyright
						&copy; Your Website 2023</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
</body>
</html>
