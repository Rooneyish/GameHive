package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.gamehive.model.LoginModel;
import com.gamehive.model.UserModel;
import com.gamehive.service.LoginService;
import com.gamehive.util.CookieUtil;
import com.gamehive.util.SessionUtil;
import com.gamehive.util.StringUtil;

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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			LoginModel loginModel = new LoginModel(username, password);
			int loginResult = loginService.getUserLoginInfo(loginModel);
			int userRoleResult = loginService.getUserRoleInfo(loginModel);

			if (loginResult == 1) {
				SessionUtil.setAttribute(request, StringUtil.USERNAME, username);
				CookieUtil.addCookie(response, StringUtil.USER, username, 30 * 60);

				if (userRoleResult == 1) {
					SessionUtil.setAttribute(request, "role", "admin");
					response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_ADMIN);
				} else if (userRoleResult == 0) {
					SessionUtil.setAttribute(request, "role", "gamer");
					response.sendRedirect(request.getContextPath() + StringUtil.PAGE_URL_GAMER_PORTAL);
				} else {
					request.setAttribute("error", "User role not found.");
				    request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Invalid username or password");
				request.setAttribute("username", username);
				request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
			}

		} catch (Exception e) {
			handleError(request, response, "An unexpected error occured, please try again.");
		}
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(req, resp);
	}

}
