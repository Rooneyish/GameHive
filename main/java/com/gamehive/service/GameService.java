package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

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
			
			if(result.next()) {
				int gId = result.getInt("game_id");
				String gTitle = result.getString("game_title");
				String gDescription = result.getString("game_description");
				String gPublisher = result.getString("game_publisher");
				String gDeveloper = result.getString("developer");
				String gGenre = result.getString("genre");
				String gPlatform = result.getString("platform");
				Date gReleasedDate = result.getDate("game_released_date");
				float gRating= result.getFloat("game_rating");
				float gPrice= result.getFloat("game_price");
			
				return new GameModel(gId, gTitle, gDescription, gPublisher, gDeveloper, gGenre, gPlatform, gReleasedDate,
						gPrice, gRating);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return null;
	}
	
}
