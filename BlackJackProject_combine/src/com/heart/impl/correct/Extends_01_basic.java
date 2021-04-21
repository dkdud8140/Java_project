package com.heart.impl.correct;

import java.util.ArrayList;
import java.util.List;
import com.heart.model.DeckVO;

public class Extends_01_basic extends BlackJackRuleImplV2 {

	
	// TODO 게임 메인 화면
	// 모든 메소드가 콘트롤 되는 장소
	@Override
	public void gameMainScreen() {

		System.out.println("*".repeat(lineNum));
		System.out.println("*" + "            " + "블랙잭게임" + "            " + "*");
		System.out.println("*".repeat(lineNum));

		// 플레이어의 이름 입력 메소드
		// 플레이어의 이름을 새로 입력받거
		// 기존에 저장되어 있는 플레이어를 불러온다.
		voP.setName(this.inputGamer());

		
		//inputGamer에서 입력받은 문자열이 QUIT면 게임을 종료한다
		if (voP.getName() == null) {
			System.out.println("\n ** 게임을 종료합니다. ** ");
			return;
		}

		
		//게임 선택 메뉴
		while (true) {
			System.out.println("\n현재 " + voP.getName() + "님의 재산은 " + voP.getMoney() + "원 입니다.");
			System.out.println("\n" + "-".repeat(lineNum));
			System.out.println("게임을 시작하시겠습니까?");
			System.out.println("▷ 1 : 게임하기");
			System.out.println("▷ 2 : 저장하기");
			System.out.println("▷ 3 : 그만하기");
			System.out.println("-".repeat(lineNum));
			System.out.print(" ▷ ");
			String goQuit = scan.nextLine();

			
			// 게임 선택
			if (goQuit.equals("1")) {
				this.playScreen();
			}

			// 게임종료 선택
			else if (goQuit.equals("QUIT")) {
				System.out.println("\n ** 게임을 종료합니다. ** ");
				return;
			}

			// 잘못입력
			else {
				System.out.println("입력은 GO / QUIT 만 가능합니다.");
				continue;
			}

		}

	}

	
	// TODO 게임 플레이 전반을 콘트롤 하는 장소
	@Override
	public void playScreen() {
		deckList = makeDeck.deckList; // deckList에 original 덱을 생성하여 저장
		makeDeck.createDeck(); // 새 게임이 시작될 때 마다 새로운 덱
		deckIndex = 0; // 새 게임이 시작될 때 마다 초기화

		playerList = new ArrayList<DeckVO>(); // 새 게임이 시작될 떄 마다 플레이어의 카드리스트 초기화
		dealerList = new ArrayList<DeckVO>(); // 새 게임이 시작될 떄 마다 딜러의 카드리스트 초기화

		this.shuffleDeck(); // 새로운 덱을 셔플하여 deckList에 다시 저장

		System.out.println("\n게임을 시작합니다.");

		// 베팅금 메소드
		betMoney = this.bettingMoney();

		System.out.println("\n" + "-".repeat(lineNum));
		System.out.println("플레이어와 딜러에게 카드를 두 장씩 드립니다.");
		System.out.println("-".repeat(lineNum));

		// 딜러 카드 리스트에 카드 두 장 추가
		this.handDeck(dealerList);
		this.handDeck(dealerList);

		// 플레이어 카드 리스트에 카드 두 장 추가
		this.handDeck(playerList);
		this.handDeck(playerList);

	//블랙잭 확인하기 위한 디버깅 코드
//		DeckVO vo0 = playerList.get(0);
//		vo0.setValue(1);
//		DeckVO vo1 = playerList.get(1);
//		vo1.setValue(10);
		
		
		//딜러의 공개된 한장이 A일 경우 플레이어에게 인슈런스 여부 물어봄
		this.insurance();

		for (int i = 0; i < 2; i++) {
			DeckVO vo = playerList.get(i);
			Integer sum = 0;
			sum += vo.getValue();
			voP.setScore(sum);
		}

		for (int i = 0; i < 2; i++) {
			DeckVO vo = dealerList.get(i);
			Integer sum = 0;
			sum += vo.getValue();
			voD.setScore(sum);
		}

		// 플레이어 블랙잭 판단.
		voP.setBj(this.checkBJ(playerList));

		// 카드 보여주는 메소드
		this.showCard();
		

		// 플레이어가 블랙잭이 아닐 경우 hit & stand 진행
		if (voP.getBj())
			System.out.println("블랙잭입니다!");
		else if (!voP.getBj())
			this.pHitAndStand();

		//딜러의 블랙잭 판단
		voD.setBj(this.checkBJ(dealerList));

		
		//플레이어가 블랙잭이 아니고 딜러가 블랙잭이 아니어야 딜러의 힛앤스탠드가 진행된다.
		if (!voP.getBj() && !voD.getBj()) {		
			this.dHitAndStand();
			if (voD.getScore() > 21)
				voD.setBust(true);
		}

		this.gameResult(); // 결과창
		this.gamerMoney(); // 돈 반환

	}

	
	
