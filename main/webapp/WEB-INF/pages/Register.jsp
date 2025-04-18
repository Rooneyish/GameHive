<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
<body>
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
							<label for="role">Role</label>
							<div class="radio-buttons">
								<input type="radio" id="gamer" name="role" value="gamer">
								<label for="gamer">Gamer</label> <input type="radio" id="admin"
									name="role" value="admin"><label for="admin">Admin</label>
							</div>
						</div>
						<div></div>
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
				<a href="loin" class="not-registered">Registered Already? Log-In</a>
			</div>
			<div class="banner">
				<img alt="" src="${pageContext.request.contextPath}/resources/images/registerimg1.png">
			</div>
		</div>
	</div>
</body>
</html>