<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gamehive.model.GameModel"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Admin</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/Admin.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/AddNewGame.css" />
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
					<div class="admin-games">
						<h3>Games</h3>
						<a href="#" id="openAddGameModal">Add Games</a>
					</div>
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
						List<GameModel> games = (List<GameModel>) request.getAttribute("games");
						if (games != null) {
							for (GameModel game : games) {
						%>
						<tr>
							<td><%=game.getGameId()%></td>
							<td><%=game.getGameTitle()%></td>
							<td><%=game.getGamePublisher()%></td>
							<td><%=game.getGameDevelopers()%></td>
							<td><%=game.getGameReleasedDate()%></td>
							<td><%=game.getGameRating()%></td>
							<td>$<%=game.getGamePrice()%></td>
							<td>
								<button class="edit-btn">✏️</button>
								<form method="post" action="deleteGame" style="display: inline;">
									<input type="hidden" name="gameId"
										value="<%=game.getGameId()%>">
									<button type="submit" class="delete-btn"
										onclick="return confirm('Are you sure you want to delete this game?')">🗑️</button>
								</form>
							</td>
						</tr>
						<%
						}
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
	<jsp:include page='AddNewGame.jsp' />
	<jsp:include page="footer.jsp" />
	<script>
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							const modal = document
									.getElementById("addGameModal");
							const openBtn = document
									.getElementById("openAddGameModal");
							const closeBtn = document
									.getElementById("closeModal");

							if (openBtn && modal && closeBtn) {
								openBtn.onclick = function(event) {
									event.preventDefault(); // prevent "#" from jumping
									modal.style.display = "block";
								};

								closeBtn.onclick = function() {
									modal.style.display = "none";
								};

								window.onclick = function(event) {
									if (event.target === modal) {
										modal.style.display = "none";
									}
								};
							} else {
								console
										.warn("Modal elements not found. Check if AddNewGame.jsp contains correct modal IDs.");
							}
						});
	</script>

</body>
</html>
