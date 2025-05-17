package com.gamehive.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gamehive.model.GameModel;
import com.gamehive.service.AdminService;
import com.gamehive.service.GameService;
import com.gamehive.service.SortService;

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
		AdminService adminService = new AdminService();
		SortService sortService = new SortService();


		String sortBy = request.getParameter("sortOptions");
		String orderByClause ="ORDER BY game_id ASC";

	    if (sortBy != null) {
	        switch (sortBy) {
	            case "id_asc":
	                orderByClause = "ORDER BY game_id";
	                break;
	            case "id_desc":
	                orderByClause = "ORDER BY game_id DESC";
	                break;
	            case "price_asc":
	                orderByClause = "ORDER BY game_price";
	                break;
	            case "price_desc":
	                orderByClause = "ORDER BY game_price DESC";
	                break;
	            case "rating_asc":
	                orderByClause = "ORDER BY game_rating";
	                break;
	            case "rating_desc":
	                orderByClause = "ORDER BY game_rating DESC";
	                break;
	            case "date_asc":
	                orderByClause = "ORDER BY game_released_date";
	                break;
	            case "date_desc":
	                orderByClause = "ORDER BY game_released_date DESC";
	                break;
	            default:
	                break;
	        }
	    }

	    List<GameModel> sortGames = sortService.getSortedGames(orderByClause);
		int totalGames = gameService.getNumberOfGames();
		int totalDevelopers = gameService.getNumberOfDevelopers();
		int totalFreeGames = gameService.getNumberOfFreeGames();
		int totalUsers = adminService.getNumberOfUsers();

		String success = request.getParameter("success");
	    if (success != null && !success.isEmpty()) {
	        request.setAttribute("success", success);
	    }
	    
	    String error = request.getParameter("error");
	    if (error != null && !error.isEmpty()) {
	        request.setAttribute("error", error);
	    }
		
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalGames", totalGames);
		request.setAttribute("totalDevelopers", totalDevelopers);
		request.setAttribute("totalFreeGames", totalFreeGames);
		request.setAttribute("games", sortGames);
		request.getRequestDispatcher("/WEB-INF/pages/Admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
