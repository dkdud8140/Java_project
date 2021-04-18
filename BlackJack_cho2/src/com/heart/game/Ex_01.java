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
		List<DeckVO> dealerList = new ArrayList<DeckVO>();

		for (int j = 0; j < 5; j++) {
			// 1차 배분
			ex.suffleDeck();
			ex.handDeck(playerList);

			int nSizeP = playerList.size();

			for (int i = 0; i < nSizeP; i++) {
				DeckVO vo = playerList.get(i);

				System.out.print(vo.getDeck() + " : ");
				System.out.println(vo.getValue());
			}

			DeckVO voP = playerList.get(nSizeP - 1);
			System.out.println("합 : " + voP.getValueSum());
			System.out.println();
			System.out.println("------------------");

			ex.handDeck(dealerList);

			int nSizeD = dealerList.size();

			for (int i = 0; i < nSizeD; i++) {
				DeckVO vo = dealerList.get(i);

				System.out.print(vo.getDeck() + " : ");
				System.out.println(vo.getValue());
			}

			DeckVO voD = dealerList.get(nSizeD - 1);
			System.out.println("합 : " + voD.getValueSum());
			System.out.println();
			System.out.println("=================");

		}

	}
}
