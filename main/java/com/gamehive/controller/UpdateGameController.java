package com.gamehive.controller;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gamehive.model.GameModel;
import com.gamehive.service.GameService;

/**
 * Servlet implementation class UpdateGameController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updateGame" })
public class UpdateGameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final GameService gameService = new GameService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateGameController() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int gameId = Integer.parseInt(request.getParameter("gameId"));
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
			GameModel game = new GameModel(gameId , title, description, publisher, java.sql.Date.valueOf(releasedDate),
					Float.parseFloat(price), Float.parseFloat(rating), developers, genreStr, platformStr);

			GameService service = new GameService();
			boolean success = service.updateGame(game);

			if (success) {
				response.sendRedirect("admin?success=Game+update+successfull!");
			} else {
				request.setAttribute("error", "Failed to update game.");
				request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Invalid input.");
			request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
		}
	}
}
