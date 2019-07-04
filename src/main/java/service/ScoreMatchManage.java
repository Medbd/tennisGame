package service;

import java.util.Arrays;
import java.util.List;

import model.Match;
import model.Player;

public class ScoreMatchManage {
	
	 public static final List<Integer> SCORES = Arrays.asList(0, 15, 30, 40);
	 public static final int ONE = 1 ;
	 public static final int TWO = 2 ;
	 public static final int THREE = 3 ;
	 public static final int FOUR = 4 ;
	 public static final int FIVE = 5 ;
	 public static final int SIX = 6 ;
	 public static final int SEVEN = 7 ;
	 public static final int FORTY = 40 ;
	
	private Match match ;
	
    public ScoreMatchManage(Match match){
        this.match = match;
    }
    
    public void calculWinPoint(Player winPointPlayer , Player opponentPlayer){
		if(winPointPlayer.getMatchScore() == FORTY) {
			if(opponentPlayer.getMatchScore() != FORTY) {
				match.winMatch(winPointPlayer);
			} else if (winPointPlayer.isDeuce()) {
				match.winMatch(winPointPlayer) ;
			} else if (!opponentPlayer.isDeuce()){
				winPointPlayer.setDeuce(true);	
			} else {
				opponentPlayer.setDeuce(false);
			}
		}	  else {
			winPointPlayer.setMatchScore(calculScore(winPointPlayer.getMatchScore()));
		}	
 	
    }
    
    public void calculSetScore(Player winMatchPlayer , Player opponentPlayer){
		if((winMatchPlayer.getScoreSet()+1) >=SIX) {
			if(opponentPlayer.getScoreSet() <=FOUR || ((winMatchPlayer.getScoreSet()+1) == SEVEN &&  opponentPlayer.getScoreSet() == FIVE)) {
				match.winSet(winMatchPlayer) ;
				winMatchPlayer.setScoreSet(winMatchPlayer.getScoreSet()+ONE);		
			} else if(winMatchPlayer.getScoreSet() >=SIX && opponentPlayer.getScoreSet() >=SIX) {
				winMatchPlayer.setScoreSet(winMatchPlayer.getScoreSet()+ONE);
				if(winMatchPlayer.getScoreSet() >=SEVEN && winMatchPlayer.getScoreSet() - opponentPlayer.getScoreSet() > TWO) {
					match.winSet(winMatchPlayer);
				}
			} else {
				winMatchPlayer.setScoreSet(winMatchPlayer.getScoreSet()+ONE);
			}
			
		} else {
			winMatchPlayer.setScoreSet(winMatchPlayer.getScoreSet()+ONE);
		}
    }
    
    
    
    private int calculScore(final int score) {
    	int indexOfScore = SCORES.indexOf(score) ;
    	if(indexOfScore < THREE)
    		return SCORES.get(indexOfScore + ONE);
    	return SCORES.indexOf(score);
    }
}
