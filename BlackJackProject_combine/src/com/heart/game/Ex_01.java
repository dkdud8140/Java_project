package com.heart.game;

import java.util.ArrayList;
import java.util.List;

import com.heart.impl.BlackJackRuleImplV1;
import com.heart.impl.correct.BlackJackYubin;
import com.heart.model.DeckVO;
import com.heart.service.BlackjackRule;

public class Ex_01 {
	public static void main(String[] args) {

		BlackjackRule ex = new BlackJackRuleImplV1();

//		List<DeckVO> playerList = new ArrayList<DeckVO>();
//		List<DeckVO> dealerList = new ArrayLis	SAZt<DeckVO>();

		
		BlackJackYubin by = new BlackJackYubin() ;
		
		List<DeckVO> voList = by.deckList ;
		
				 by.createDeck();
		
		for(int i = 0 ;i < voList.size() ; i ++) {
			
			DeckVO vo = voList.get(i) ;
			System.out.println(vo.getDeck() + " : " + vo.getValue());
		}
		
		
		
	}
}
