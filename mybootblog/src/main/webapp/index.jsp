<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!-- JSTL사용 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty username and empty password }">
	out.println("<script>
		alert('로그인이 필요합니다');
		location.href = 'loginController'
	</script>");
</c:if>

<!-- 홈페이지 -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>메인 페이지</title>
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
<!-- 바디 -->
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="indexController"><c:out
					value="${username }" />의 게시판</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
						href="indexController">Home</a></li>
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
						href="writeController">Write</a></li>
					<c:if test="${empty logstatus}">
						<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
							href="loginController">login</a></li>
					</c:if>
					<c:if test="${not empty logstatus}">
						<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
							href="logoutController">logout</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('assets/img/home-bg.jpg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="site-heading">
						<h1>
							<c:out value="${username }" />
							Blog
						</h1>
						<span class="subheading">A Blog Theme by <c:out
								value="${username }" /></span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Main Content-->
	<nav class="navbar navbar-light" style="display: flex">
		<div class="container-fluid"
			style="justify-content: center; margin-bottom: 50px">
			<form class="d-flex" action="indexController" method="post">
				<input class="form-control me-2" type="search" placeholder="Search"
					name="search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<!-- Post preview  반복 필요-->
				<c:forEach var="list" items="${list}" varStatus="status">
					<div class="post-preview">
						<a href="postController?board_id=${list.id }&action=view">
							<h2 class="post-title">${list.title }</h2>
						</a>
						<p class="post-meta">
							조회수:${list.views} Posted by <a href="#!">${list.userName}</a> on
							${list.createdAt }
						</p>
					</div>
					<!-- Divider-->
					<hr class="my-4" />
				</c:forEach>
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<c:if test="${pageNumber - 5 ge 0}">
							<li class="page-item"><a class="page-link" name="pageButton" href="indexController?page=previous&pageNumber=${pageNumber}">이전</a></li>
						</c:if>
						<c:if test="${listSize gt pageNumber + 5}">
						<li class="page-item"><a class="page-link"  name="pageButton" href="indexController?page=next&pageNumber=${pageNumber}">다음</a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
	</div>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>
