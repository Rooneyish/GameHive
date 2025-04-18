package com.gamehive.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gamehive.config.DBconfig;
import com.gamehive.model.LoginModel;
import com.gamehive.model.UserModel;

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

	public Boolean loginUser(UserModel userModel) {
		if (dbConnect == null) {
			System.err.print("Database connection is not available");
			return null;
		}

		String query = "SELECT username, password, user_role FROM user_information WHERE username=?";
		try (PreparedStatement stmt = dbConnect.prepareStatement(query)) {
			stmt.setString(1, userModel.getUsername());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				String dbPassword = result.getString("password");
				String userInputPassword = userModel.getPassword();

				if (dbPassword.equals(userInputPassword)) {
					userModel.setUserRole(result.getString("user_role"));
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return false;
	}
	
	public int getUserLoginInfo(LoginModel loginModel) {
	    try {
	        PreparedStatement st = dbConnect.prepareStatement("SELECT * FROM user_information WHERE username = ?");
	        st.setString(1, loginModel.getUsername());

	        ResultSet result = st.executeQuery();

	        if (result.next()) {
	            String userDb = result.getString("username");

	            String passwordDb = result.getString("username");
	            
	            if (userDb.equals(loginModel.getUsername()) 
	            		&& passwordDb.equals(loginModel.getPassword())) {
	                return 1;
	            } else {
	                return 0;
	            }
	        } else {
	            return -1;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return -2;
	    }
	}
}
