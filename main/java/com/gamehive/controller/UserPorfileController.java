package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */
import java.sql.Date;

import com.gamehive.model.UserModel;
import com.gamehive.service.UserProfileService;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

/**
 * Servlet implementation class UserPorfile
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class UserPorfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserPorfileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      Handles HTTP POST requests for updating user profile
	 *      information.
	 *
	 *      Retrieves updated user details from the request, verifies the current
	 *      session user, checks if the new email is already taken by another user,
	 *      and validates the user's password. If validation passes, updates the
	 *      user profile using UserProfileService. Updates session attributes and
	 *      sets success or error messages accordingly, then forwards to the
	 *      UserProfile JSP page.
	 *
	 * @param request  the HttpServletRequest containing profile update data
	 * @param response the HttpServletResponse used to forward the response
	 * @throws ServletException if a servlet-specific error occurs during forwarding
	 * @throws IOException      if an I/O error occurs during forwarding
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("edit-username");
		String email = request.getParameter("edit-email");
		Date dob = Date.valueOf(request.getParameter("edit-dob"));
		String gender = request.getParameter("edit-gender");
		String password = request.getParameter("edit-password");

		String currentUser = (String) request.getSession().getAttribute("username");

		if (currentUser == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		UserProfileService userProfileService = new UserProfileService();

		if (userProfileService.isEmailTakenByAnotherUser(email, currentUser)) {
			request.setAttribute("error", "This email address is already used by another account.");
			request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
			return;
		}

		boolean isPasswordCorrect = userProfileService.verifyUser(username, password);

		if (!isPasswordCorrect) {
			request.setAttribute("error", "Incorrect password. Profile not updated.");
			request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
			return;
		}

		UserModel user = new UserModel();
		user.setUsername(username);
		user.setUserEmail(email);
		user.setDob(dob);
		user.setGender(gender);

		UserModel updatedUser = userProfileService.updateUserInfo(user);

		if (updatedUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user_email", updatedUser.getUserEmail());
			session.setAttribute("username", updatedUser.getUsername());
			request.setAttribute("success", "Profile updated successfully.");
			request.getSession().setAttribute("loggedUser", username);
		} else {
			request.setAttribute("error", "Failed to update profile. Try again.");
		}

		request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
	}

}
