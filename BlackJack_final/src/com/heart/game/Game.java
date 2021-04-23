package com.heart.game;

import com.heart.impl.Extends_06_Choi;
import com.heart.service.BlackjackRule;

public class Game {
	public static void main(String[] args) {
		
		
//		BlackjackRule bjGame = new BlackJackRuleImplV2();
		
		BlackjackRule bjGame = new Extends_06_Choi();		
		
		bjGame.gameMainScreen();
		
	}
}
