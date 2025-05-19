package com.gamehive.service;

/**
 * @author Ronish Prajapati 
 * LUM-ID 23048584
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gamehive.config.DBconfig;
import com.gamehive.model.GameModel;

public class SortService {
	private Connection dbConnect;

	public SortService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves a list of games from the database with their associated developers,
	 * genres, and platforms, sorted according to the provided ORDER BY clause.
	 *
	 * @param orderByClause The SQL ORDER BY clause (e.g., "ORDER BY game_title
	 *                      ASC").
	 * @return A list of GameModel objects sorted as specified. Returns an empty
	 *         list if no games found or if a DB error occurs.
	 */
	public List<GameModel> getSortedGames(String orderByClause) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}

		List<GameModel> games = new ArrayList<>();

		String selectQuery = "SELECT gi.game_id, gi.game_title, gi.game_description, gi.game_publisher, GROUP_CONCAT(DISTINCT d.developer SEPARATOR ', ') AS developers, GROUP_CONCAT(DISTINCT g.genre SEPARATOR ', ') AS genres, GROUP_CONCAT(DISTINCT p.platform SEPARATOR ', ') AS platforms, gi.game_released_date, gi.game_rating, gi.game_price FROM game_information gi LEFT JOIN game_developers gd ON gi.game_id = gd.game_id LEFT JOIN developers d ON gd.developer_id = d.developer_id LEFT JOIN game_genres gg ON gi.game_id = gg.game_id LEFT JOIN genres g ON gg.genre_id = g.genre_id LEFT JOIN game_platforms gp ON gi.game_id = gp.game_id LEFT JOIN platforms p ON gp.platform_id = p.platform_id GROUP BY gi.game_id "
				+ orderByClause;

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				GameModel game = new GameModel(result.getInt("game_id"), result.getString("game_title"),
						result.getString("game_description"), result.getString("game_publisher"),
						result.getDate("game_released_date"), result.getFloat("game_price"),
						result.getFloat("game_rating"), result.getString("developers"), result.getString("genres"),
						result.getString("platforms"));
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return games;
	}
}
