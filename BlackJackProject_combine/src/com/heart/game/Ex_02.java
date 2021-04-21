package com.heart.game;

import com.heart.impl.correct.BlackJackRuleImplV3;
import com.heart.impl.correct.Extends_02_Kim;
import com.heart.impl.correct.Extends_07_FINAL;
import com.heart.service.BlackjackRule;

public class Ex_02 {
	public static void main(String[] args) {

//		BlackjackRule bj = new Extends_07_FINAL();
//
//		bj.gameMainScreen();
//		
//		
//		
//		Extends_02_Kim bj1 = new Extends_02_Kim();
//		bj1.saveGame();
		
		
		BlackjackRule bj = new BlackJackRuleImplV3();
		
		bj.gameMainScreen();

	}
}
