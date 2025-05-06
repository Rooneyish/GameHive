package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gamehive.config.DBconfig;
import com.gamehive.model.UserModel;
import com.gamehive.util.PasswordUtil;

/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

public class UserProfileService {
	private Connection dbConnect;
	
	public UserProfileService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		}catch(SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error: "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public UserModel updateUserInfo(UserModel user) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}
		
		String updateQuery = "UPDATE user_information SET username=?, user_email=?, date_of_birth=?, gender=? WHERE username = ?";
		
		try(PreparedStatement preparedStatement = dbConnect.prepareStatement(updateQuery)){
			preparedStatement.setString(1, user.getUsername());
	        preparedStatement.setString(2, user.getUserEmail());
	        preparedStatement.setDate(3, user.getDob());
	        preparedStatement.setString(4, user.getGender());
	        preparedStatement.setString(5, user.getUsername());
			
			int rowAffected = preparedStatement.executeUpdate();
			
			if (rowAffected > 0) {
				return user;
			}else {
				System.err.print("User not found in database.");
				return null;
			}
		}catch(SQLException e) {
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
            	String dbPassword= result.getString("user_password");
            	System.out.println("Verifying user: " + username);
            	System.out.println("Password in DB (encrypted): " + dbPassword);
				try {
					String decryptedPassword= PasswordUtil.decrypt(dbPassword, dbUsername);
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
}
