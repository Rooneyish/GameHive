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

import com.gamehive.service.GameService;

/**
 * Servlet implementation class DeleteGameController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/deleteGame" })
public class DeleteGameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final GameService gameService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGameController() {
        super();
        // TODO Auto-generated constructor stub
        this.gameService = new GameService();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Handles HTTP POST requests to delete a game by its ID.
	 * Retrieves the game ID from the request, validates it, and calls the service to delete the game.
	 * Redirects to the admin page with appropriate success or error messages.
	 *
	 * @param request  The HttpServletRequest object containing the game ID parameter.
	 * @param response The HttpServletResponse object used to redirect with result messages.
	 * @throws ServletException If a servlet-specific error occurs.
	 * @throws IOException      If an input or output error occurs.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String gameIdJSP = request.getParameter("gameId");
		
		try {
			int gameId = Integer.parseInt(gameIdJSP);
			
			boolean isDeleted = gameService.deleteGameById(gameId);
			
			if (isDeleted) {
                response.sendRedirect("admin?success=Game+deleted+successfully");
            } else {
                response.sendRedirect("admin?error=Failed+to+delete+game");
            }
		}catch (NumberFormatException e) {
            response.sendRedirect("admin?error=Invalid+Game+ID");
        }
	}

}
