<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!-- JSTL사용 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty username and empty password }">
	out.println("<script>
		alert('로그인이 필요합니다');
		location.href = 'login.jsp'
	</script>");
</c:if>

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
			<a class="navbar-brand" href="IndexController"><c:out
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
						href="IndexController">Home</a></li>
					<!-- <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="about.jsp">About</a></li> -->
					<!-- <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="post.jsp">Post</a></li> -->
					<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
						href="WriteController">Write</a></li>
					<c:if test="${empty logstatus}">
						<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
							href="login.jsp">login</a></li>
					</c:if>
					<c:if test="${not empty logstatus}">
						<li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
							href="login.jsp">logout</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('assets/img/about-bg.jpg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="post-heading">
						<h1>
							<c:out value="${title }" />
						</h1>
						<span class="meta"> Posted by <c:out value="${username }" />
							<!-- <a href="#!">Start Bootstrap</a>
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
					<form action="UpdateController?action=updatebefore" method="post"
						style="display: flex; flex-direction: column;">
						<p style="border: 1px solid gray; padding: 10px">
							<c:out value="${description }" />
						</p>
						<input type="text" style="display: none" name="description"
							value="${description }" /> <input type="text"
							style="display: none" name="board_id" value="${board_id }" />
						<div style="display: flex; justify-content: flex-end">
							<c:set var="board_user_id" value="${board_user_id }" />
							<c:if test="${board_user_id eq user_id }">
								<button class="btn btn-primary text-uppercase" id="submitButton"
									type="submit" style="margin: 10px 0px">수정</button>
							</c:if>
						</div>
					</form>
					<form action="DeleteController" method="post"
						style="display: flex; justify-content: flex-end">
						<c:if test="${board_user_id eq user_id }">
							<input type="text" style="display: none" name="board_id"
								value="${board_id }" />
							<button class="btn btn-primary text-uppercase" id="submitButton"
								type="submit"
								style="margin: 10px 0px; background-color: #F82B55">삭제</button>
						</c:if>
					</form>

					<p>댓글창</p>
					<div class="card bg-light">
						<div class="card-body">
							<!-- Comment form-->
							<form action="ReplyController?action=insert" method="post"
								class="mb-4" style="display: flex; flex-direction: column;">
								<textarea class="form-control" id="description" name="content"
									placeholder="Enter your message here..." style="height: 6rem"
									data-sb-validations="required"></textarea>
								<input type="text" style="display: none" name="board_id"
									value="${board_id }" />
								<button class="btn btn-primary text-uppercase" id="submitButton"
									type="submit"
									style="background-color: #FFA479; margin: 10px 0px;">등록</button>
							</form>
							<!-- Comment with nested comments-->
							<c:forEach var="list" items="${replyList }">
								<div class="d-flex mb-4" style="display: flex;">
									<!-- Parent comment-->
									<div class="flex-shrink-0">
										<img class="rounded-circle"
											src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
											alt="..." />
									</div>
									<div class="ms-3">
										<div class="fw-bold">${list.userName }</div>
										${list.content }
									</div>
									<form action="ReplyController?action=delete" method="post"
										style="flex: 1; display: flex; justify-content: flex-end;">
										<input type="text" style="display: none" name="reply_id"
											value="${list.id }" /> <input type="text"
											style="display: none" name="board_id" value="${board_id }" />
										<c:if test="${list.user_id eq user_id }">
											<button class="btn btn-primary text-uppercase"
												id="submitButton" type="submit"
												style="background-color: #930A13; border-radius: 10px;">삭제</button>
										</c:if>
									</form>
								</div>
								<!-- Single comment-->
							</c:forEach>
						</div>
					</div>
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
