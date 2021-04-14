package com.heart.impl;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.heart.model.DeckMaker;
import com.heart.model.DeckVO;
import com.heart.model.DeckVO2_cho;

public class BlackJackRule_cho extends BlackJackRuleimplV1 {

	DeckMaker dM;

	Random rnd = new Random();

	public BlackJackRule_cho() {
		dM = new DeckMaker();
	}

	@Override
	public void suffleDeck() {
		// TODO 카드 덱 셔플

		List<DeckVO2_cho> deckList = dM.cardMaker();

		Collections.shuffle(deckList);  
		
		for (int i = 0; i < deckList.size(); i++) {
			DeckVO2_cho vo = deckList.get(i);
			System.out.println(vo.getDeckName() + "\t" + vo.getDeckScore());
		}

	}

	@Override
	public void handDeck() {


	}

}
