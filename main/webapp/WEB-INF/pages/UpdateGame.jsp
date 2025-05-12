<div id="updateGameModal" class="modal">
	<div class="modal-content">
		<h3>Update Game</h3>
		<span class="close-btn" id="closeModal">&times;</span>
		<form action="updateGame" method="post">
			<input type="hidden" name="gameId" id="gameId" />

			<div class="field">
				<label>Game Title</label> <input type="text" name="gameTitle"
					id="gameTitle" required />
			</div>

			<div class="field">
				<label>Game Description (In 16-20 words)</label>
				<textarea name="gameDescription" id="gameDescription" rows="3"
					required></textarea>
			</div>

			<div class="field">
				<label>Publisher</label> <input type="text" name="publisher"
					id="publisher" required />
			</div>

			<div class="field">
				<label>Developers</label> <input type="text" name="developers"
					id="developers" required />
			</div>

			<div class="field">
				<label>Genres</label>
				<div class="options-group">
					<label><input type="checkbox" name="genre[]" value="Action" />Action</label>
					<label><input type="checkbox" name="genre[]"
						value="Adventure" />Adventure</label> <label><input
						type="checkbox" name="genre[]" value="Role-Playing" />Role-Playing</label>
					<label><input type="checkbox" name="genre[]"
						value="Simulation" />Simulation</label> <label><input
						type="checkbox" name="genre[]" value="Strategy" />Strategy</label> <label><input
						type="checkbox" name="genre[]" value="Sports" />Sports</label> <label><input
						type="checkbox" name="genre[]" value="Racing/Driving" />Racing/Driving</label>
					<label><input type="checkbox" name="genre[]" value="Puzzle" />Puzzle</label>
				</div>
			</div>

			<div class="field">
				<label>Platforms</label>
				<div class="options-group">
					<label><input type="checkbox" name="platform[]" value="PC" />PC</label>
					<label><input type="checkbox" name="platform[]"
						value="Xbox" />Xbox</label> <label><input type="checkbox"
						name="platform[]" value="PlayStation" />PlayStation</label> <label><input
						type="checkbox" name="platform[]" value="Switch" />Nintendo
						Switch</label> <label><input type="checkbox" name="platform[]"
						value="Mobile" />Mobile</label> <label><input type="checkbox"
						name="platform[]" value="VR" />VR</label>
				</div>
			</div>

			<div class="field">
				<label>Released Date</label> <input type="date" name="releasedDate"
					id="releasedDate" required />
			</div>

			<div class="field">
				<label>Rating</label> <input type="number" name="rating" id="rating"
					required />
			</div>

			<div class="field">
				<label>Price</label> <input type="text" name="price" id="price"
					required />
			</div>
			<input type="hidden" name="gameId" value="${game.gameId}">

			<button type="submit" class="update-game-button">Update Game</button>
		</form>
	</div>
</div>