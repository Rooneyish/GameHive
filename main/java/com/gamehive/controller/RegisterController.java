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
	 * 
	 *      Handles user registration via HTTP POST.
	 *
	 *      Extracts user details from the request, validates input, encrypts the
	 *      password, and attempts to register the user. Responds with success or
	 *      error messages accordingly.
	 *
	 * @param request  the HttpServletRequest containing the registration data
	 * @param response the HttpServletResponse used to send feedback to the client
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs while processing the request
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

			if (isAdded == null) {
				handleError(request, response,
						"We're currently experiencing technical issues. Please try again later.");
			} else if (isAdded) {
				handleSuccess(request, response, "Your account has been created successfully! You can now log in.",
						"/WEB-INF/pages/Login.jsp");
			} else {
				handleError(request, response,
						"The username or email address is already registered. Please try a different one.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(request, response, "An unexpected error occurred during registration. Please try again.");
		}
	}

	/**
	 * Sets a success message as a request attribute and forwards the request and
	 * response to the specified JSP page for display.
	 *
	 * @param req          the HttpServletRequest object
	 * @param resp         the HttpServletResponse object
	 * @param message      the success message to display
	 * @param redirectPage the path to the JSP page to forward to
	 * @throws ServletException if a servlet-specific error occurs during forwarding
	 * @throws IOException      if an I/O error occurs during forwarding
	 */
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	/**
	 * Sets an error message as a request attribute and forwards the request and
	 * response to the registration JSP page for error display. Also logs the error
	 * message to the server console.
	 *
	 * @param req     the HttpServletRequest object
	 * @param resp    the HttpServletResponse object
	 * @param message the error message to display and log
	 * @throws ServletException if a servlet-specific error occurs during forwarding
	 * @throws IOException      if an I/O error occurs during forwarding
	 */
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
		System.out.print(message);
	}

	/**
	 * Validates the fields of the given UserModel.
	 * 
	 * Checks that username is alphanumeric, email is valid, password meets
	 * complexity rules, gender is either 'male' or 'female', and age is above 10
	 * years.
	 * 
	 * @param user the UserModel to validate
	 * @return a validation error message if any validation fails; otherwise, null
	 *         if all fields are valid
	 */
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
	 * Extracts user registration details from the HttpServletRequest and populates
	 * a UserModel object.
	 *
	 * @param request the HttpServletRequest containing user input parameters
	 * @return a UserModel object populated with the extracted data
	 */

	private UserModel extractUserModel(HttpServletRequest request) {
		UserModel user = new UserModel();
		user.setUsername(request.getParameter("username"));
		user.setDob(Date.valueOf(request.getParameter("dob")));
		user.setGender(request.getParameter("gender"));
		user.setUserEmail(request.getParameter("user-email"));
		user.setPassword(request.getParameter("password"));
		user.setCreatedDate(new Date(System.currentTimeMillis()));
		return user;
	}

}
