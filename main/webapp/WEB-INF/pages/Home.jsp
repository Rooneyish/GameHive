<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Home.css" />
</head>
<body>
	<jsp:include page='header.jsp' />
	<div class=content>
		<div class="container flex main-content">
			<div class="homeText">
				<h3>
					Welcome to <span style="color: #FF6F00">GameHive!</span>
				</h3>
				<h6>Your Ultimate Hub for Game Insights</h6>
				<p>Dive into the world of gaming like never before! GameSphere
					brings you detailed information on the latest titles, all-time
					classics, and upcoming releases — all in one place. Whether you're
					a casual player or a hardcore enthusiast, you'll find everything
					from game reviews and ratings to trailers, system requirements, and
					community feedback.</p>
				<a>About Us</a>
			</div>
			<div class="branding">
				<img
					src="${pageContext.request.contextPath}/resources/images/logo.png" />
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>