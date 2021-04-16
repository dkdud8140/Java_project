package com.heart.game;

import java.util.ArrayList;
import java.util.List;

import com.heart.impl.BlackJackRule_cho2;
import com.heart.model.DeckVO;
import com.heart.service.BlackjackRule;

public class Ex_01 {
	public static void main(String[] args) {

		BlackjackRule ex = new BlackJackRule_cho2();

		List<DeckVO> playerList = new ArrayList<DeckVO>();

		ex.suffleDeck();

		int nSizeP = playerList.size();

		ex.handDeck(playerList);

		for (int i = 0; i < nSizeP; i++) {
			DeckVO vo = playerList.get(i);

			System.out.print(vo.getDeck() + " : ");
			System.out.println(vo.getValue());
			System.out.println("=================");
		}


		ex.handDeck(playerList);

		for (int i = 0; i < nSizeP; i++) {
			DeckVO vo = playerList.get(i);

			System.out.print(vo.getDeck() + " : ");
			System.out.println(vo.getValue());
			System.out.println("=================");
		}
		
		
		ex.handDeck(playerList);

		for (int i = 0; i < nSizeP; i++) {
			DeckVO vo = playerList.get(i);

			System.out.print(vo.getDeck() + " : ");
			System.out.println(vo.getValue());
			System.out.println("=================");
		}
		ex.handDeck(playerList);

		for (int i = 0; i < nSizeP; i++) {
			DeckVO vo = playerList.get(i);

			System.out.print(vo.getDeck() + " : ");
			System.out.println(vo.getValue());
			System.out.println("=================");
		}
		ex.handDeck(playerList);

		for (int i = 0; i < nSizeP; i++) {
			DeckVO vo = playerList.get(i);

			System.out.print(vo.getDeck() + " : ");
			System.out.println(vo.getValue());
			System.out.println("=================");
		}
		ex.handDeck(playerList);

		for (int i = 0; i < nSizeP; i++) {
			DeckVO vo = playerList.get(i);

			System.out.print(vo.getDeck() + " : ");
			System.out.println(vo.getValue());
			System.out.println("=================");
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//
//
//		
//		int nSizeD = dealList.size();
//
//		for (int i = 0; i < nSizeP; i++) {
//			DeckVO vo = playerList.get(i);
//
//			System.out.print(vo.getDeck() + " : ");
//			System.out.println(vo.getValue());
//
//		}
//
//		DeckVO vo1 = dealList.get(0);
//
//		System.out.print(vo1.getDeck() + " : ");
//		System.out.println(vo1.getValue());
//
//		ex.handDeck(playerList);
//		ex.handDeck(dealList);

	}
}
