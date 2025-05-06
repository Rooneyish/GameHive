<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Profile</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/UserProfile.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/GamePortal.css" />
<style type="text/css">
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
	<header>
		<div class="gamer-header">
			<div>
				<a href="gamerportal"><img alt="gamer-brand-logo"
					src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
				<div class="gamer-profile">
					<a href="gamerportal">Dashboard</a>
				</div>
			</div>
		</div>
	</header>
	<section>
		<div class="section-container">
			<div class="edit-profile">
				<form action="profile" method="post">
					<h3>Edit Profile</h3>
					<div>
						<label for="edit-username">Username</label> <input type="text"
							id="edit-username" name="edit-username" required>
					</div>
					<div>
						<label for="edit-email">Email</label> <input type="text"
							id="edit-email" name="edit-email" required>
					</div>

					<div>
						<label for="edit-dob">Date of Birth</label> <input type="date"
							id="edit-dob" name="edit-dob" required>
					</div>
					<div>
						<label for="edit-gender">Gender</label>
						<div class="radio-buttons">
							<input type="radio" id="e-male" name="edit-gender" value="male">
							<label for="e-male">Male</label> <input type="radio"
								id="e-female" name="edit-gender" value="female"><label
								for="e-female">Female</label> <input type="radio" id="e-other"
								name="edit-gender" value="other"> <label for="e-other">Other</label>
						</div>
					</div>
					<div>
						<label for="edit-password">Enter Password to Update</label> <input
							type="password" id="edit-password" name="edit-password" required>
					</div>
					<button type="submit" class="edit-profile-button">Save
						Profile</button>
				</form>
			</div>
			<div class="profile">
				<img alt="user-profile" src="">
				<div>
					<p class="profile-username">Username</p>
					<p class="profile-email">User-email</p>
					<p class="profile-created-date">created-date</p>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>