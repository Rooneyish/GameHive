<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gamehive.model.GameModel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Admin</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Admin.css" />
</head>
<body>
	<header>
		<div class="admin-header">
			<div>
				<a href="admin"><img alt="admin-brand-logo"
					src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
				<div class="admin-search-wrapper">
					<input type="text" id="admin-search" name="admin-search"
						placeholder="Search...">
					<button type="submit" class="admin-search-button">Search</button>
				</div>
				<div class="admin-profile">
					<img alt="Admin Profile"
						src="${pageContext.request.contextPath}/resources/images/admin.png">
					<div>
						<p>Admin,</p>
						<%
						String username = (String) session.getAttribute("username");
						%>
						<a href="profile"><%=username%></a>
					</div>
				</div>
			</div>
		</div>
	</header>

	<section>
		<div class="section-container">

			<div class="admin-sidebar">
				<ul>
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Games</a></li>
					<li><a href="#">Gamers</a></li>
					<li><a href="#">Settings</a></li>
				</ul>
				<div class="logout-button">
					<a href="logout">Logout</a>
				</div>
			</div>

			<div class="admin-dashboard">
				<div class="overview">
					<h3>Overview</h3>
					<div class="overview-cards">
						<div class="o-card">
							<h4>Total Games</h4>
							<p id="t-games-no">1234</p>
						</div>
						<div class="o-card">
							<h4>Total Users</h4>
							<p id="t-users-no">1234</p>
						</div>
						<div class="o-card">
							<h4>Total Developers</h4>
							<p id="t-developers-no">1234</p>
						</div>
						<div class="o-card">
							<h4>Total Free Games</h4>
							<p id="t-free-games-no">1234</p>
						</div>
					</div>
				</div>

				<div class="admin-table">
					<h3>Games</h3>
					<table>
						<tr>
							<th>Game ID</th>
							<th>Game Title</th>
							<th>Publisher</th>
							<th>Developers</th>
							<th>Released Date</th>
							<th>Rating</th>
							<th>Price</th>
							<th>Edit</th>
						</tr>
						<%
						GameModel gameInfo = (GameModel) request.getAttribute("gameInfo");
						if (gameInfo != null) {
						%>
						<tr>
							<td><%=gameInfo.getGameId()%></td>
							<td><%=gameInfo.getGameTitle()%></td>
							<td><%=gameInfo.getGamePublisher()%></td>
							<td><%=gameInfo.getGameDevelopers()%></td>
							<td><%=gameInfo.getGameReleasedDate()%></td>
							<td><%=gameInfo.getGameRating()%></td>
							<td>$<%=gameInfo.getGamePrice()%></td>
							<td>
								<button class="edit-btn">✏️</button>
								<button class="delete-btn">🗑️</button>
							</td>
						</tr>
						<%
						} else {
						%>
						<tr>
							<td colspan="8">No game data available</td>
						</tr>
						<%
						}
						%>
					</table>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>
