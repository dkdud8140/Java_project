package com.callor.game.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.callor.game.model.DeckVO;
import com.callor.game.model.GamerMoneyVO;
import com.callor.game.service.GameRule;

public class GameRuleImplV1 implements GameRule {

	DeckVO deckVO;
	GamerMoneyVO gamerMoney;

	List<String> dealerDeck;
	List<String> gamerDeck;

	int dealerSum;
	int gamerSum;

	List<Integer> shuffleDeck;

	Scanner scan;

	public GameRuleImplV1() {
		deckVO = new DeckVO();
		deckVO.cardNameMaker();
		deckVO.cardScoreMaker();

		gamerMoney = new GamerMoneyVO();

		dealerSum = 0;
		gamerSum = 0;

		scan = new Scanner(System.in);

	}

	@Override
	public void gameMain() {
		// TODO 게임 초기 화면 출력

		dealerDeck = new ArrayList<String>();
		gamerDeck = new ArrayList<String>();

		System.out.println("*".repeat(36));
		System.out.println("*" + "            " + "블랙잭게임" + "            " + "*");
		System.out.println("*".repeat(36));

		System.out.println("\n현재 겜블러의 재산은" + gamerMoney.getGamerMoney() + "원 입니다.");

		while (true) {
			System.out.println("\n" + "-".repeat(36));
			System.out.println("게임을 시작하시겠습니까?");
			System.out.println("▷ GO : 게임하기");
			System.out.println("▷ QUIT : 그만하기");
			System.out.println("-".repeat(36));
			System.out.print(" ▷ ");
			String goQuit = scan.nextLine();

			// 게임 선택
			if (goQuit.equals("GO")) {
				this.blackJackGameMain();
			}

			// 게임종료 선택
			else if (goQuit.equals("QUIT")) {
				System.out.println("\n게임을 종료합니다.");
				return;
			}

			// 잘못입력
			else {
				System.out.println("입력은 GO / QUIT 만 가능합니다.");
				continue;
			}

		}
	}

	@Override
	public void blackJackGameMain() {
		// TODO 게임진행

		// 판돈 베팅
		this.bettingMoney();

		System.out.println("\n**게임을 시작합니다.**\n");

		// 카드셔플
		this.shuffleCard();

		this.hitOrStand();

	}

	@Override
	public void bettingMoney() {
		// TODO 베팅금입력

		Integer intBMonney = null;

		while (true) {
			System.out.println("베팅금을 입력하세요.(단, 배팅금은 100단위만 가능합니다.");
			System.out.print(" ▷ ");
			String bettingMoney = scan.nextLine();

			try {
				intBMonney = Integer.valueOf(bettingMoney);
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력하세요.");
				continue;
			}

			if (intBMonney > gamerMoney.getGamerMoney()) {
				System.out.println("겜블러의 자산 (" + gamerMoney.getGamerMoney() + ") 보다 큰 돈은 베팅할 수 없습니다.");
				continue;
			} else if (!(intBMonney % 100 == 0)) {
				System.out.println("베팅금은 100단위만 가능합니다.");
				continue;
			}

			gamerMoney.setBettingMoney(intBMonney);
			return;
		}

	}

	@Override
	public void shuffleCard() {
		// TODO 셔플메소드
		// 게임 시작시 카드를 셔플해주는 메소드
		// 딜러와 게이머에게 카드를 두 장 씩 배분

		System.out.println("** 카드를 셔플하였습니다. **");
		System.out.println("** 딜러와 게이머에게 두 장씩 배분합니다. **\n");

		List<Integer> deckNum = this.shuffleDeck();

		int intDeckNum[] = new int[52];

		for (int i = 0; i < intDeckNum.length; i++) {
			intDeckNum[i] = deckNum.get(i);
		}

		dealerDeck.add(deckVO.getCardName(intDeckNum[0]));
		dealerDeck.add(deckVO.getCardName(intDeckNum[1]));

		dealerSum += deckVO.getCardScore(intDeckNum[0]);
		dealerSum += deckVO.getCardScore(intDeckNum[1]);

		gamerDeck.add(deckVO.getCardName(intDeckNum[2]));
		gamerDeck.add(deckVO.getCardName(intDeckNum[3]));

		gamerSum += deckVO.getCardScore(intDeckNum[2]);
		gamerSum += deckVO.getCardScore(intDeckNum[3]);

		System.out.println("딜러의 패는 다음과 같습니다.");
		System.out.println("-".repeat(50));
		System.out.println(dealerDeck.get(0));
		System.out.println("????");
		System.out.println("-".repeat(50));

		System.out.println("게이머의 패는 다음과 같습니다.");
		System.out.println("-".repeat(50));
		System.out.println(gamerDeck.get(0));
		System.out.println(gamerDeck.get(1));
		System.out.println("점수합계 : " + gamerSum);
		System.out.println("-".repeat(50));

	}

	public List<Integer> shuffleDeck() {

		List<Integer> deckNum = new ArrayList<Integer>();

		int num;
		for (num = 0; num < 52; num++) {
			deckNum.add(num);
		}

		Collections.shuffle(deckNum);

		// 디거깅코드
//		for (int i : deckNum) {
//			System.out.println(i);
//		}

		this.shuffleDeck = deckNum;
		return shuffleDeck;
	}

	@Override
	public void selectMenu() {
		// TODO 선택지메소드

	}

	@Override
	public void insurance() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doubleDown() {
		// TODO 더블다운 메소드

	}

	@Override
	public void Split() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hitOrStand() {
		// TODO 히트 or 스탠드

		while (true) {
			System.out.println("Hit 하시겠습니까? Stand하시겠습니까?");
			System.out.println("▷ H : HIT 한다");
			System.out.println("▷ S : STAND한다");
			System.out.println("-".repeat(36));
			System.out.print(" ▷ ");
			String hOs = scan.nextLine();

			// 게임 선택
			if (hOs.equals("H")) {
				
			}
			// 게임종료 선택
			else if (hOs.equals("S")) {
				System.out.println(" ** STAND **");
				return;
			}

			// 잘못입력
			else {
				System.out.println("입력은 H / S 만 가능합니다.");
				continue;
			}
		}

	}

	@Override
	public void resultMoney() {
		// TODO Auto-generated method stub

	}

	@Override
	public void surrender() {
		// TODO Auto-generated method stub

	}

}
