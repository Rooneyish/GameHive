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
}
