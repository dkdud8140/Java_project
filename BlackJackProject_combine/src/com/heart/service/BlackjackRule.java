package com.heart.service;

import java.util.List;

import com.heart.model.DeckVO;

public interface BlackjackRule {

	public void gameMainScreen(); 				// 게임 첫화면

	public void playScreen();

	public String inputGamer(); 					// 게이머 정보 입력 이름 , 초기자본(디폴트)

	public Integer bettingMoney(); 				// 판돈 걸기

	public void shuffleDeck(); 					// 덱 셔플 : 유빈

	public void handDeck(List<DeckVO> list); 	// 덱 1장씩 배분 : 아영

	public Boolean checkBJ(List<DeckVO> list); 	// 블랙잭 판정 : 소정

	public void pHitAndStand(); 				// 플레이어 힛 앤 검증 된 판정 : 혜미

	public void dHitAndStand(); 				// 딜러 힛 앤 맞는 판정 : 혜미

	public void gameResult(); 					// 게임 결과 판정

	public void gamerMoney(); 					// 돈 계산 : 선영

}
