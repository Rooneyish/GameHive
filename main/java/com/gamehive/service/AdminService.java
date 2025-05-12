package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gamehive.config.DBconfig;
import com.gamehive.model.GameModel;

/**
 * @author Ronish Prajapati 
 * LUM-ID 23048584
 */

public class AdminService {
	private Connection dbConnect;

	public AdminService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error:" + e.getMessage());
			e.printStackTrace();
		}
	}

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
	        System.err.println("SQL Exception during game insert:");
	        e.printStackTrace();
	        return null;
	    }
	}

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
	            System.out.println("⚠ Platform not found in DB: " + platformName);
	            return -1;
	        }
	    } catch (SQLException e) {
	        System.err.println("Error fetching platform ID for: " + platformName);
	        e.printStackTrace();
	        return -1;
	    }
	}

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
}
