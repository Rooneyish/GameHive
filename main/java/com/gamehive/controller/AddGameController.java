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
		float rating = Float.parseFloat(request.getParameter("rating"));
		float price = Float.parseFloat(request.getParameter("price"));

		String[] genres = request.getParameterValues("genre[]");
		String[] platforms = request.getParameterValues("platform[]");

		String genreStr = String.join(",", genres != null ? genres : new String[0]);
		String platformStr = String.join(",", platforms != null ? platforms : new String[0]);
		
		try {
		    if (rating > 5.0 || rating < 0.0) {
		        response.sendRedirect("admin?error=Rating+should+be+between+0+and+5");
		        return;
		    }
		} catch (NumberFormatException e) {
		    response.sendRedirect("admin?error=Invalid+rating+value");
		    return;
		}
		
		try {
		    if (price < 0.0) {
		        response.sendRedirect("admin?error=Price+cannot+be+negative.");
		        return;
		    }
		} catch (NumberFormatException e) {
		    response.sendRedirect("admin?error=Invalid+rating+value");
		    return;
		}

		try {
			GameModel game = new GameModel(0, title, description, publisher, java.sql.Date.valueOf(releasedDate),
					price, rating, developers, genreStr, platformStr);

			GameService service = new GameService();
			boolean success = service.insertGame(game);

			if (Boolean.TRUE.equals(success)) {
			    response.sendRedirect("admin?success=Game+Added+Successfully");
			} else if (Boolean.FALSE.equals(success)) {
			    handleError(request, response, "A game with this title already exists.");
			} else {
			    handleError(request, response, "An error occurred while adding the game. Please check the inputs.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Invalid input.");
			request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
		}
	}

	/**
	 * Handles errors that occur during game addition.
	 * Retrieves the existing game list and forwards the request to the Admin page
	 * with an appropriate error message.
	 * 
	 * @param req     The HttpServletRequest object.
	 * @param resp    The HttpServletResponse object.
	 * @param message The error message to be displayed.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an I/O error occurs.
	 */
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		GameService gameService = new GameService();
		req.setAttribute("games", gameService.getAllGameInfo()); 
		req.setAttribute("error", message);
		req.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(req, resp);
		System.out.print(message);
	}

}
