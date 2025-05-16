<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Contact</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Contact.css" />
</head>
<body>
	<jsp:include page='header.jsp' />
	<div class="content">
		<div class="container main-content">
			<div class="contact-left">
				<h3>Get in Touch</h3>
				<p>Have a question, feedback, or just want to say hello?</p>
				<p>Fill out the form below or reach out directly - we'd love to
					hear from you!</p>
				<form action="">
					<div class="field">
						<label for="firstName">First Name</label> <input type="text"
							name="firstName" required>
					</div>
					<div class="field">
						<label for="lastName">Last Name</label> <input type="text"
							name="lastName" required>
					</div>
					<div class="field">
						<label for="email">Email</label> <input type="text" name="email"
							required>
					</div>
					<div class="field">
						<label>Message</label>
						<textarea name="message" rows="8" required></textarea>
					</div>
					<button type="submit" class="message-submit">Submit</button>
				</form>
			</div>
			<div class="contact-right">
				<h4>Chat with us</h4>
				<ul>
					<li><a href = "https://www.instagram.com/" target="_blank">Instagram</a></li>
					<li><a href = "https://www.facebook.com/" target="_blank">Facebook</a></li>
					<li><a href="https://discord.com/" target="_blank">Discord</a></li>
					<li><a href="https://np.linkedin.com/" target="_blank">Linkedin</a></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>