package com.callor.game.model;

public class DeckVO {

	// 카드는 조커를 제외하고 52장

	private String cardName[]; // 카드의 이름
	private Character cardMark[]; // 카드 문양
	private String markName[]; // 카드 문양 이름
	private String cardNumber[]; // 카드 숫자

	private int cardScore[]; // 카드당 점수

	public DeckVO() {

		cardName = new String[52];
		cardMark = new Character[] { '◆', '♥', '♣', '♠' };
		markName = new String[] { "다이아몬드", "하트", "클로버", "스페이드" };
		cardNumber = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		cardScore = new int[52];

	}

	public void cardNameMaker() {
		
		//TODO 카드 이름 붙이기
		for (int i = 0; i < 4; i++) { 
			for (int j = 0; j < cardNumber.length; j++) {
				cardName[j + (13 * i)] = cardMark[i] + markName[i] + " " + cardNumber[j] + " " + cardMark[i];
			}
		}

	}
	
	
	public void cardScoreMaker() {
		
		//TODO 카드에 점수 넣기
		for (int i = 0; i < 4; i++) { 
			for (int j = 0; j < cardNumber.length-3; j++) {
				cardScore[j + (13 * i)] = (j+1);
			}
			for(int j = 10 ; j < cardNumber.length ; j ++ ) {
				cardScore[j + (13 * i)] = 10;
			}
		}

	}
	
	
	public void printDebuging() {
		
		for(int i = 0 ; i < 52 ; i ++) {
			System.out.println(cardName[i] +"=" +cardScore[i] );
		}
	}
	
	
	
	
	public String[] getCardDeck() {
			return this.cardName;
	}
	
	
	public String getCardName(int num) {
		return cardName[num] ;
	}
	
	public int getCardScore(int num) {
		return cardScore[num] ;
	}
	
}
