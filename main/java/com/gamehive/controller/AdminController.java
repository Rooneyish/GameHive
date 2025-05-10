package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.gamehive.model.GameModel;
import com.gamehive.service.AdminService;
import com.gamehive.service.GameService;

/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

/**
 * Servlet implementation class AdminController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
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
		GameService gameService = new GameService();
		List<GameModel> games = gameService.getAllGameInfo();

		request.setAttribute("games", games);
		request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String title = request.getParameter("gameTitle");
	        String description = request.getParameter("gameDescription");
	        String publisher = request.getParameter("publisher");
	        String developers = request.getParameter("developers");
	        String releasedDate = request.getParameter("releasedDate");
	        String rating = request.getParameter("rating");
	        String price = request.getParameter("price");

	        // Retrieve checkboxes
	        String[] genres = request.getParameterValues("genre[]");
	        String[] platforms = request.getParameterValues("platform[]");

	        // Convert to comma-separated strings
	        String genreStr = String.join(",", genres != null ? genres : new String[0]);
	        String platformStr = String.join(",", platforms != null ? platforms : new String[0]);

	        try {
	            GameModel game = new GameModel(
	                0,
	                title,
	                description,
	                publisher,
	                java.sql.Date.valueOf(releasedDate),
	                Float.parseFloat(price),
	                Float.parseFloat(rating),
	                developers,
	                genreStr,
	                platformStr
	            );

	            AdminService service = new AdminService();
	            boolean success = service.insertGame(game);

	            if (success) {
	                response.sendRedirect("admin?success=true");
	            } else {
	                request.setAttribute("error", "Failed to add game.");
	                request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Invalid input.");
	            request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
	        }
	}

}
