package com.callor.game.service;

import java.util.List;

public interface GameRule {

	public void gameMain() ; 		//게임 메인 출력 메소드
	public void bettingMoney() ;	//돈베팅메소드
	public void shuffleCard() ; 	//카드 배분 메소드
	public List<Integer> shuffleDeck() ; 	//카드 덱 셔플 메소드
	
	public void selectMenu() ;		//선택지메소드
	public void blackJackGameMain();//게임진행
	
	public void insurance() ; 		//인셔런스 메소드
	public void doubleDown() ;		//더블다운 메소드
	public void Split() ;			//스플릿 메소드
	public void hitOrStand() ;		//Hit 혹은 Stand 선택 메소드
	public void surrender() ;		//Surrender 메소드
	
	public void resultMoney() ;		//게임결과 게이머의 판돈
	
}
