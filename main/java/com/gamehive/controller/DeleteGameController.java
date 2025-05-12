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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String gameIdJSP = request.getParameter("gameId");
		
		try {
			int gameId = Integer.parseInt(gameIdJSP);
			
			boolean isDeleted = gameService.deleteGameById(gameId);
			
			if (isDeleted) {
                response.sendRedirect("admin?msg=Game+deleted+successfully");
            } else {
                response.sendRedirect("admin?error=Failed+to+delete+game");
            }
		}catch (NumberFormatException e) {
            response.sendRedirect("admin?error=Invalid+Game+ID");
        }
	}

}
