package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.gamehive.config.DBconfig;
import com.gamehive.model.GameModel;

/**
 * @author Ronish Prajapati LUM-ID 23048584
 */

public class GameService {
	private Connection dbConnect;

	public GameService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Searches for games whose titles contain the specified search value
	 * (case-insensitive).
	 *
	 * @param searchValue the string to search for within game titles
	 * @return a list of GameModel objects matching the search criteria; empty list
	 *         if none found or on error
	 */
	public List<GameModel> searchGames(String searchValue) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}

		List<GameModel> games = new ArrayList<>();
		String selectQuery = "SELECT gi.game_id, gi.game_title, gi.game_description, gi.game_publisher, GROUP_CONCAT(DISTINCT d.developer SEPARATOR ', ') AS developers, GROUP_CONCAT(DISTINCT g.genre SEPARATOR ', ') AS genres, GROUP_CONCAT(DISTINCT p.platform SEPARATOR ', ') AS platforms, gi.game_released_date, gi.game_rating, gi.game_price FROM game_information gi LEFT JOIN game_developers gd ON gi.game_id = gd.game_id LEFT JOIN developers d ON gd.developer_id = d.developer_id LEFT JOIN game_genres gg ON gi.game_id = gg.game_id LEFT JOIN genres g ON gg.genre_id = g.genre_id LEFT JOIN game_platforms gp ON gi.game_id = gp.game_id LEFT JOIN platforms p ON gp.platform_id = p.platform_id WHERE LOWER(game_title) LIKE LOWER(CONCAT('%', ?, '%')) GROUP BY gi.game_id;";

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {

			stmt.setString(1, searchValue);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					GameModel game = new GameModel();
					game.setGameId(rs.getInt("game_id"));
					game.setGameTitle(rs.getString("game_title"));
					game.setGameDescription(rs.getString("game_description"));
					game.setGameDevelopers(rs.getString("developers"));
					game.setGamePlatforms(rs.getString("platforms"));
					game.setGameGenres(rs.getString("genres"));
					game.setGamePrice(rs.getFloat("game_price"));
					games.add(game);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	/**
	 * Retrieves the total number of games stored in the database.
	 *
	 * @return the count of games as an integer, or 0 if a database connection is
	 *         unavailable or an error occurs
	 */
	public int getNumberOfGames() {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return 0;
		}

		String selectQuery = "SELECT COUNT(game_id) number_of_games FROM game_information;";

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getInt("number_of_games");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Retrieves the total number of developers stored in the database.
	 *
	 * @return the count of developers as an integer, or 0 if the database
	 *         connection is unavailable or an error occurs
	 */
	public int getNumberOfDevelopers() {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return 0;
		}

		String selectQuery = "SELECT COUNT(developer_id) number_of_developers FROM developers;";

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getInt("number_of_developers");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Retrieves the total number of free games (games with a price of 0) in the
	 * database.
	 *
	 * @return the count of free games as an integer, or 0 if the database
	 *         connection is unavailable or an error occurs
	 */
	public int getNumberOfFreeGames() {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return 0;
		}

		String selectQuery = "SELECT COUNT(game_id) number_of_free_games FROM game_information WHERE game_price = 0;";

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getInt("number_of_free_games");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Retrieves a list of all games with their detailed information from the
	 * database. This includes game ID, title, description, publisher, developers,
	 * genres, platforms, release date, rating, and price.
	 *
	 * @return a List of GameModel objects representing all games in the database,
	 *         or null if the database connection is not available.
	 */
	public List<GameModel> getAllGameInfo() {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}

		List<GameModel> games = new ArrayList<>();

		String selectQuery = "SELECT gi.game_id, gi.game_title, gi.game_description, gi.game_publisher, GROUP_CONCAT(DISTINCT d.developer SEPARATOR ', ') AS developers, GROUP_CONCAT(DISTINCT g.genre SEPARATOR ', ') AS genres, GROUP_CONCAT(DISTINCT p.platform SEPARATOR ', ') AS platforms, gi.game_released_date, gi.game_rating, gi.game_price FROM game_information gi LEFT JOIN game_developers gd ON gi.game_id = gd.game_id LEFT JOIN developers d ON gd.developer_id = d.developer_id LEFT JOIN game_genres gg ON gi.game_id = gg.game_id LEFT JOIN genres g ON gg.genre_id = g.genre_id LEFT JOIN game_platforms gp ON gi.game_id = gp.game_id LEFT JOIN platforms p ON gp.platform_id = p.platform_id GROUP BY gi.game_id;";

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				int gId = result.getInt("game_id");
				String gTitle = result.getString("game_title");
				String gDescription = result.getString("game_description");
				String gPublisher = result.getString("game_publisher");
				String gDevelopers = result.getString("developers");
				String gGenres = result.getString("genres");
				String gPlatforms = result.getString("platforms");
				Date gReleasedDate = result.getDate("game_released_date");
				float gRating = result.getFloat("game_rating");
				float gPrice = result.getFloat("game_price");

				GameModel game = new GameModel(gId, gTitle, gDescription, gPublisher, gReleasedDate, gPrice, gRating,
						gDevelopers, gGenres, gPlatforms);
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	/**
	 * Adds a new game and its related genres, platforms, and developers to the
	 * database.
	 * 
	 * Inserts the game details first, then links genres, platforms, and developers
	 * using bridge tables. If a developer doesn't exist, it will be created.
	 * 
	 * @param game The game data to insert
	 * @return true if the insert was successful, false if the game title already
	 *         exists, null on error
	 */
	public Boolean insertGame(GameModel game) {
		if (dbConnect == null) {
			System.err.println("Database connection is not available");
			return null;
		}

		String insertGameInfo = "INSERT INTO game_information (game_title, game_description, game_publisher, game_released_date, game_price, game_rating) VALUES (?, ?, ?, ?, ?, ?)";
		String getGameIdSQL = "SELECT game_id FROM game_information WHERE game_title = ?";
		String insertGameGenre = "INSERT INTO game_genres (game_id, genre_id) VALUES (?, ?)";
		String insertGamePlatform = "INSERT INTO game_platforms (game_id, platform_id) VALUES (?, ?)";
		String insertGameDeveloper = "INSERT INTO game_developers (game_id, developer_id) VALUES (?, ?)";

		try {
			try (PreparedStatement insertStmt = dbConnect.prepareStatement(insertGameInfo)) {
				insertStmt.setString(1, game.getGameTitle());
				insertStmt.setString(2, game.getGameDescription());
				insertStmt.setString(3, game.getGamePublisher());
				insertStmt.setDate(4, game.getGameReleasedDate());
				insertStmt.setFloat(5, game.getGamePrice());
				insertStmt.setFloat(6, game.getGameRating());
				insertStmt.executeUpdate();
				System.out.println("Game inserted into game_information table.");
			}

			int gameId = -1;
			try (PreparedStatement selectStmt = dbConnect.prepareStatement(getGameIdSQL)) {
				selectStmt.setString(1, game.getGameTitle());
				ResultSet result = selectStmt.executeQuery();
				if (result.next()) {
					gameId = result.getInt("game_id");
					System.out.println("Retrieved game_id: " + gameId);
				} else {
					System.err.println("Game ID not found after insert.");
					return false;
				}
			}

			for (String genreName : game.getGameGenres().split(",")) {
				int genreId = getGenreIdByName(genreName.trim());
				if (genreId != -1) {
					try (PreparedStatement stmt = dbConnect.prepareStatement(insertGameGenre)) {
						stmt.setInt(1, gameId);
						stmt.setInt(2, genreId);
						stmt.executeUpdate();
						System.out.println("Genre linked: " + genreName.trim() + " (ID: " + genreId + ")");
					}
				} else {
					System.out.println("Genre not found: " + genreName.trim());
				}
			}

			for (String platformName : game.getGamePlatforms().split(",")) {
				int platformId = getPlatformIdByName(platformName.trim());
				if (platformId != -1) {
					try (PreparedStatement stmt = dbConnect.prepareStatement(insertGamePlatform)) {
						stmt.setInt(1, gameId);
						stmt.setInt(2, platformId);
						stmt.executeUpdate();
						System.out.println("Platform linked: " + platformName.trim() + " (ID: " + platformId + ")");
					}
				} else {
					System.out.println("Platform not found: " + platformName.trim());
				}
			}

			for (String devName : game.getGameDevelopers().split(",")) {
				int devId = getOrCreateDeveloperId(devName.trim());
				if (devId != -1) {
					try (PreparedStatement stmt = dbConnect.prepareStatement(insertGameDeveloper)) {
						stmt.setInt(1, gameId);
						stmt.setInt(2, devId);
						stmt.executeUpdate();
						System.out.println("Developer linked: " + devName.trim() + " (ID: " + devId + ")");
					}
				} else {
					System.out.println("Failed to create or find developer: " + devName.trim());
				}
			}

			System.out.println("Game inserted successfully with all relations!");
			return true;

		} catch (SQLException e) {
			if (e.getMessage().toLowerCase().contains("duplicate") || e.getErrorCode() == 1062) {
				System.err.println("Duplicate game title error: " + e.getMessage());
				return false;
			}
			System.err.println("SQL Exception during game insert:");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the genre ID from the database for a given genre name.
	 * 
	 * @param genreName The name of the genre to look up
	 * @return The genre ID if found, otherwise -1
	 */
	private int getGenreIdByName(String genreName) {
		String sql = "SELECT genre_id FROM genres WHERE genre = ?";
		try (PreparedStatement stmt = dbConnect.prepareStatement(sql)) {
			stmt.setString(1, genreName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("genre_id");
				System.out.println("Found Genre ID for '" + genreName + "': " + id);
				return id;
			} else {
				System.out.println("Genre not found in DB: " + genreName);
				return -1;
			}
		} catch (SQLException e) {
			System.err.println("Error fetching genre ID for: " + genreName);
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Retrieves the platform ID from the database for a given platform name.
	 * 
	 * @param platformName The name of the platform to look up
	 * @return The platform ID if found, otherwise -1
	 */
	private int getPlatformIdByName(String platformName) {
		String sql = "SELECT platform_id FROM platforms WHERE platform = ?";
		try (PreparedStatement stmt = dbConnect.prepareStatement(sql)) {
			stmt.setString(1, platformName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("platform_id");
				System.out.println("Found Platform ID for '" + platformName + "': " + id);
				return id;
			} else {
				System.out.println("Platform not found in DB: " + platformName);
				return -1;
			}
		} catch (SQLException e) {
			System.err.println("Error fetching platform ID for: " + platformName);
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Retrieves the developer ID for the given developer name if it exists,
	 * otherwise inserts a new developer and returns the new ID.
	 * 
	 * @param devName The developer name to search or insert
	 * @return The developer ID if found or created, otherwise -1 on error
	 */
	private int getOrCreateDeveloperId(String devName) {
		String selectSQL = "SELECT developer_id FROM developers WHERE developer = ?";
		String insertSQL = "INSERT INTO developers (developer) VALUES (?)";

		try (PreparedStatement selectStmt = dbConnect.prepareStatement(selectSQL)) {
			selectStmt.setString(1, devName);
			ResultSet rs = selectStmt.executeQuery();
			if (rs.next()) {
				int existingId = rs.getInt("developer_id");
				System.out.println("Found existing developer '" + devName + "' with ID: " + existingId);
				return existingId;
			}
		} catch (SQLException e) {
			System.err.println("Error checking developer: " + devName);
			e.printStackTrace();
			return -1;
		}

		// If not found, insert new developer
		try (PreparedStatement insertStmt = dbConnect.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
			insertStmt.setString(1, devName);
			int affectedRows = insertStmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Inserting developer failed, no rows affected.");
			}

			try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int newId = generatedKeys.getInt(1);
					System.out.println("Inserted new developer '" + devName + "' with ID: " + newId);
					return newId;
				} else {
					throw new SQLException("Inserting developer failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error inserting developer: " + devName);
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Deletes a game from the database by its game ID.
	 * 
	 * @param gameId The ID of the game to delete
	 * @return true if the game was deleted successfully, false otherwise
	 */
	public boolean deleteGameById(int gameId) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return false;
		}

		String deleteQuery = "DELETE FROM game_information WHERE game_id = ?";

		try (PreparedStatement stmt = dbConnect.prepareStatement(deleteQuery)) {
			stmt.setInt(1, gameId);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates a game record and its related genres, platforms, and developers in
	 * the database.
	 *
	 * @param game The GameModel object containing updated game details and
	 *             relations
	 * @return true if the update was successful; false otherwise
	 */

	public Boolean updateGame(GameModel game) {
		if (dbConnect == null) {
			System.err.println("Database connection is not available");
			return false;
		}

		// SQL queries
		String updateGameInfo = "UPDATE game_information SET game_title = ?, game_description = ?, game_publisher = ?, game_released_date = ?, game_price = ?, game_rating = ? WHERE game_id = ?";
		String deleteGameGenres = "DELETE FROM game_genres WHERE game_id = ?";
		String deleteGamePlatforms = "DELETE FROM game_platforms WHERE game_id = ?";
		String deleteGameDevelopers = "DELETE FROM game_developers WHERE game_id = ?";
		String insertGameGenre = "INSERT INTO game_genres (game_id, genre_id) VALUES (?, ?)";
		String insertGamePlatform = "INSERT INTO game_platforms (game_id, platform_id) VALUES (?, ?)";
		String insertGameDeveloper = "INSERT INTO game_developers (game_id, developer_id) VALUES (?, ?)";

		try {
			// Update game information
			try (PreparedStatement updateStmt = dbConnect.prepareStatement(updateGameInfo)) {
				updateStmt.setString(1, game.getGameTitle());
				updateStmt.setString(2, game.getGameDescription());
				updateStmt.setString(3, game.getGamePublisher());
				updateStmt.setDate(4, game.getGameReleasedDate());
				updateStmt.setFloat(5, game.getGamePrice());
				updateStmt.setFloat(6, game.getGameRating());
				updateStmt.setInt(7, game.getGameId());
				int affectedRows = updateStmt.executeUpdate();
				if (affectedRows == 0) {
					System.err.println("Game update failed. No rows affected.");
					return false;
				}
				System.out.println("Game information updated.");
			}

			try (PreparedStatement deleteGenresStmt = dbConnect.prepareStatement(deleteGameGenres);
					PreparedStatement deletePlatformsStmt = dbConnect.prepareStatement(deleteGamePlatforms);
					PreparedStatement deleteDevelopersStmt = dbConnect.prepareStatement(deleteGameDevelopers)) {

				deleteGenresStmt.setInt(1, game.getGameId());
				deleteGenresStmt.executeUpdate();
				deletePlatformsStmt.setInt(1, game.getGameId());
				deletePlatformsStmt.executeUpdate();
				deleteDevelopersStmt.setInt(1, game.getGameId());
				deleteDevelopersStmt.executeUpdate();

				System.out.println("Existing genres, platforms, and developers deleted.");
			}

			// Insert updated genres
			for (String genreName : game.getGameGenres().split(",")) {
				int genreId = getGenreIdByName(genreName.trim());
				if (genreId != -1) {
					try (PreparedStatement insertStmt = dbConnect.prepareStatement(insertGameGenre)) {
						insertStmt.setInt(1, game.getGameId());
						insertStmt.setInt(2, genreId);
						insertStmt.executeUpdate();
						System.out.println("Genre linked: " + genreName.trim() + " (ID: " + genreId + ")");
					}
				} else {
					System.out.println("Genre not found: " + genreName.trim());
				}
			}

			// Insert updated platforms
			for (String platformName : game.getGamePlatforms().split(",")) {
				int platformId = getPlatformIdByName(platformName.trim());
				if (platformId != -1) {
					try (PreparedStatement insertStmt = dbConnect.prepareStatement(insertGamePlatform)) {
						insertStmt.setInt(1, game.getGameId());
						insertStmt.setInt(2, platformId);
						insertStmt.executeUpdate();
						System.out.println("Platform linked: " + platformName.trim() + " (ID: " + platformId + ")");
					}
				} else {
					System.out.println("Platform not found: " + platformName.trim());
				}
			}

			// Insert updated developers
			for (String devName : game.getGameDevelopers().split(",")) {
				int devId = getOrCreateDeveloperId(devName.trim());
				if (devId != -1) {
					try (PreparedStatement insertStmt = dbConnect.prepareStatement(insertGameDeveloper)) {
						insertStmt.setInt(1, game.getGameId());
						insertStmt.setInt(2, devId);
						insertStmt.executeUpdate();
						System.out.println("Developer linked: " + devName.trim() + " (ID: " + devId + ")");
					}
				} else {
					System.out.println("Failed to create or find developer: " + devName.trim());
				}
			}

			System.out.println("Game updated successfully with all relations!");
			return true;

		} catch (SQLException e) {
			System.err.println("SQL Exception during game update:");
			e.printStackTrace();
			return false;
		}
	}
}
