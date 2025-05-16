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

public class GameFilterService {
	private Connection dbConnect;

	public GameFilterService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<GameModel> getGamesByGenre(String genreName) {
		List<GameModel> games = new ArrayList<>();

		String selectQuery = "SELECT gi.game_id, gi.game_title, gi.game_description, gi.game_publisher, GROUP_CONCAT(DISTINCT d.developer SEPARATOR ', ') AS developers, GROUP_CONCAT(DISTINCT g.genre SEPARATOR ', ') AS genres, GROUP_CONCAT(DISTINCT p.platform SEPARATOR ', ') AS platforms, gi.game_released_date, gi.game_rating, gi.game_price FROM game_information gi LEFT JOIN game_developers gd ON gi.game_id = gd.game_id LEFT JOIN developers d ON gd.developer_id = d.developer_id LEFT JOIN game_genres gg ON gi.game_id = gg.game_id LEFT JOIN genres g ON gg.genre_id = g.genre_id LEFT JOIN game_platforms gp ON gi.game_id = gp.game_id LEFT JOIN platforms p ON gp.platform_id = p.platform_id WHERE LOWER(g.genre) LIKE LOWER(?) GROUP BY gi.game_id;";

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			stmt.setString(1, genreName);
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
	
	public List<GameModel> getGamesByPlatform(String platformName) {
		List<GameModel> games = new ArrayList<>();

		String selectQuery = "SELECT gi.game_id, gi.game_title, gi.game_description, gi.game_publisher, GROUP_CONCAT(DISTINCT d.developer SEPARATOR ', ') AS developers, GROUP_CONCAT(DISTINCT g.genre SEPARATOR ', ') AS genres, GROUP_CONCAT(DISTINCT p.platform SEPARATOR ', ') AS platforms, gi.game_released_date, gi.game_rating, gi.game_price FROM game_information gi LEFT JOIN game_developers gd ON gi.game_id = gd.game_id LEFT JOIN developers d ON gd.developer_id = d.developer_id LEFT JOIN game_genres gg ON gi.game_id = gg.game_id LEFT JOIN genres g ON gg.genre_id = g.genre_id LEFT JOIN game_platforms gp ON gi.game_id = gp.game_id LEFT JOIN platforms p ON gp.platform_id = p.platform_id WHERE LOWER(p.platform) LIKE LOWER(?) GROUP BY gi.game_id;";
		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			stmt.setString(1, platformName);
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
