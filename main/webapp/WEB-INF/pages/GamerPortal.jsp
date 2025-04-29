<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<a href="profile">Gamer Name</a>
					</div>
				</div>
			</div>
		</div>
	</header>

	<section>
		<div class="section-container">
			<div class="filters">
				<h3>Filters</h3>

				<div class="filter-section">
					<h4>Genres</h4>
					<div>
						<label><input type="checkbox" /> FPS</label> <label><input
							type="checkbox" /> Simulation</label> <label><input
							type="checkbox" /> Strategy</label> <label><input
							type="checkbox" /> <em>Role-Playing</em></label>
					</div>
				</div>

				<div class="filter-section">
					<h4>Platforms</h4>
					<div>
						<label><input type="checkbox" /> Play Station</label> <label><input
							type="checkbox" /> Xbox</label> <label><input type="checkbox" />
							PC</label> <label><input type="checkbox" /> Mobile</label>

					</div>
				</div>

				<div class="filter-section">
					<h4>Price</h4>
					<input type="range" min="0" max="100" />
				</div>
			</div>

			<div class="recommend-games">
				<h2>Recommend Games</h2>

				<div class="row">
					<div class="game-card">
						<img
							src="${pageContext.request.contextPath}/resources/images/game1.jpg"
							alt="Game Image" />
						<div class="game-details">
							<h3>Battlefield V</h3>
							<p class="game-description">Enter mankind's greatest conflict
								with Battlefield™ V as the series goes back to its roots in a
								never-before-seen portrayal of World War 2.</p>
							<p>
								<strong>Platform:</strong> PC
							</p>
							<p>
								<strong>Price:</strong> $19.99
							</p>
							<button class="more-btn">More Details</button>
						</div>
					</div>
					<div class="game-card">
						<img
							src="${pageContext.request.contextPath}/resources/images/game1.jpg"
							alt="Game Image" />
						<div class="game-details">
							<h3>Battlefield V</h3>
							<p class="game-description">Enter mankind's greatest conflict
								with Battlefield™ V as the series goes back to its roots in a
								never-before-seen portrayal of World War 2.</p>
							<p>
								<strong>Platform:</strong> PC
							</p>
							<p>
								<strong>Price:</strong> $19.99
							</p>
							<button class="more-btn">More Details</button>
						</div>
					</div>
					<div class="game-card">
						<img
							src="${pageContext.request.contextPath}/resources/images/game1.jpg"
							alt="Game Image" />
						<div class="game-details">
							<h3>Battlefield V</h3>
							<p class="game-description">Enter mankind's greatest conflict
								with Battlefield™ V as the series goes back to its roots in a
								never-before-seen portrayal of World War 2.</p>
							<p>
								<strong>Platform:</strong> PC
							</p>
							<p>
								<strong>Price:</strong> $19.99
							</p>
							<button class="more-btn">More Details</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp" />
</body>
</html>