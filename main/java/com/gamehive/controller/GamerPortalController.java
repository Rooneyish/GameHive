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
import java.util.List;

import com.gamehive.model.GameModel;
import com.gamehive.service.GameFilterService;
import com.gamehive.service.GameService;

/**
 * Servlet implementation class GamerPortalController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/gamerportal" })
public class GamerPortalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GamerPortalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchValue = request.getParameter("searchValue");
		String filterOption = request.getParameter("option");
		
		GameService gameService = new GameService();
		GameFilterService filterService = new GameFilterService();
		
		List<GameModel> gameList = null;
		
		if (searchValue != null && !searchValue.trim().isEmpty()) {
			gameList = gameService.searchGames(searchValue.trim());
		} else if (filterOption != null && !filterOption.trim().isEmpty()) {
			gameList = filterService.getGamesByGenre(filterOption.trim());
			if (gameList.isEmpty()) {
				gameList = filterService.getGamesByPlatform(filterOption.trim());
			}
		} else {
			gameList = gameService.getAllGameInfo();
		}
		
		request.setAttribute("gameList", gameList);
		request.setAttribute("searchValue", searchValue);
		request.setAttribute("filterOption", filterOption);
		
		request.getRequestDispatcher("/WEB-INF/pages/GamerPortal.jsp").forward(request, response);
	}
}
