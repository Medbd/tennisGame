package model;

public class Player {
	
	private String name ; 
	private int matchScore ; 
	private int scoreSet ; 
	private boolean isDeuce ;
	

	public Player(String name, int matchScore, boolean isDeuce) {
		super();
		this.name = name;
		this.matchScore = matchScore;
		this.isDeuce = isDeuce;
	}
	
	/**
	 * @return the scoreSet
	 */
	public int getScoreSet() {
		return scoreSet;
	}

	/**
	 * @param scoreSet the scoreSet to set
	 */
	public void setScoreSet(int scoreSet) {
		this.scoreSet = scoreSet;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the matchScore
	 */
	public int getMatchScore() {
		return matchScore;
	}
	/**
	 * @param matchScore the matchScore to set
	 */
	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}
	/**
	 * @return the isDeuce
	 */
	public boolean isDeuce() {
		return isDeuce;
	}
	/**
	 * @param isDeuce the isDeuce to set
	 */
	public void setDeuce(boolean isDeuce) {
		this.isDeuce = isDeuce;
	} 
	
    public void clearMatchScore() {
    	matchScore = 0;
    }

}
