package com.heart.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.heart.model.DeckVO;
import com.heart.service.BlackjackRule;

public class BlackJackRuleImplV1 implements BlackjackRule {

	protected final int lineNum = 36;

	protected Scanner scan;
	protected Random rnd = new Random();

	protected List<DeckVO> deckList; // 셔플된 덱을 저장하는 리스트
	private int deckIndex = 0 ; // deckList에서 하나씩 차례대로 선택할수있도록 참조하는 인덱스값

	protected BlackJackYubin makeDeck; // 카드덱을 만드는 메소드가 있는 클래스
	protected DeckVO deckVO; // 덱VO 객체 생성

	protected List<DeckVO> playerList; // 플레이어의 카드리스트
	protected List<DeckVO> dealerList; // 딜러의 카드리스트

	protected String playerName; // 플레이어의 이름
	protected int playerMoney; // 플레이어의 돈
	protected int betMoney; // 플레이어가 베팅한 금액

	
	
	public BlackJackRuleImplV1() {
		scan = new Scanner(System.in);
		makeDeck = new BlackJackYubin();
		deckVO = new DeckVO();

		playerMoney = 10000; // 플레이어의 돈 10000원으로 디폴트값 설정
	}

	@Override
	public void gameMainScreen() {
		// TODO 게임 첫 화면

		System.out.println("*".repeat(lineNum));
		System.out.println("*" + "            " + "블랙잭게임" + "            " + "*");
		System.out.println("*".repeat(lineNum));

		this.inputGamer();

		while (true) {
			System.out.println("\n현재" + playerName + "님의 재산은" + playerMoney + "원 입니다.");
			System.out.println("\n" + "-".repeat(lineNum));
			System.out.println("게임을 시작하시겠습니까?");
			System.out.println("▷ GO : 게임하기");
			System.out.println("▷ QUIT : 그만하기");
			System.out.println("-".repeat(lineNum));
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

		deckList = makeDeck.deckList; // deckList에 original 덱을 생성하여 저장
		makeDeck.createDeck(); // 새 게임이 시작될 때 마다 새로운 덱
		deckIndex = 0; // 새 게임이 시작될 때 마다 초기화

		playerList = new ArrayList<DeckVO>(); // 새 게임이 시작될 떄 마다 플레이어의 카드리스트 초기화
		dealerList = new ArrayList<DeckVO>(); // 새 게임이 시작될 떄 마다 딜러의 카드리스트 초기화

		this.shuffleDeck(); // 새로운 덱을 셔플하여 deckList에 다시 저장

		System.out.println("게임을 시작합니다.");

		betMoney = this.bettingMoney(); // 베팅금 메소드
		
		System.out.println("\n" + "-".repeat(lineNum*2));
		System.out.println("플레이어와 딜러에게 카드를 두 장씩 드립니다.");
		System.out.println("-".repeat(lineNum*2));
		
		this.handDeck(dealerList); // 딜러 카드 리스트에 카드 두 장 추가
		this.handDeck(dealerList);

		this.handDeck(playerList); // 플레이어 카드 리스트에 카드 두 장 추가
		this.handDeck(playerList);

		
		
	}

	@Override
	public void inputGamer() {
		// TODO 플레이어의 정보 입력

		System.out.println("-".repeat(lineNum));
		System.out.println("플레이어의 이름을 입력하세요.");
		System.out.print(" 이름 입력 >> ");
		playerName = scan.nextLine();
		System.out.println("-".repeat(lineNum));

	}

	@Override
	public Integer bettingMoney() {
		// TODO 베팅금 입력 메소드

		Integer intMoney = null;

		while (true) {
			System.out.println("-".repeat(lineNum));
			System.out.println("베팅금을 거세요.(100단위로 가능합니다.)");
			System.out.print(" 베팅금 입력 >> ");
			String strMoney = scan.nextLine();

			try {
				intMoney = Integer.valueOf(strMoney);
			} catch (NumberFormatException e) {
				System.out.println("베팅금은 숫자 입력만 가능합니다.");
				continue;
			}

			if (intMoney > playerMoney) {		
				System.out.println("베팅금은 소지금액보다 작아야합니다.");
				continue;
			} else if (!(intMoney % 100 == 0)) { // 베팅금은 100 단위만 가능
				System.out.println("베팅금은 100단위로만 가능합니다.");
				continue;
			}
			return intMoney;
		}
	}

	@Override
	public void shuffleDeck() {
		// TODO 조아영
		/*
		 * 생성된 덱을 셔플하는 메소드 셔플 후 리스트 deckList에 저장됨 deckIndex 값을 참조하여 deckList에서 차례대로 덱을
		 * 하나씩 뽑아내면 deckList는 이미 셔플된 덱의 리스트이기 때문에 랜덤으로 덱을 뽑을 수 있음과 동시에 중복성검사도 할 필요가 없다.
		 */

		Collections.shuffle(deckList); // deckList의 덱을 셔플하여 다시 deckList에 저장

	}

	@Override
	public void handDeck(List<DeckVO> list) {
		// TODO 조아영
		/*
		 * 딜러와 플레이어의 카드 리스트를 매개변수로 받아서 deckList에서 카드정보(카드이름,점수)를 0번부터 차례대로 배분하여 나눠주는 메소드
		 */

		DeckVO vo = deckList.get(deckIndex); // 셔플된 dexkList에서 deckIndex번째 값을 호출하여 vo에 저장

		vo.getDeck();
		vo.getValue();

		deckIndex++; // deckIndex 값은 handDeck이 실행될때마다 증가

		list.add(vo); // 매개변수로 받아온 list에 뽑은 카드를 추가

	}

	@Override
	public void checkBJ() {
		// TODO Auto-generated method stub

	}



	@Override
	public void hitAndStand() {
		//TODO 장혜미
		
		/*
		 * 플레이어가 히트를 할 경우 딜러가 히트를 할 경우
		 */
		

		// 플레이어의 카드와 점수
		for (int i = 0; i < deckList.size(); i++) {
			DeckVO vo = deckList.get(i);
			System.out.println(vo.getDeck());
			System.out.println(vo.getValue());
		}

		// 딜러의 카드와 점수

		// hit stand 선택지
		while (true) {
			System.out.println("=".repeat(lineNum*2));
			System.out.println("HIT 하시겠습니까?");
			System.out.println("HIT : 1");
			System.out.println("STAND : 0");
			System.out.println("=".repeat(lineNum*2));
			System.out.print(">> ");
			Integer hOs = scan.nextInt();
 
			//플레이어
			Integer gamerC = this.gamerRule(hOs);
			if(gamerC == null) {
				break;	
			} else {
				DeckVO vo = new DeckVO();
				//뭔가 해야함
				deckList.add(vo);
			}
			
			//딜러
			Integer dealerC = this.dealerRule(hOs);
		} // while end

	}

	
	
	protected Integer gamerRule(Integer num) {
		//TODO 장혜미 플레이어의 룰이 적용되는 히트 메서드
		
		Integer result = null;
		if (num == 1) {
			result = this.hit();
			// 플레이어의 규칙 : 21이상의 점수가 나올 시 Bust
			if (result > 21) {
				return null;
			}
			// 21이하면 카드리스트에 추가하고 점수 계산하고 다시 히트스탠드로 돌아가기
		} else if (num == 0) {
			return null;
		}
		return result;
	}
	
	
	
	
	protected Integer dealerRule(Integer num) {
		//TODO 장혜미 딜러의 룰이 적용되는 히트 메서드
		
		Integer result = null;
		if (num == 1) {
			result = this.hit();
			// 딜러의 규칙 : 17이상의 점수가 나올 시 히트 불가능
			if (result > 17 && result <= 21) {
				//딜러리스트에 값을 저장하고 vo리턴?
				return null;
			} else if (result > 21)
				return null;
		} else if (num == 0) {
			return null;
		}
		return result;
	}

	
	protected Integer hit() {
		// TODO 장혜미 히트하는 메서드
		
		// handDeck

		// handDeck 추가
		// 계산 하기

		// 계산된 점수값을 리턴하기 vo를 리턴하게 될 것 같음 아마...
		return null;
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
