package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gamehive.config.DBconfig;
import com.gamehive.model.LoginModel;
import com.gamehive.model.UserModel;
import com.gamehive.util.PasswordUtil;

/**
 * @author Ronish Prajapati LUM-ID 23048584
 */

public class LoginService {
	private Connection dbConnect;

	public LoginService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Attempts to log in a user by validating the username and password against the
	 * database.
	 *
	 * @param loginModel Contains the username and password input by the user
	 *                   attempting login.
	 * @return A UserModel object with user details if login is successful; null
	 *         otherwise.
	 */

	public UserModel loginUser(LoginModel loginModel) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}

		String query = "SELECT username, user_password, user_role, user_email, date_of_birth, gender, created_date "
				+ "FROM user_information WHERE username=?";

		System.out.println("LoginService: Attempting login for user: " + loginModel.getUsername());
		try (PreparedStatement stmt = dbConnect.prepareStatement(query)) {
			stmt.setString(1, loginModel.getUsername());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				String dbUsername = result.getString("username");
				String dbPasswordHash = result.getString("user_password");
				String dbUserRole = result.getString("user_role");

				boolean passwordMatches = false;
				try {
					passwordMatches = PasswordUtil.decrypt(dbPasswordHash, dbUsername).equals(loginModel.getPassword());
				} catch (Exception e) {
					System.err.println("Error during password decryption/validation for user " + dbUsername + ": "
							+ e.getMessage());
					return null;
				}

				if (passwordMatches) {
					System.out.println("Login successful for user: " + dbUsername + " with role: " + dbUserRole);

					UserModel loggedInUser = new UserModel();
					loggedInUser.setUsername(dbUsername);
					loggedInUser.setUserRole(dbUserRole);
					loggedInUser.setUserEmail(result.getString("user_email"));
					loggedInUser.setDob(result.getDate("date_of_birth"));
					loggedInUser.setGender(result.getString("gender"));
					loggedInUser.setCreatedDate(result.getDate("created_date"));

					return loggedInUser;

				} else {
					System.out.println("Password validation failed for user: " + dbUsername);
					return null;
				}
			} else {
				System.out.println("User not found: " + loginModel.getUsername());
				return null;
			}
		} catch (SQLException e) {
			System.err.println("SQL Error during login for user " + loginModel.getUsername() + ": " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
