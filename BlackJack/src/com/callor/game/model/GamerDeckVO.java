package com.callor.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamerDeckVO {

	private List<String> gDeckCard;
	private List<Integer> gDeckScore;
	DeckVO deckVO ;

	protected Random rnd;

	public GamerDeckVO() {
		gDeckCard = new ArrayList<String>();
		gDeckScore = new ArrayList<Integer>();
		deckVO = new DeckVO() ;
		rnd = new Random();
	}

	public void firstDDeck() {

		deckVO.cardNameMaker();
		deckVO.cardScoreMaker();
		
		
		int intCard1 = rnd.nextInt(52);
		int intCard2 ;
		
		while (true) {
			intCard2 = rnd.nextInt(52);
			if (intCard2 == 0) {
				continue;
			}
			break ;
		}
		
		String card1 = deckVO.getCardName(intCard1);
		String card2 = deckVO.getCardName(intCard2);
		
		Integer score1 = deckVO.getCardScore(intCard1);
		Integer score2 = deckVO.getCardScore(intCard2);
		
		gDeckCard.add(card1);
		gDeckCard.add(card2);
		
		gDeckScore.add(score1);
		gDeckScore.add(score2);
		
		
	}
	
	
	public String getCardName(int num) {
		return gDeckCard.get(num) ;
	}
	
	public Integer getCardScore(int num) {
		return gDeckScore.get(num) ;
	}

}
