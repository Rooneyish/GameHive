package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.gamehive.model.GameModel;
import com.gamehive.service.AdminService;
import com.gamehive.service.GameService;

/**
 * Servlet implementation class AddGameController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addGame" })
public class AddGameController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddGameController() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		String[] genres = request.getParameterValues("genre[]");
		String[] platforms = request.getParameterValues("platform[]");

		String genreStr = String.join(",", genres != null ? genres : new String[0]);
		String platformStr = String.join(",", platforms != null ? platforms : new String[0]);

		try {
			GameModel game = new GameModel(0, title, description, publisher, java.sql.Date.valueOf(releasedDate),
					Float.parseFloat(price), Float.parseFloat(rating), developers, genreStr, platformStr);

			GameService service = new GameService();
			boolean success = service.insertGame(game);

			if (success) {
				response.sendRedirect("admin?success=Game+Added+Successfully");
			} else {
				handleError(request, response, "Game could not be added.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Invalid input.");
			request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
		}
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(req, resp);
		System.out.print(message);
	}

}
