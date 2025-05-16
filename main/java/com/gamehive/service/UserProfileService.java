package com.gamehive.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gamehive.config.DBconfig;
import com.gamehive.model.UserModel;
import com.gamehive.util.PasswordUtil;

/**
 * @author Ronish Prajapati LUM-ID 23048584
 */

public class UserProfileService {
	private Connection dbConnect;

	public UserProfileService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public UserModel updateUserInfo(UserModel user) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}

		String selectQuery = "SELECT * FROM user_information WHERE username = ?";
		String updateQuery = "UPDATE user_information SET user_email=?, date_of_birth=?, gender=? WHERE username = ?";

		try (PreparedStatement selectStmt = dbConnect.prepareStatement(selectQuery);
				PreparedStatement updateStmt = dbConnect.prepareStatement(updateQuery)) {
			selectStmt.setString(1, user.getUsername());
			ResultSet result = selectStmt.executeQuery();

			if (result.next()) {
				String newEmail = user.getUserEmail() != null && !user.getUserEmail().isEmpty() ? user.getUserEmail()
						: result.getString("user_email");

				Date newDob = user.getDob() != null ? user.getDob() : result.getDate("date_of_birth");

				String newGender = user.getGender() != null && !user.getGender().isEmpty() ? user.getGender()
						: result.getString("gender");
				
	            updateStmt.setString(1, newEmail);
	            updateStmt.setDate(2, newDob);
	            updateStmt.setString(3, newGender);
	            updateStmt.setString(4, user.getUsername());
			}
			

			int rowAffected = updateStmt.executeUpdate();

			if (rowAffected > 0) {
				return user;
			} else {
				System.err.print("User not found in database.");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean verifyUser(String username, String enteredPassword) {
		System.out.println("Verify user called");
		String sql = "SELECT username, user_password FROM user_information WHERE username = ?";

		try (PreparedStatement preparedStatement = dbConnect.prepareStatement(sql)) {
			preparedStatement.setString(1, username);
			ResultSet result = preparedStatement.executeQuery();
			System.out.println("Reached Here");
			if (result.next()) {
				String dbUsername = result.getString("username");
				String dbPassword = result.getString("user_password");
				System.out.println("Verifying user: " + username);
				System.out.println("Password in DB (encrypted): " + dbPassword);
				try {
					String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);
					System.out.println("Password entered: " + enteredPassword);
					System.out.println("Password after decryption: " + decryptedPassword);
					return decryptedPassword.equals(enteredPassword);
				} catch (Exception e) {
					System.err.println("Error during password decryption/validation for user " + dbUsername + ": "
							+ e.getMessage());
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean isEmailTakenByAnotherUser(String email, String currentUsername) {
		String query = "SELECT COUNT(*) FROM user_information WHERE user_email = ? AND username <> ?";
		try (PreparedStatement stmt = dbConnect.prepareStatement(query)) {
			stmt.setString(1, email);
			stmt.setString(2, currentUsername);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public UserModel getUserByUsername(String username) {
	    if (dbConnect == null) {
	        System.err.print("Database connection is not available");
	        return null;
	    }
	    String query = "SELECT username, user_email, date_of_birth, gender FROM user_information WHERE username = ?";
	    try (PreparedStatement stmt = dbConnect.prepareStatement(query)) {
	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            UserModel user = new UserModel();
	            user.setUsername(rs.getString("username"));
	            user.setUserEmail(rs.getString("user_email"));
	            user.setDob(rs.getDate("date_of_birth"));
	            user.setGender(rs.getString("gender"));
	            return user;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
