package com.heart.game;

import com.heart.impl.BlackJackRule_cho;
import com.heart.model.DeckMaker;
import com.heart.service.BlackjackRule;

public class Ex_01 {
	public static void main(String[] args) {
		
		
		BlackjackRule ex = new BlackJackRule_cho() ;
		
//		ex.gameMainScreen();
		
		ex.suffleDeck();
		
		
		DeckMaker dM = new DeckMaker();
		
		//dM.cardMaker();
		
	}
}
