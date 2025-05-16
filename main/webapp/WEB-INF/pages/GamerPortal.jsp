<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GameHive | Gamer Portal</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/GamePortal.css" />
</head>
<body>

	<header>
		<div class="gamer-header">
			<div>
				<a href="gamerportal"><img alt="gamer-brand-logo"
					src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
				<div class="gamer-search-wrapper">
					<input type="text" id="gamer-search" name="gamer-search"
						placeholder="Search...">
					<button type="submit" class="gamer-search-button">Search</button>
				</div>
				<div class="gamer-profile">
					<img alt="Gamer Profile"
						src="${pageContext.request.contextPath}/resources/images/admin.png">
					<div>
						<p>Gamer,</p>
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
			<div class="filters">
				<div class="filter-section">
					<h3>Filters</h3>
					<h4>Genres</h4>
					<div>
						<label><input type="radio" name="option" value="FPS" />
							FPS</label> <label><input type="radio" name="option"
							value="Simulation" /> Simulation</label> <label><input
							type="radio" name="option" value="Strategy" /> Strategy</label> <label><input
							type="radio" name="option" value="Role-Playing" /> <em>Role-Playing</em></label>
					</div>

					<h4>Platforms</h4>
					<div>
						<label><input type="radio" name="option"
							value="Play Station" /> Play Station</label> <label><input
							type="radio" name="option" value="Xbox" /> Xbox</label> <label><input
							type="radio" name="option" value="PC" /> PC</label> <label><input
							type="radio" name="option" value="Mobile" /> Mobile</label>
					</div>

					<br />
					<button type="submit">Filter</button>
				</div>
				<div class="logout-button">
					<a href="logout">Logout</a>
				</div>

			</div>

			<div class="games-panel">
				<h2>All Games</h2>
				<c:forEach var="game" items="${gameList}" varStatus="status">
					<c:if test="${status.index % 3 ==0 }">
						<div class="row">
					</c:if>
					<div class="game-card">
						<div class="game-details">
							<h3>${game.gameTitle}</h3>
							<p class="game-description">${game.gameDescription}</p>
							<p>
								<strong>Platforms:</strong> ${game.gamePlatforms}
							</p>
							<p>
								<strong>Genres:</strong> ${game.gameGenres}
							</p>
							<p>
								<strong>Price:</strong> $${game.gamePrice}
							</p>
							<button class="review-btn">Review Game</button>
						</div>
					</div>
					<c:if test="${status.index % 3 == 2 || status.last}">
			</div>
			</c:if>
			</c:forEach>
		</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>