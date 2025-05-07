<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Register</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Login.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Register.css" />
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
		<div class="register-container">
			<div class="register-forms">
				<p>Welcome!</p>
				<h3>
					Register to <span>GameHive</span>
				</h3>
				<form action="register" method="post">
					<div class="register-row">
						<div>
							<label for="username">Username</label> <input type="text"
								id="username" name="username" required>
						</div>
						<div>
							<label for="user-email">Email</label> <input type="text"
								id="user-email" name="user-email" required>
						</div>
					</div>
					<div class="register-row">
						<div>
							<label for="dob">Date of Birth</label> <input type="date"
								id="dob" name="dob" required>
						</div>
						<div>
							<label for="gender">Gender</label>
							<div class="radio-buttons">
								<input type="radio" id="male" name="gender" value="male">
								<label for="male">Male</label> <input type="radio" id="female"
									name="gender" value="female"><label for="female">Female</label>
								<input type="radio" id="other" name="gender" value="other">
								<label for="other">Other</label>
							</div>
						</div>
					</div>
					<div class="register-row">
						<div>
							<label for="password">Password</label> <input type="password"
								id="password" name="password" required>
						</div>
						<div>
							<label for="c-password">Confirm Password</label> <input
								type="password" id="c-password" name="c-password" required>
						</div>
					</div>
					<button type="submit" class="Register-button">Register</button>
				</form>
				<a href="login" class="not-registered">Registered Already? Log-In</a>
			</div>
			<div class="banner">
				<img alt=""
					src="${pageContext.request.contextPath}/resources/images/registerimg1.png">
			</div>
		</div>
	</div>
</body>
</html>