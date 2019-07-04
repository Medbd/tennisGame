package model;

import java.util.ArrayList;
import java.util.List;

import resource.TennisGameResource;
import service.ScoreMatchManage;



public class Match {

    private final List<Player> players = new ArrayList();
	private Boolean gameOver = false ;
	private Player winnerMatch ;
	private Player winnerSet ;
	
    /**
	 * @return the winnerSet
	 */
	public Player getWinnerSet() {
		return winnerSet;
	}

	/**
	 * @param winnerSet the winnerSet to set
	 */
	public void setWinnerSet(Player winnerSet) {
		this.winnerSet = winnerSet;
	}

	/**
	 * @return the gameOver
	 */
	public Boolean getGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}


	/**
	 * @return the winnerMatch
	 */
	public Player getWinnerMatch() {
		return winnerMatch;
	}

	/**
	 * @param winnerMatch the winnerMatch to set
	 */
	public void setWinnerMatch(Player winnerMatch) {
		this.winnerMatch = winnerMatch;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}


	private ScoreMatchManage scoreMatchManage = new ScoreMatchManage(this);
	

	
	public void startMatch() {
		if(players.size()<2)
			throw new IllegalArgumentException(TennisGameResource.YOU_SHOULD_ADD_TWO_PAYER_TO_START_THE_GAME.name()) ; 
		players.forEach(p -> p.setMatchScore(0));
	}
	
	public void winPoint(Player winPointPlayer  , Player opponentPlayer) {
		if(gameOver) 
			throw new IllegalArgumentException(TennisGameResource.GAME_IS_OVER.name()) ; 
		scoreMatchManage.calculWinPoint(winPointPlayer, opponentPlayer);
	}
	
	public void winMatch(Player player) {
		winnerMatch = player ; 
		scoreMatchManage.calculSetScore(player , players.stream().filter(p -> p != player).findFirst().get());
		players.forEach(p -> p.setMatchScore(0));
	}
	
	public void winSet(Player player) {
		winnerSet = player ; 
		players.forEach(p -> p.setMatchScore(0));
		gameOver = true;
	}
	
	
public void addPlayer(Player player) {
	if(player == null) 
		throw new IllegalArgumentException(TennisGameResource.PLAYER_CAN_NOT_BE_NULL.name()); 
	if(players.size() == 2) 
		throw new IllegalArgumentException(TennisGameResource.YOU_CAN_NOT_ADD_MORE_THAN_TWO_PLAYER.name()); 
	if(players.stream().filter(p -> p.getName().equals(player.getName())).findAny()
			  .orElse(null) != null) 
		throw new IllegalArgumentException(TennisGameResource.CAN_NOT_ADD_TWO_PLAYER_WITH_THE_SAME_NAME.name()); 
	players.add(player);
}

}
