<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Log-In</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Login.css" />
	
<style>
.overlay {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 9999;
    justify-content: center;
    align-items: center;
    transition: opacity 0.3s ease;
}

/* Message Box Styles */
.overlay-message {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    max-width: 400px;
    text-align: center;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); 
}

.overlay .error-message {
    background-color: #ff4e4e;
    color: white;
    font-weight: bold;
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 15px;
}

.overlay .success-message {
    background-color: #4caf50;
    color: white;
    font-weight: bold;
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 15px;
}

.overlay button {
    background-color: #f44336;
    color: white;
    border: none;
    padding: 10px 20px;
    margin-top: 10px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.overlay button:hover {
    background-color: #d32f2f;
}

.overlay button:focus {
    outline: none; 
}
</style>
</head>
<body>
	<c:if test="${not empty error}">
		<div class="overlay" style="display: flex;">
			<div class="overlay-message">
				<p class="error-message">${error}</p>
				<button onclick="this.closest('.overlay').style.display='none'">Close</button>
			</div>
		</div>
	</c:if>

	<c:if test="${not empty success}">
		<div class="overlay" style="display: flex;">
			<div class="overlay-message">
				<p class="success-message">${success}</p>
				<button onclick="this.closest('.overlay').style.display='none'">Close</button>
			</div>
		</div>
	</c:if>
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