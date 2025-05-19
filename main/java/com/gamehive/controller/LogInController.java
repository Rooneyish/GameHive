package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.gamehive.model.LoginModel;
import com.gamehive.model.UserModel;
import com.gamehive.service.LoginService;
import com.gamehive.util.CookieUtil;
import com.gamehive.util.SessionUtil;
import com.gamehive.util.StringUtil;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

/**
 * Servlet implementation class LogInController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LoginService loginService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInController() {
		super();
		this.loginService = new LoginService();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * Handles POST requests for logging in.
	 * Validates user credentials and starts a session on successful login.
	 * Redirects the user to the appropriate dashboard based on role.
	 *
	 * @param request  The HttpServletRequest object.
	 * @param response The HttpServletResponse object.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an input or output error is detected.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter(StringUtil.USERNAME); 
		String password = request.getParameter(StringUtil.PASSWORD); 

		try {
			if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
				handleLoginError(request, response, "Username and password cannot be empty.", username);
				return;
			}

			LoginModel loginModel = new LoginModel(username, password);

			UserModel loggedInUser = loginService.loginUser(loginModel);


			if (loggedInUser != null) {
				String actualUsername = loggedInUser.getUsername(); 
				String userRole = loggedInUser.getUserRole(); 

				System.out.println("Controller: Login successful for " + actualUsername + ", Role: " + userRole);

				SessionUtil.setAttribute(request, StringUtil.USERNAME, actualUsername);
				SessionUtil.setAttribute(request, StringUtil.USER_ROLE, userRole); 
				SessionUtil.setAttribute(request, StringUtil.USER_EMAIL, loggedInUser.getUserEmail());
				SessionUtil.setAttribute(request, StringUtil.CREATED_DATE, (Object)loggedInUser.getCreatedDate());

				CookieUtil.addCookie(response, StringUtil.USER_COOKIE_NAME, actualUsername, 30 * 60);

				if (StringUtil.ROLE_ADMIN.equalsIgnoreCase(userRole)) { 
					response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_ADMIN);
				} else if (StringUtil.ROLE_GAMER.equalsIgnoreCase(userRole)) { 
					response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_GAMER_PORTAL);
				} else {
					System.err.println("Unknown role encountered: " + userRole + " for user: " + actualUsername);
					SessionUtil.invalidateSession(request);
					CookieUtil.deleteCookie(response, StringUtil.USER_COOKIE_NAME);
					handleLoginError(request, response,
							"Login successful, but your user role is undefined. Please contact support.", username);
				}

			} else {
				System.out.println("Controller: Login failed for username attempt: " + username);
				handleLoginError(request, response, StringUtil.MESSAGE_ERROR_LOGIN, username);
			}

		} catch (Exception e) {
			System.err.println("Unexpected error during login POST request processing: " + e.getMessage());
			e.printStackTrace();
			handleLoginError(request, response, StringUtil.MESSAGE_ERROR_GENERIC, username); 
		}
	}

	private void handleLoginError(HttpServletRequest request, HttpServletResponse response, String message,
			String username) throws ServletException, IOException {
		request.setAttribute(StringUtil.ERROR_MESSAGE_ATTRIBUTE, message); 
		if (username != null) {
			request.setAttribute(StringUtil.USERNAME, username);
		}

		request.getRequestDispatcher(StringUtil.PAGE_URL_LOGIN).forward(request, response);
	}

}
