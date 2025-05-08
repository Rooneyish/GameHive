package com.gamehive.model;

import java.util.Date;
/**
 * @author Ronish Prajapati
 * LUM-ID 23048584
 * */

public class GameModel {
	private int gameId;
	private String gameTitle;
	private String gameDescription;
	private String gamePublisher;
	private Date gameReleasedDate;
	private float gamePrice;
	private float gameRating;
	private String gameDevelopers;
	private String gameGenres;
	private String gamePublishers; 
	
	public GameModel(int gameId, String gameTitle, String gameDescription, String gamePublisher, String gameDevelopers, String gameGenres, String gamePlatforms, Date gameReleasedDate,
			float gamePrice, float gameRating) {
		super();
		this.gameId= gameId;
		this.gameTitle = gameTitle;
		this.gameDescription = gameDescription;
		this.gamePublisher = gamePublisher;
		this.gameReleasedDate = gameReleasedDate;
		this.gamePrice = gamePrice;
		this.gameRating = gameRating;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}

	public String getGamePublisher() {
		return gamePublisher;
	}

	public void setGamePublisher(String gamePublisher) {
		this.gamePublisher = gamePublisher;
	}

	public Date getGameReleasedDate() {
		return gameReleasedDate;
	}

	public void setGameReleasedDate(Date gameReleasedDate) {
		this.gameReleasedDate = gameReleasedDate;
	}

	public float getGamePrice() {
		return gamePrice;
	}

	public void setGamePrice(float gamePrice) {
		this.gamePrice = gamePrice;
	}

	public float getGameRating() {
		return gameRating;
	}

	public void setGameRating(float gameRating) {
		this.gameRating = gameRating;
	}

	public String getGameDevelopers() {
		return gameDevelopers;
	}

	public void setGameDevelopers(String gameDevelopers) {
		this.gameDevelopers = gameDevelopers;
	}

	public String getGameGenres() {
		return gameGenres;
	}

	public void setGameGenres(String gameGenres) {
		this.gameGenres = gameGenres;
	}

	public String getGamePublishers() {
		return gamePublishers;
	}

	public void setGamePublishers(String gamePublishers) {
		this.gamePublishers = gamePublishers;
	}
}
