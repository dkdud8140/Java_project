package com.heart.impl;

public class Extends_06_Choi extends Extends_05_Cho {

	// TODO 최선영
	// 게임 결과 승리와 패배 판정 메소드
	// 동시에 플레이어 베팅금 반환
	@Override
	public void scoreResult() {

		// 인슈어런스 진행했을 시, 딜러가 블랙잭이 맞았다면 인슈어런스 금액 돌려줌
		if (inYN && voD.getBj()) {
			System.out.println("인슈어런스 보험금을 돌려드립니다.");
			System.out.println((inMoney * 2) + "원 지급");
			voP.setMoney(voP.getMoney() + (inMoney * 2));
		}

		Integer pScore = voP.getScore(); // 플레이어의 최종 점수
		Integer dScore = voD.getScore(); // 딜러의 최종 점수

		// 2021.04.23 블랙잭으로 인한 승부계산 <-> 블랙잭 아닐 때 승부 계산 함수 자리 바꾸고 return 추가
		if (voP.getBj()) { // 플레이어가 블랙잭이라면
			if (voD.getBj()) { // 딜러가 블랙잭인지도 확인하고 딜러도 블랙잭이라면
				push(); // 무승부
				return; // 아래 if 문은 실행되지 않게 return 해버리기
			} else { // 딜러는 블랙잭이 아니라면
				win_bj(); // 플레이어 승리 ~!
				return;
			}
		}

		if (voP.getBust()) { // 플레이어가 BUST
			if (voD.getBust())
				push(); // 딜러도 BUST 라면 무승부
			else
				lose(); // 딜러는 BUST 아니라면 패배
		} else if (((pScore > dScore) || voD.getBust()))
			win(); // 플레이어는 BUST가 아니면서 딜러보다 점수가 높거나, 딜러가 BUST면 승리

		else if (pScore < dScore)
			lose(); // 플레이어, 딜러는 BUST가 아니면서 플레이어가 딜러보다 점수가 낮으면 패배

		else if (pScore == dScore)
			push(); // 플레이어, 딜러는 BUST가 아니면서 플레이어와 딜러 점수가 같으면 무승부

		else if (voD.getBj())
			lose();

	}

	// TODO 최선영
	// 플레이어가 블랙잭으로 이겼을 때 베팅금 계산
	public void win_bj() {
		// TODO 플레이어가 블랙잭으로 이겼을 경우
		System.out.println("\n" + voP.getName() + "님 BlackJack으로 승리");
		voP.setMoney((int) (voP.getMoney() + (betMoney * 2.5)));
	}

	// TODO 최선영
	// 플레이어가 그냥 이겼을 때 베팅금 계산
	public void win() {
		// TODO 플레이어가 이겼을 경우 돈계산
		// 양쪽 카드 오픈 후 플레이어 점수 합이 더 높을 때
		// (플레이어는 BUST가 아니고) 딜러가 BUST일 때

		// 배팅금의 2배 돌려받음
		System.out.println("\n" + voP.getName() + "님 승리");
		voP.setMoney((voP.getMoney() + (betMoney * 2)));
	}

	// TODO 최선영
	// 플레이어가 졌을 때 베팅금 계산
	public void lose() {
		// TODO 플레이어가 졌을 경우 돈계산
		// 플레이어가 BUST일 때
		// 양쪽 카드 오픈 후 딜러 점수 합이 더 높을 때
		// (플레이어는 블랙잭이 아니고) 딜러가 블랙잭일 때

		// 배팅금 잃은 거라 그대로임

		System.out.println("\n" + voP.getName() + "님 패배");
		return;
	}

	// TODO 최선영
	// 무승부 베팅금 계산
	public void push() {
		// TODO 비겼을 경우 돈계산
		// 양쪽 카드 오픈 후 플레이어와 딜러 점수 합이 같을 때
		// 양쪽 다 블랙잭인 경우

		// 배팅금 다시 돌려줌
		System.out.println("\n" + "무 승 부");
		voP.setMoney((voP.getMoney() + betMoney));
	}

}
