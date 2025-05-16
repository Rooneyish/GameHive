<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | About</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/About.css" />
</head>
<body>
	<jsp:include page='header.jsp' />
	<div class="content">
		<div class="container main-content">
			<h3>
				Main man behind <span style="color: #FF6F00">GameHive!</span>
			</h3>
			<p>Our philosophy is simple: build with passion, design with
				purpose, and empower gamers with great tools. We're a team of game
				lovers, tech enthusiasts, and data nerds dedicated to creating the
				best platform for exploring the world of gaming.</p>
			<img alt="" src="${pageContext.request.contextPath}/resources/images/me.png">
			<h4><span style="color: #FF6F00">Mr. Ronish Prajapati</span></h4>
			<h6>Founder & CEO</h6>
			<p>Visionary leader driving innovation and growth in business.</p>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>