	// TODO 플레이어의 정보 입력
	@Override
	public String inputGamer() {

		System.out.println("\n" + "-".repeat(lineNum));
		System.out.println("플레이어의 이름을 입력하세요.(QUIT : 종료)");
		System.out.print(" 이름 입력 >> ");
		voP.setName(scan.nextLine());
		if (voP.getName().equals("QUIT"))
			return null;

		System.out.println("-".repeat(lineNum));
		return voP.getName();

	}

	
	
	// TODO 베팅금 입력 메소드
	@Override
	public Integer bettingMoney() {

		Integer intMoney = null;

		while (true) {
			System.out.println("\n" + "-".repeat(lineNum));
			System.out.println("베팅금을 거세요.(100단위로 가능합니다.)");
			System.out.print(" 베팅금 입력 >> ");
			String strMoney = scan.nextLine();

			try {
				intMoney = Integer.valueOf(strMoney);
				int sum = voP.getMoney() - intMoney;
				voP.setMoney(sum);
			} catch (NumberFormatException e) {
				System.out.println("베팅금은 숫자 입력만 가능합니다.");
				continue;
			}

			if (intMoney > voP.getMoney()) {
				System.out.println("베팅금은 소지금액보다 작아야합니다.");
				continue;
			} else if (!(intMoney % 100 == 0)) { // 베팅금은 100 단위만 가능
				System.out.println("베팅금은 100단위로만 가능합니다.");
				continue;
			}
			return intMoney;
		}
	}

	
	
	// TODO 카드 한 장을 보여주는 메소드
	protected void showOneCard(List<DeckVO> list, int num) {
		DeckVO vo = list.get(num);
		System.out.println(vo.getDeck());
	}

	
	
	// TODO 초기 카드 보여주는 메소드
	protected void showCard() {

		// 딜러의 카드 보여줌
		System.out.println("딜러의 카드는 다음과 같습니다.");
		this.showOneCard(dealerList, 0);
		System.out.println("????");
		System.out.println("-".repeat(lineNum * 2));

		// 플레이어의 카드 보여줌

		System.out.println(voP.getName() + "의 카드는 다음과 같습니다.");
		this.showOneCard(playerList, 0);
		this.showOneCard(playerList, 1);

		if (voP.getBj()) {
			System.out.println("블랙잭!");
		} else {
			System.out.println("플레이어의 점수 합 : " + voP.getScore());
		}
		System.out.println("-".repeat(lineNum * 2));
	}
	
	
	
	// TODO 게임 결과 화면 출력
	@Override
	public void gameResult() {

		System.out.println("*".repeat(lineNum));
		System.out.println("···게임결과···");
		System.out.println("*".repeat(lineNum));

		System.out.println(voP.getName() + "님의 게임결과");
		System.out.println("-".repeat(lineNum));

		showResultCard(playerList);

		System.out.println(".".repeat(lineNum));

		if (voP.getBj()) {
			System.out.println(voP.getName() + " 블랙잭!");
		} else if (voP.getBust()) {
			System.out.println(voP.getName() + " BUST!");
		} else {
			System.out.println(voP.getName() + "님의 점수 : " + voP.getScore());
		}

		System.out.println("*".repeat(lineNum));

		System.out.println("딜러님의 게임결과");
		System.out.println("-".repeat(lineNum));

		showResultCard(dealerList);

		System.out.println(".".repeat(lineNum));
		if (voD.getBj()) {
			System.out.println("딜러 블랙잭!");
		} else if (voD.getBust()) {
			System.out.println("딜러 Bust!");
		} else {
			System.out.println("딜러의 점수 : " + voD.getScore());
		}
		System.out.println();

	}

	
	
	// TODO 카드 리스트 보여주는 메소드
	protected void showResultCard(List<DeckVO> list) {

		int nSize = list.size();
		for (int i = 0; i < nSize; i++) {
			this.showOneCard(list, i);
		}
	}


}
