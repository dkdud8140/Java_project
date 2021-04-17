package com.heart.impl;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.heart.model.DeckMaker;
import com.heart.model.DeckVO;
import com.heart.model.DeckVO2_cho;

public class BlackJackRule_cho extends BlackJackRuleImplV1 {

	
	List<DeckVO2_cho> deckList ;

	Random rnd = new Random();
	
	private int num = 0 ;
	
	
	public BlackJackRule_cho() {
	}

	
	
	
	@Override
	public void suffleDeck() {
		// TODO 카드 덱 셔플
		DeckMaker dM = new DeckMaker();
		deckList = dM.cardMaker();

		Collections.shuffle(deckList);  
		
		for (int i = 0; i < deckList.size(); i++) {
			DeckVO2_cho vo = deckList.get(i);
			System.out.println(vo.getDeckName() + "\t" + vo.getDeckScore());
		}

	}

	// playScreen에서 참조값 num 설정해두어야 한다. 
//	@Override
//	public void handDeck (List<DeckVO> list ) {
//		DeckVO vo = new DeckVO2_cho();
//
//		vo = deckList.get(num);
//		
//		vo.getDeckName() ;
//		vo.getDeckScore() ;
//		
//		num++ ;
//		
//		list.add(vo) ;
//		
//		return ;
//
//	}

}
