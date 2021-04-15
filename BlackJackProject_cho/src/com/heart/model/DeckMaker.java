package com.heart.model;

import java.util.ArrayList;
import java.util.List;

public class DeckMaker {

	List<DeckVO2_cho> deckList;

	private Character cardMark[]; // 카드 문양
	private String markName[]; // 카드 문양 이름
	private String cardNumber[]; // 카드 숫자

	public DeckMaker() {

		deckList = new ArrayList<DeckVO2_cho>();

		cardMark = new Character[] { '◆', '♥', '♣', '♠' };
		markName = new String[] { "다이아몬드", "하트", "클로버", "스페이드" };
		cardNumber = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	}

	public List<DeckVO2_cho> cardMaker() {
		// TODO 카드 이름과 점수 할당

		String cardName;
		int cardScore;

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < cardNumber.length; j++) {

				cardName = cardMark[i] + markName[i] + " " + cardNumber[j] + " " + cardMark[i];

				if (j < cardNumber.length - 3) {
					cardScore = (j + 1);
				} else {
					cardScore = 10;
				}

				DeckVO2_cho vo = new DeckVO2_cho();
				vo.setDeckName(cardName);
				vo.setDeckScore(cardScore);
				deckList.add(vo);
			}

		}
		
//		디버깅코드
//		for(int i = 0 ; i < deckList.size() ; i ++ ) {
//			DeckVO2_cho vo = deckList.get(i) ;
//			System.out.println(vo.getDeckName() +"\t" + vo.getDeckScore());
//		}
		
		return deckList ;

	}

}
