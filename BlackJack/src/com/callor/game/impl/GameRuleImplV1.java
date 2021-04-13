package com.callor.game.impl;

import java.util.Scanner;

import com.callor.game.model.DeckVO;
import com.callor.game.model.GamerMoneyVO;
import com.callor.game.service.GameRule;

public class GameRuleImplV1 implements GameRule {

	DeckVO deckVO;
	GamerMoneyVO gamerMoney;

	Scanner scan;

	public GameRuleImplV1() {

		deckVO = new DeckVO();
		gamerMoney = new GamerMoneyVO();

		scan = new Scanner(System.in);
	}

	@Override
	public void gameMain() {
		// TODO 게임 초기 화면 출력

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
			
			//게임 선택
			if (goQuit.equals("GO")) {
				this.blackJackGameMain();
			} 
			
			//게임종료 선택
			else if (goQuit.equals("QUIT")) {
				System.out.println("\n게임을 종료합니다.");
				return;
			} 
			
			//잘못입력
			else {
				System.out.println("입력은 GO / QUIT 만 가능합니다.");
				continue;
			}
			
			
		}
	}
	

	@Override
	public void blackJackGameMain() {
		// TODO 게임진행
		
		//판돈 베팅
		this.bettingMoney();
		
		System.out.println("\n**게임을 시작합니다.**\n");
		
		//카드셔플
		this.shuffleCard();
		
		
		
		
		
		
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
				System.out.println("겜블러의 자산 (" + gamerMoney.getGamerMoney() +
							") 보다 큰 돈은 베팅할 수 없습니다.");
				continue;
			}
			else if (!(intBMonney % 100 == 0)) {
				System.out.println("베팅금은 100단위만 가능합니다.");
				continue;
			}

			gamerMoney.setBettingMoney(intBMonney);
			return ;
		}

	}

	@Override
	public void shuffleCard() {
		// TODO 셔플메소드
		
		System.out.println("셔플메소드");
	}

	@Override
	public void selectMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insurance() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doubleDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Split() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hitOrStand() {
		// TODO Auto-generated method stub

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
