package com.gamehive.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.gamehive.config.DBconfig;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

public class StringUtil {
	private Connection dbConnect;

	public StringUtil() {
		try {
			this.dbConnect = DBconfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Database connection error:" + e.getMessage());
			e.printStackTrace();
		}
	}


	// Start: Parameter names
	public static final String USERNAME = "username";
	public static final String USER_EMAIL = "user_email";
	public static final String CREATED_DATE = "created_date";
	public static final String BIRTHDAY = "dob";
	public static final String GENDER = "gender";
	public static final String USER_ROLE = "role";
	public static final String PASSWORD = "password";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_GAMER = "gamer";
	// End: Parameter names

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";
	public static final String MESSAGE_ERROR_USER_ROLE_NOT_FOUND = "User Role is undefine.";
    public static final String MESSAGE_ERROR_GENERIC = "An unexpected error occurred. Please try again later.";
    public static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    public static final String USER_COOKIE_NAME = "gamehiveUser";
	
	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";

	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String PAGE_URL_LOGIN = "/WEB-INF/pages/Login.jsp";
	public static final String PAGE_URL_REGISTER = "/WEB-INF/pages/Register.jsp";
	public static final String PAGE_URL_HOME = "/WEB-INF/pages/Home.jsp";
	public static final String PAGE_URL_FOOTER = "/WEB-INF/pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "/WEB-INF/pages/header.jsp";
	public static final String PAGE_URL_ADMIN = "/admin"; 
	public static final String PAGE_URL_GAMER_PORTAL = "/gamerportal";
	public static final String URL_LOGIN = "/login.jsp";
	public static final String URL_INDEX = "/home.jsp";
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/register";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_ADMIN="/admin";
	// End: Servlet Route

	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
}
