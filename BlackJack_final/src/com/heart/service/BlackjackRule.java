package com.heart.service;

import com.heart.model.PlayerVO;

public interface BlackjackRule {

	public void gameMainScreen(); 				// 게임 첫화면
	public void playScreen();					// 게임의 진행 컨트롤 장소
	public void bettingMoney(); 				// 판돈 걸기
	
	public void gameResult(); 					// 게임 결과 판정


	
	
	//김소정
	public void inputGamer(); 					// 플레이어 이름 입력, 기존에 파일이 있으면 기존 파일 로드
	public void saveGame(String id, Integer money) ; // 플레이어의 이름, 재산을 저장하는 메소드
	public void checkBJ(PlayerVO vo); 			// 블랙잭 판정
	
	//이유빈
	public void askD_Down() ;					// 더블다운 여부

	
	//장혜미
	public void pHitAndStand(); 				// 플레이어 힛 앤 검증 된 판정
	public void dHitAndStand(); 				// 딜러 힛 앤 맞는 판정

	
	//조아영
	public void shuffleDeck(); 					// 덱 셔플
	public void handDeck(PlayerVO vo); 			// 덱 1장씩 배분
	public void insurance() ;					// 인슈어런스


	//최선영
	public void scoreResult(); 					// 돈 계산


	
}
