package com.heart.impl;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.heart.model.DeckVO;
import com.heart.model.DeckVO2_cho;

public class BlackJackRule_cho2 extends BlackJackRuleImplV1 {

	
	List<DeckVO> deckList ;

	Random rnd = new Random();
	
	private int num = 0 ;
	
	BlackJackYubin makeDeck ;
	
	DeckVO deckVO ;
	
	
	
	public BlackJackRule_cho2() {
		
		makeDeck = new BlackJackYubin();
		
		deckVO = new DeckVO() ;
	}

	
	
	
	@Override
	public void suffleDeck() {
		// TODO 카드 덱 셔플

		deckList = makeDeck.deckList ;
		makeDeck.createDeck();
		
		Collections.shuffle(deckList);  
		
//		for (int i = 0; i < deckList.size(); i++) {
//			DeckVO vo = deckList.get(i);
//			System.out.println(vo.getDeck() + " : " + vo.getValue());
//		}

	}

	// playScreen에서 참조값 num 설정해두어야 한다. 
	@Override
	public void handDeck (List<DeckVO> list) {
		
		DeckVO vo = deckList.get(num);
		
		vo.getDeck() ;
		vo.getValue() ;
		
		num++ ;
		
		list.add(vo) ;

	}

}
