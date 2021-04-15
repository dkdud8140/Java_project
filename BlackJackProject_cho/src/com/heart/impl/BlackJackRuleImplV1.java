package com.heart.impl;

import java.util.List;
import java.util.Scanner;

import com.heart.model.DeckVO;
import com.heart.service.BlackjackRule;

public class BlackJackRuleImplV1 implements BlackjackRule {

	Scanner scan ; 
	
	
	
	public BlackJackRuleImplV1() {

		scan = new Scanner(System.in) ;
	}
	
	@Override
	public void gameMainScreen() {
		// TODO 게임 첫 화면
		
		System.out.println("*".repeat(36));
		System.out.println("*" + "            " + "블랙잭게임" + "            " + "*");
		System.out.println("*".repeat(36));

		System.out.println("\n현재 겜블러의 재산은"  + "원 입니다.");

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
				this.playScreen();
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
	public void playScreen() {
		// TODO 게임 메소드 호출 장소
		System.out.println("게임시작");
		
	}
	
	
	

	@Override
	public void inputGamer() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer bettingMoney() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void suffleDeck() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handDeck(List<DeckVO> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkBJ() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hitAndStand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameResult() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gamerMoney() {
		// TODO Auto-generated method stub

	}

	

}
