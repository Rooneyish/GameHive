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
		GameService gameService = new GameService();
		List<GameModel> gameList;
		
		if(searchValue != null &&  !searchValue.trim().isEmpty()) {
			gameList = gameService.searchGames(searchValue.trim());
		}else {
			gameList = gameService.getAllGameInfo();
		}
		
		request.setAttribute("gameList", gameList);
		request.setAttribute("searchValue", searchValue);
		request.getRequestDispatcher("/WEB-INF/pages/GamerPortal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
