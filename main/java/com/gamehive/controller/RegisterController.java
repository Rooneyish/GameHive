package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import com.gamehive.util.PasswordUtil;
import com.gamehive.model.UserModel;
import com.gamehive.service.RegisterService;
import com.gamehive.util.ValidationUtil;

/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final RegisterService registerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		this.registerService = new RegisterService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			UserModel userModel = extractUserModel(request); 
			
			String validationError = validateUserModel(userModel);
			
	        if (validationError != null) {
	            handleError(request, response, validationError);
	            return;
	        }
	        
	        String plainPassword = userModel.getPassword();
            String username = userModel.getUsername();
            
            String encryptedPassword = PasswordUtil.encrypt(username, plainPassword);
            
            userModel.setPassword(encryptedPassword);
			
			Boolean isAdded = registerService.registerUser(userModel);
			
			if(isAdded == null) {
				handleError(request, response, "Our service is under maintenance. Please try again later.");
			}
			else if (isAdded) {
				handleSuccess(request, response, "Account Register Successfully!", "/WEB-INF/pages/Login.jsp");
			}
			else {
				handleError(request, response, "Couldn't Register your account.");
			}
		} catch (Exception e) {
			handleError(request, response, "An unexpected error occured, please try again.");
		}
	}

	
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
		System.out.print(message);
	}
	
	private String validateUserModel(UserModel user) {
	    if (user.getUsername() == null || !ValidationUtil.isAlphanumeric(user.getUsername())) {
	        return "Username must contain only alpha-numeric values.";
	    }

	    if (user.getUserEmail() == null || !ValidationUtil.isEmail(user.getUserEmail())) {
	        return "Please enter a valid email address.";
	    }

	    if (user.getPassword() == null || !ValidationUtil.isValidPassword(user.getPassword())) {
	        return "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.";
	    }

	    if (user.getGender() == null || !ValidationUtil.isGenderMatches(user.getGender())) {
	        return "Gender must be either 'male' or 'female'.";
	    }

	    if (user.getDob() == null || !ValidationUtil.isValidAge(user.getDob())) {
	        return "Gamer must be above 10 years";
	    }

	    return null; 
	}

	
	/**
	 * Extracts User credentials from the Registration form
	 * 
	 * @return UserModel 
	 */
	private UserModel extractUserModel(HttpServletRequest request) {
	    UserModel user = new UserModel();
	    user.setUsername(request.getParameter("username"));
	    user.setDob(Date.valueOf(request.getParameter("dob")));
	    user.setGender(request.getParameter("gender"));
	    user.setUserEmail(request.getParameter("user-email"));
	    user.setPassword( request.getParameter("password"));
	    user.setCreatedDate(new Date(System.currentTimeMillis()));
	    return user;
	}

}
