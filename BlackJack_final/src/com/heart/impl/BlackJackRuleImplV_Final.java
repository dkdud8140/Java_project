package com.heart.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.heart.model.DeckVO;
import com.heart.model.PlayerVO;
import com.heart.service.BlackjackRule;

public class BlackJackRuleImplV_Final implements BlackjackRule {

	protected final int lineNum = 36;

	protected Scanner scan;
	protected Random rnd;

	
	// 파일 저장시 저장 베이스 루트 문자열
	protected String basePath = "src/com/heart/game/";
	
	// 셔플된 덱을 저장하는 리스트
	protected List<DeckVO> deckList;

	// deckList에서 하나씩 차례대로 선택할수있도록 참조하는 인덱스값
	protected int deckIndex = 0; 

	// 카드덱을 만드는 메소드가 있는 클래스
	protected BlackJackYubin makeDeck; 

	// 덱VO 객체 생성
	protected DeckVO deckVO;
	// 플레이어 정보 저장 값
	protected PlayerVO voP; 
	// 딜러 정보 저장 값
	protected PlayerVO voD; 

	// 플레이어가 베팅한 금액
	protected int betMoney; 

	// 인슈어런스 판단 변수
	protected boolean inYN;
	// 인슈어런스 머니
	protected int inMoney; 

	
	
	
	// TODO 생성자
	public BlackJackRuleImplV_Final() {
		scan = new Scanner(System.in);
		rnd = new Random();

		makeDeck = new BlackJackYubin();
		deckVO = new DeckVO();
		deckList = new ArrayList<DeckVO>();

		voP = new PlayerVO();
		voD = new PlayerVO("딜러");

	}
	
	
	
	
	
	

	// TODO 게임 메인 화면
	// 모든 메소드가 컨트롤 되는 장소
	@Override
	public void gameMainScreen() {
	}
	
	

	// TODO 김소정
	// 플레이어의 정보 입력
	@Override
	   public void inputGamer() {
	   }
	
	
	// TODO 김소정
	// 플레이어의 정보 불러오기
	 public void loadGame(String id) {
	   }
	

	
	
	
	// TODO 게임 플레이 전반을 컨트롤 하는 장소
	@Override
	public void playScreen() {
	}
	
	
	// TODO 조아영
	// 덱을 셔플하는 메소드
	@Override
	public void shuffleDeck() {
	}
	
	
	// TODO 조아영
	// 카드를 한 장씩 나눠주는 메소드
	@Override
	public void handDeck(PlayerVO vo) {
	}
		
		
	
	// TODO 베팅금 입력 메소드
	@Override
	public void bettingMoney() {
	}
	
	
	// TODO 딜러와 플레이어가 처음 받은
	// 2장의 카드 보여주는 메소드
	protected void showCard() {
	}
	
	// TODO 카드 한 장을 보여주는 메소드
	protected void showOneCard(List<DeckVO> list, int num) {
	}
	
	
	
	// TODO 이유빈
	// 더블다운 여부를 물어보는 메소드
	@Override
	public void askD_Down() {
	}

	
	
	// TODO 이유빈
	// 더블다운이 실행되는 메소드
	// 이름때문에 더블다운 진행한거 같은 그냥 askDDown 에서 진행
	public void doubleDown() {
	}

	

	// TODO 김소정
	// 블랙잭 판단 메소드
	@Override
	public void checkBJ(PlayerVO vo) {
	}


	
	
	// TODO 장혜미
	// 플레이어와 딜러의 히트 스탠드를 진행한다
	@Override
	public void pHitAndStand() {
	}

	
	// TODO 장혜미
	// 딜러의 히트앤 스탠드 진행
	@Override
	public void dHitAndStand() {
	}
	
	// TODO 장혜미
	// 히트 스탠드 묻는 메서드
	protected String askhOs() {
		return null;
	}

	
	// TODO 장혜미
	// 카드 점수와 카드 리스트를 출력해주는 메소드
	protected void hit(PlayerVO vo) {
	}

	
	
	
	// TODO 게임 결과 창
	@Override
	public void gameResult() {
	}

	
	// TODO 결과화면 출력 메소드
	public void printResult(PlayerVO vo) {
	}

	
	// TODO 카드 리스트 보여주는 메소드
	protected void showResultCard(List<DeckVO> list) {
	}

	
	
	// TODO 최선영 
	// 게임 결과 승리와 패배 판정 메소드
	// 동시에 플레이어 베팅금 반환
	@Override
	public void scoreResult() {
	}

	// TODO 최선영 
	// 플레이어가 블랙잭으로 이겼을 때 베팅금 계산
	public void win_bj() {
	}

	// TODO 최선영 
	// 플레이어가 그냥 이겼을 때 베팅금 계산
	public void win() {
	}
	
	// TODO 최선영 
	// 플레이어가 졌을 때 베팅금 계산
	public void lose() {
	}
	
	// TODO 최선영 
	// 무승부 베팅금 계산
	public void push() {
	}

	
	
	
	// TODO 조아영 인슈런스
	@Override
	public void insurance() {
	}

	
	
	
	// TODO 김소정
	// 게임 저장하기
	@Override
	public void saveGame(String id, Integer money) {
	}
	
	
	
	
	
	

}
