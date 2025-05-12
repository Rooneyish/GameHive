package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public GameModel getGameInformation() {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}

		String selectQuery = "SELECT gi.game_id, gi.game_title, gi.game_description, gi.game_publisher, d.developer, g.genre, p.platform, gi.game_released_date, gi.game_rating, gi.game_price FROM game_information as gi LEFT JOIN game_developers as gd ON gi.game_id = gd.game_id LEFT JOIN developers as d ON gd.developer_id = d.developer_id LEFT JOIN game_genres as gg ON gi.game_id = gg.game_id LEFT JOIN genres as g ON gg.genre_id = g.genre_id LEFT JOIN game_platforms as gp ON gi.game_id = gp.game_id LEFT JOIN platforms as p ON gp.platform_id = p.platform_id;";

		try (PreparedStatement stmt = dbConnect.prepareStatement(selectQuery)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				int gId = result.getInt("game_id");
				String gTitle = result.getString("game_title");
				String gDescription = result.getString("game_description");
				String gPublisher = result.getString("game_publisher");
				String gDevelopers = result.getString("developer");
				String gGenres = result.getString("genre");
				String gPlatforms = result.getString("platform");
				Date gReleasedDate = result.getDate("game_released_date");
				float gRating = result.getFloat("game_rating");
				float gPrice = result.getFloat("game_price");

				return new GameModel( gId, gTitle, gDescription, gPublisher, gReleasedDate,
						gPrice, gRating, gDevelopers, gGenres, gPlatforms);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

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

				GameModel game = new GameModel( gId, gTitle, gDescription, gPublisher, gReleasedDate,
						gPrice, gRating, gDevelopers, gGenres, gPlatforms);
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}
	
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
}
