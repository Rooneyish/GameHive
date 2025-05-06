package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */
import java.sql.Date;

import com.gamehive.model.UserModel;
import com.gamehive.service.UserProfileService;

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
		
		UserProfileService userProfilService = new UserProfileService();
		boolean isPasswordCorrect = userProfilService.verifyUser(username, password);

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
	    
	    UserModel updatedUser = userProfilService.updateUserInfo(user);
	    
	    if (updatedUser != null) {
	        request.setAttribute("success", "Profile updated successfully.");
	        request.getSession().setAttribute("loggedUser", username); 
	    } else {
	        request.setAttribute("error", "Failed to update profile. Try again.");
	    }

	    request.getRequestDispatcher("/WEB-INF/pages/UserProfile.jsp").forward(request, response);
	}
}
