package com.heart.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.heart.impl.correct.BlackJackYubin;
import com.heart.model.DeckVO;

public class Ex_03 {

	public static void main(String[] args) {

		BlackJackYubin makeDeck = new BlackJackYubin();

		List<DeckVO> deckList = new ArrayList<DeckVO>();
		deckList = makeDeck.deckList;

		makeDeck.createDeck();

		Random rnd = new Random();

		int dSize = deckList.size();
		System.out.println(dSize);


		for (int i = 0; i < 50; i++) {
			int num = rnd.nextInt(dSize);
			DeckVO vo1 = deckList.get(i);
			DeckVO voNum = deckList.get(num);
			DeckVO tempVO = vo1;
			
			vo1 = voNum;
			voNum = tempVO;

			deckList.set(i, vo1);
			deckList.set(num, voNum);

		}

		for (int i = 0; i < dSize; i++) {
			DeckVO vo = deckList.get(i);
			System.out.println(vo.toString());
		}

	}

}
