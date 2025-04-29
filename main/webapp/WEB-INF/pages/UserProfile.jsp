<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
</head>
<body>
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
				<form action="edit-profile" method="">
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
						<label for="edit-password">Password</label> <input type="text"
							id="edit-password" name="edit-password" required>
					</div>
					<div>
						<label for="edit-c-passowrd">Confirm Password</label> <input
							type="text" id="edit-c-passowrd" name="edit-c-passowrd" required>
					</div>
					<button type="submit" class="edit-profile-button">Save Profile</button>
				</form>
			</div>
			<div class="profile">
				<img alt="user-profile" src="">
				<div>
					<p class="profile-username">Username</p>
					<p class ="profile-email">User-email</p>
					<p class = "profile-created-date">created-date</p>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>