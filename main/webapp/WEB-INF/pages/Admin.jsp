<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gamehive.model.GameModel"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="admin-header">
			<div>
				<a href="admin"><img alt="admin-brand-logo"
					src="${pageContext.request.contextPath}/resources/images/logo.png"></a>

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
							<p id="t-games-no"><%=request.getAttribute("totalGames")%></p>
						</div>
						<div class="o-card">
							<h4>Total Users</h4>
							<p id="t-users-no"><%=request.getAttribute("totalUsers")%></p>
						</div>
						<div class="o-card">
							<h4>Total Developers</h4>
							<p id="t-developers-no"><%=request.getAttribute("totalDevelopers")%></p>
						</div>
						<div class="o-card">
							<h4>Total Free Games</h4>
							<p id="t-free-games-no"><%=request.getAttribute("totalFreeGames")%></p>
						</div>
					</div>
				</div>

				<div class="admin-table">
					<div class="admin-games">
						<h3>Games</h3>
						<div style="display: flex; justify-content: space-between; gap: 20px;">
							<form method="get" action="admin">
								<div class="sort-container">
									<label for="sortOptions">Sort by:</label> <select
										id="sortOptions" name="sortOptions"
										onchange="this.form.submit()">
										<option value="id_asc">ID ↑</option>
										<option value="id_desc">ID ↓</option>
										<option value="price_asc">Price ↑</option>
										<option value="price_desc">Price ↓</option>
										<option value="rating_asc">Rating ↑</option>
										<option value="rating_desc">Rating ↓</option>
										<option value="date_asc">Release Date ↑</option>
										<option value="date_desc">Release Date ↓</option>
									</select>
								</div>
							</form>
							<a href="#" id="openAddGameModal">Add Games</a>
						</div>
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
								<button class="edit-btn" data-id="<%=game.getGameId()%>"
									data-title="<%=game.getGameTitle()%>"
									data-description="<%=game.getGameDescription()%>"
									data-publisher="<%=game.getGamePublisher()%>"
									data-developers="<%=game.getGameDevelopers()%>"
									data-genres="<%=String.join(",", game.getGameGenres())%>"
									data-platforms="<%=String.join(",", game.getGamePlatforms())%>"
									data-date="<%=game.getGameReleasedDate()%>"
									data-rating="<%=game.getGameRating()%>"
									data-price="<%=game.getGamePrice()%>">✏️</button>
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
	<jsp:include page='UpdateGame.jsp' />
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
									event.preventDefault(); 
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
		
		document.addEventListener("DOMContentLoaded", function() {
		    const updateModal = document.getElementById("updateGameModal");
		    const editButtons = document.querySelectorAll(".edit-btn");
		    const closeUpdateBtn = document.querySelector("#updateGameModal #closeModal");

		    editButtons.forEach(button => {
		        button.addEventListener("click", function() {
		            updateModal.style.display = "block";

		            document.querySelector("#gameId").value = this.dataset.id;
		            document.querySelector("#gameTitle").value = this.dataset.title;
		            document.querySelector("#gameDescription").value = this.dataset.description;
		            document.querySelector("#publisher").value = this.dataset.publisher;
		            document.querySelector("#developers").value = this.dataset.developers;
		            document.querySelector("#releasedDate").value = this.dataset.date;
		            document.querySelector("#rating").value = this.dataset.rating;
		            document.querySelector("#price").value = this.dataset.price;

		            const genres = this.dataset.genres.split(',').map(g => g.trim().toLowerCase());
		            document.querySelectorAll("#updateGameModal input[name='genre[]']").forEach(checkbox => {
		                const checkboxValue = checkbox.value.trim().toLowerCase();
		                checkbox.checked = genres.includes(checkboxValue);
		            });

		            const platforms = this.dataset.platforms.split(',').map(p => p.trim().toLowerCase());
		            document.querySelectorAll("#updateGameModal input[name='platform[]']").forEach(checkbox => {
		                const checkboxValue = checkbox.value.trim().toLowerCase();
		                checkbox.checked = platforms.includes(checkboxValue);
		            });
		        });
		    });

		    if (closeUpdateBtn) {
		        closeUpdateBtn.onclick = function() {
		            updateModal.style.display = "none";
		        };
		    }

		    window.onclick = function(event) {
		        if (event.target === updateModal) {
		            updateModal.style.display = "none";
		        }
		    };
		});
	</script>

</body>
</html>
