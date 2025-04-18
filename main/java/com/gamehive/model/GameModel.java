package com.gamehive.model;

import java.time.LocalDate;

public class GameModel {
	private String gameTitle;
	private String gameDescription;
	private String gamePublisher;
	private LocalDate gameReleasedDate;
	private float gamePrice;
	private float gameRating;
	public GameModel(String gameTitle, String gameDescription, String gamePublisher, LocalDate gameReleasedDate,
			float gamePrice, float gameRating) {
		super();
		this.gameTitle = gameTitle;
		this.gameDescription = gameDescription;
		this.gamePublisher = gamePublisher;
		this.gameReleasedDate = gameReleasedDate;
		this.gamePrice = gamePrice;
		this.gameRating = gameRating;
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
	public LocalDate getGameReleasedDate() {
		return gameReleasedDate;
	}
	public void setGameReleasedDate(LocalDate gameReleasedDate) {
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
}
