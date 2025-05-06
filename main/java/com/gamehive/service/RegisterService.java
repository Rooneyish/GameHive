package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gamehive.config.DBconfig;
import com.gamehive.model.UserModel;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

public class RegisterService {
	private Connection dbConnect;

	public RegisterService() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public Boolean registerUser(UserModel userModel) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}
		String insertQuery ="INSERT INTO user_information (username, date_of_birth, gender, user_email, user_password, created_date, user_role) VALUES  (?,?,?,?,?,?,?)";
		try (PreparedStatement insertStmt = dbConnect.prepareStatement(insertQuery)) {
			
			insertStmt.setString(1, userModel.getUsername());
			insertStmt.setDate(2, userModel.getDob());
			insertStmt.setString(3, userModel.getGender());
			insertStmt.setString(4, userModel.getUserEmail());
			insertStmt.setString(5, userModel.getPassword());
			insertStmt.setDate(6, userModel.getCreatedDate());
			insertStmt.setString(7, "gamer");

			return insertStmt.executeUpdate() >0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
