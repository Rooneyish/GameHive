<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Log-In</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Login.css" />
</head>
<body>
	<div class="auth-background">
		<div class="auth-logo">
			<img alt="GameHive Logo"
				src="${pageContext.request.contextPath}/resources/images/logo.png">
		</div>
		<div class="login-container">
			<div class="left">
				<img alt="GameHive Logo"
					src="${pageContext.request.contextPath}/resources/images/loginimg1.png">
			</div>
			<div class="right">
				<p>Welcome Back!</p>
				<h3>
					Log-In to <span>GameHive</span>
				</h3>
				<form action="login" method="post">
					<div>
						<label for="username">Username</label> <input type="text"
							id="username" name="username" required>
					</div>
					<div>
						<label for="password">Password</label> <input type="password"
							id="password" name="password" required>
					</div>
					<button type="submit" class="login-button">Log In</button>
				</form>
				<a href="register" class="not-registered">Not Registered? Sign-Up</a>
			</div>
		</div>
	</div>
</body>
</html>