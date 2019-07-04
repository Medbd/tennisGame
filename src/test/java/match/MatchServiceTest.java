package match;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import model.Match;
import model.Player;
import resource.TennisGameResource;

public class MatchServiceTest {
	
	
	
	@Test
	public void throw_exception_when_player_is_null() {
		Match match = new Match(); 
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
		match.addPlayer(null));
		assertEquals(TennisGameResource.PLAYER_CAN_NOT_BE_NULL.name(), exception.getMessage());
	}
	
	@Test
	public void throw_exception_when_you_add_two_palyer_with_the_same_pseudo_name() {
		Match match = new Match(); 
		Player firstPlayer = new Player("firstPlayer", 0 , false);
		Player secondPlayer = new Player("firstPlayer", 0 , false);
		match.addPlayer(firstPlayer);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
		match.addPlayer(secondPlayer));
		assertEquals(TennisGameResource.CAN_NOT_ADD_TWO_PLAYER_WITH_THE_SAME_NAME.name(), exception.getMessage());
	}
	
	@Test
	public void throw_exception_when_you_add_more_than_two_palyer() {
		Match match = new Match(); 
		Player firstPlayer = new Player("firstPlayer", 0 , false);
		Player secondPlayer = new Player("secondPlayer", 0 , false);
		Player thirdPlayer = new Player("thirdPlayer", 0 , false);
		match.addPlayer(firstPlayer);
		match.addPlayer(secondPlayer);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
		match.addPlayer(thirdPlayer));
		assertEquals(TennisGameResource.YOU_CAN_NOT_ADD_MORE_THAN_TWO_PLAYER.name(), exception.getMessage());
	}
	
	@Test
	public void throw_exception_when_you_start_match_without_player() {
		Match match = new Match(); 
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
		match.startMatch());
		assertEquals(TennisGameResource.YOU_SHOULD_ADD_TWO_PAYER_TO_START_THE_GAME.name(), exception.getMessage());
	}
	
	@Test
	public void throw_exception_when_you_start_match_with_one_player() {
		Match match = new Match(); 
		Player firstPlayer = new Player("firstPlayer", 0 , false);
		match.addPlayer(firstPlayer);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> 
		match.startMatch());
		assertEquals(TennisGameResource.YOU_SHOULD_ADD_TWO_PAYER_TO_START_THE_GAME.name(), exception.getMessage());
	}
	
	@Test
	public void check_when_first_player_win_one_point() {
		Match match = new Match(); 
		Player firstPlayer = new Player("firstPlayer", 0 , false);
		Player secondPlayer = new Player("secondPlayer", 0 , false);
		match.addPlayer(firstPlayer);
		match.addPlayer(secondPlayer);
		match.startMatch();
		match.winPoint(firstPlayer, secondPlayer);
		assertEquals(match.getPlayers().get(0).getMatchScore(), 15);
		assertEquals(match.getPlayers().get(1).getMatchScore(), 0);
	}
	
	@Test
	public void check_when_first_player_win_one_point_and_second_palyer_win_one_point() {
		Match match = new Match(); 
		Player firstPlayer = new Player("firstPlayer", 0 , false);
		Player secondPlayer = new Player("secondPlayer", 0 , false);
		match.addPlayer(firstPlayer);
		match.addPlayer(secondPlayer);
		match.startMatch();
		match.winPoint(firstPlayer, secondPlayer);
		match.winPoint(secondPlayer, firstPlayer);
		assertEquals(match.getPlayers().get(0).getMatchScore(), 15);
		assertEquals(match.getPlayers().get(1).getMatchScore(), 15);
	}
	
	@Test
	public void check_when_first_player_win_the_match() {
		Match match = new Match(); 
		Player firstPlayer = new Player("firstPlayer", 0 , false);
		Player secondPlayer = new Player("secondPlayer", 0 , false);
		match.addPlayer(firstPlayer);
		match.addPlayer(secondPlayer);
		match.startMatch();
		match.winPoint(firstPlayer, secondPlayer);
		match.winPoint(firstPlayer, secondPlayer);
		match.winPoint(firstPlayer, secondPlayer);
		assertEquals(match.getPlayers().get(0).getMatchScore(), 40);
		match.winPoint(firstPlayer, secondPlayer);
		assertEquals(match.getWinnerMatch(),match.getPlayers().get(0));
		assertEquals(match.getPlayers().get(0).getMatchScore(), 0);
		assertEquals(match.getPlayers().get(1).getMatchScore(), 0);
	}
	
	@Test
	public void check_when_first_player_has_deuce_and_win_the_match() {
		Match match = new Match(); 
		Player firstPlayer = new Player("firstPlayer", 0 , false);
		Player secondPlayer = new Player("secondPlayer", 0 , false);
		match.addPlayer(firstPlayer);
		match.addPlayer(secondPlayer);
		match.startMatch();
		match.winPoint(firstPlayer, secondPlayer);
		match.winPoint(firstPlayer, secondPlayer);
		match.winPoint(firstPlayer, secondPlayer);
		match.winPoint(secondPlayer, firstPlayer);
		match.winPoint(secondPlayer, firstPlayer);
		match.winPoint(secondPlayer, firstPlayer);
		assertEquals(match.getPlayers().get(0).getMatchScore(), 40);
		assertEquals(match.getPlayers().get(1).getMatchScore(), 40);
		match.winPoint(firstPlayer, secondPlayer);
		assertEquals(match.getPlayers().get(0).isDeuce(), true);
		assertEquals(match.getPlayers().get(1).isDeuce(), false);
		match.winPoint(firstPlayer, secondPlayer);
		assertEquals(match.getWinnerMatch(),match.getPlayers().get(0));
		assertEquals(match.getPlayers().get(0).getMatchScore(), 0);
		assertEquals(match.getPlayers().get(1).getMatchScore(), 0);
	}
		@Test
		public void check_when_second_player_has_deuce_and_win_the_match() {
			Match match = new Match(); 
			Player firstPlayer = new Player("firstPlayer", 0 , false);
			Player secondPlayer = new Player("secondPlayer", 0 , false);
			match.addPlayer(firstPlayer);
			match.addPlayer(secondPlayer);
			match.startMatch();
			match.winPoint(firstPlayer, secondPlayer);
			match.winPoint(firstPlayer, secondPlayer);
			match.winPoint(firstPlayer, secondPlayer);
			match.winPoint(secondPlayer, firstPlayer);
			match.winPoint(secondPlayer, firstPlayer);
			match.winPoint(secondPlayer, firstPlayer);
			assertEquals(match.getPlayers().get(0).getMatchScore(), 40);
			assertEquals(match.getPlayers().get(1).getMatchScore(), 40);
			match.winPoint(secondPlayer,firstPlayer);
			assertEquals(match.getPlayers().get(0).isDeuce(), false);
			assertEquals(match.getPlayers().get(1).isDeuce(), true);
			match.winPoint(secondPlayer ,firstPlayer);
			assertEquals(match.getWinnerMatch(),match.getPlayers().get(1));
			assertEquals(match.getPlayers().get(0).getMatchScore(), 0);
			assertEquals(match.getPlayers().get(1).getMatchScore(), 0);
	}
		@Test
		public void check_when_first_player_has_deuce_and_second_player_has_deuce_and_the_first_player_win() {
			Match match = new Match(); 
			Player firstPlayer = new Player("firstPlayer", 0 , false);
			Player secondPlayer = new Player("secondPlayer", 0 , false);
			match.addPlayer(firstPlayer);
			match.addPlayer(secondPlayer);
			match.startMatch();
			match.winPoint(firstPlayer, secondPlayer);
			match.winPoint(firstPlayer, secondPlayer);
			match.winPoint(firstPlayer, secondPlayer);
			match.winPoint(secondPlayer, firstPlayer);
			match.winPoint(secondPlayer, firstPlayer);
			match.winPoint(secondPlayer, firstPlayer);
			assertEquals(match.getPlayers().get(0).getMatchScore(), 40);
			assertEquals(match.getPlayers().get(1).getMatchScore(), 40);
			match.winPoint(secondPlayer,firstPlayer);
			assertEquals(match.getPlayers().get(0).isDeuce(), false);
			assertEquals(match.getPlayers().get(1).isDeuce(), true);
			match.winPoint(firstPlayer ,secondPlayer);
			match.winPoint(firstPlayer ,secondPlayer);
			match.winPoint(firstPlayer ,secondPlayer);
			assertEquals(match.getWinnerMatch(),match.getPlayers().get(0));
			assertEquals(match.getPlayers().get(0).getMatchScore(), 0);
			assertEquals(match.getPlayers().get(1).getMatchScore(), 0);
	}
		
		@Test
		public void check_when_first_player_win_game() {
			Match match = new Match(); 
			Player firstPlayer = new Player("firstPlayer", 0 , false);
			Player secondPlayer = new Player("secondPlayer", 0 , false);
			match.addPlayer(firstPlayer);
			match.addPlayer(secondPlayer);
			match.startMatch();
			for (int i=0; i<24; i++) {
				match.winPoint(firstPlayer, secondPlayer);
			}
			assertEquals(match.getGameOver(), true);
			assertEquals(match.getWinnerSet(),match.getPlayers().get(0));	
			assertEquals(match.getPlayers().get(0).getScoreSet(), 6);	

	}
		
		@Test
		public void check_when_second_player_win_game() {
			Match match = new Match(); 
			Player firstPlayer = new Player("firstPlayer", 0 , false);
			Player secondPlayer = new Player("secondPlayer", 0 , false);
			match.addPlayer(firstPlayer);
			match.addPlayer(secondPlayer);
			match.startMatch();
			for (int i=0; i<24; i++) {
				match.winPoint(secondPlayer,firstPlayer);
			}
			assertEquals(match.getGameOver(), true);
			assertEquals(match.getWinnerSet(),match.getPlayers().get(1));	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 6);	
	}
		
		@Test
		public void check_when_first_player_win_six_set_and_the_second_player_win_four_set() {
			Match match = new Match(); 
			Player firstPlayer = new Player("firstPlayer", 0 , false);
			Player secondPlayer = new Player("secondPlayer", 0 , false);
			match.addPlayer(firstPlayer);
			match.addPlayer(secondPlayer);
			match.startMatch();
			for (int i=0; i<20; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			for (int i=0; i<16; i++) {
				match.winPoint(secondPlayer,firstPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 5);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 4);	
			for (int i=0; i<4; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			assertEquals(match.getGameOver(), true);
			assertEquals(match.getWinnerSet(),match.getPlayers().get(0));	
	}
		@Test
		public void check_when_first_player_win_six_set_and_the_second_palyer_win_five_set_to_end_the_game_the_first_player_should_win_seven_set() {
			Match match = new Match(); 
			Player firstPlayer = new Player("firstPlayer", 0 , false);
			Player secondPlayer = new Player("secondPlayer", 0 , false);
			match.addPlayer(firstPlayer);
			match.addPlayer(secondPlayer);
			match.startMatch();
			for (int i=0; i<20; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			for (int i=0; i<20; i++) {
				match.winPoint(secondPlayer,firstPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 5);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 5);	
			for (int i=0; i<4; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 6);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 5);	
			assertEquals(match.getGameOver(), false);
			for (int i=0; i<4; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 7);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 5);	
			assertEquals(match.getGameOver(), true);
			assertEquals(match.getWinnerSet(),match.getPlayers().get(0));	
	}
		
		@Test
		public void check_when_first_player_win_six_set_and_the_second_palyer_win_six_set_to_end_the_game_the_first_player_should_win_tie_break() {
			Match match = new Match(); 
			Player firstPlayer = new Player("firstPlayer", 0 , false);
			Player secondPlayer = new Player("secondPlayer", 0 , false);
			match.addPlayer(firstPlayer);
			match.addPlayer(secondPlayer);
			match.startMatch();
			for (int i=0; i<20; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			for (int i=0; i<20; i++) {
				match.winPoint(secondPlayer,firstPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 5);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 5);	
			for (int i=0; i<4; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			for (int i=0; i<4; i++) {
				match.winPoint(secondPlayer,firstPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 6);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 6);	
			assertEquals(match.getGameOver(), false);
			for (int i=0; i<4; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 7);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 6);	
			assertEquals(match.getGameOver(), false);
			for (int i=0; i<4; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			for (int i=0; i<4; i++) {
				match.winPoint(firstPlayer,secondPlayer);
			}
			assertEquals(match.getPlayers().get(0).getScoreSet(), 9);	
			assertEquals(match.getPlayers().get(1).getScoreSet(), 6);	
			assertEquals(match.getGameOver(), true);
			assertEquals(match.getWinnerSet(),match.getPlayers().get(0));	
	}

}
