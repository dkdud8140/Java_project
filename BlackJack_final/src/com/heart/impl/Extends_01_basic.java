package com.heart.impl;

import java.util.List;

import com.heart.model.DeckVO;
import com.heart.model.PlayerVO;

public class Extends_01_basic extends BlackJackRuleImplV_Final {

	
	
	
	
	// TODO 게임 메인 화면
	// 모든 메소드가 컨트롤 되는 장소
	@Override
	public void gameMainScreen() {
		System.out.println("*".repeat(lineNum));
		System.out.println("*" + "            " + "블랙잭게임" + "            " + "*");
		System.out.println("*".repeat(lineNum));

		// 플레이어 이름 입력
		// 기존에 있는 이름이라면 파일 로드
		this.inputGamer();

		// inputGame에서 Quit 입력시 게임종료
		if (voP.getName().equalsIgnoreCase("QUIT")) {
			System.out.println("\n ** 게임을 종료합니다. ** ");
			return;
		}

		// 메뉴 입력
		while (true) {
			System.out.println("\n현재 " + voP.getName() + "님의 재산은 " + voP.getMoney() + "원 입니다.");
			System.out.println("\n" + "-".repeat(lineNum));
			System.out.println("게임을 시작하시겠습니까?");
			System.out.println("▷ 0 : 새로운 이름 설정하기");
			System.out.println("▷ 1 : 게임 하기");
			System.out.println("▷ 2 : 게임 저장하기");
			System.out.println("▷ 3 : 게임 끝내기");
			System.out.println("-".repeat(lineNum));
			System.out.print(" ▷ ");
			String selecM = scan.nextLine();

			Integer intM;
			try {
				intM = Integer.valueOf(selecM);
			} catch (NumberFormatException e) {
				System.out.println("숫자 0, 1, 2, 3만 입력하세요");
				continue;
			}

			//새로운  플레이어 이름 입력
			if (intM == 0) {
				voP = new PlayerVO();
				this.inputGamer();
			}

			// 게임 하기 선택
			else if (intM == 1) {
				this.playScreen();
			}

			// 게임 저장하기 선택
			else if (intM == 2) {
				this.saveGame(voP.getName(), voP.getMoney());
			}

			// 게임종료 선택
			else if (intM == 3) {
				System.out.println("\n ** 게임을 종료합니다. ** ");
				return;
			}

			
			// 잘못입력
			else {
				System.out.println("입력은 1, 2, 3만 가능합니다.");
				continue;
			}

		}

	}

	// TODO 게임 플레이 전반을 컨트롤 하는 장소
	@Override
	public void playScreen() {

		// 플레이어의 소지금이 100원 이하(최소 베팅금)일땐
		// 게임 강제 종료
		if (voP.getMoney() < 100) {
			System.out.println("소지금이 부족합니다");
			System.out.println("게임선택화면으로 돌아갑니다.");
			return;
		}

		makeDeck.createDeck(deckList); // 새 게임이 시작될 때 마다 새로운 덱
		deckIndex = 0; // 새 게임이 시작될 때 마다 초기화

		// 플레이어와 딜러의
		// (순서대로) 점수의 합, 블랙잭여부, 버스트여부, 더블다운여부, 덱리스트를
		// 초기화
		voP.resetVO(0, false, false, false, null);
		voD.resetVO(0, false, false, false, null);

		this.shuffleDeck(); // 새로운 덱을 셔플하여 deckList에 다시 저장

		System.out.println("\n게임을 시작합니다.");

		// 베팅금 메소드를 실행하여 베팅금을 변수에 저장
		this.bettingMoney();
		if (betMoney == -1)
			return; // QUIT 입력시 게임 종료

		System.out.println("\n" + "-".repeat(lineNum));
		System.out.println("플레이어와 딜러에게 카드를 두 장씩 드립니다.");
		System.out.println("-".repeat(lineNum));

		// 딜러 카드 리스트에 카드 두 장 추가
		this.handDeck(voD);
		this.handDeck(voD);

		// 플레이어 카드 리스트에 카드 두 장 추가
		this.handDeck(voP);
		this.handDeck(voP);

		// 플레이어 블랙잭 판단.
		this.checkBJ(voP);

		// 카드 보여주는 메소드
		this.showCard();

		// 인슈어런스 여부 물어보기
		this.insurance();

		// 플레이어가 블랙잭
		if (voP.getBj())
			System.out.println("블랙잭입니다!");

		// 플레이어가 블랙잭이 아닐 경우
		else if (!voP.getBj()) {

			this.askD_Down(); // pHitAndStand 도 여기서 진행하는게 더 보기 좋을 거같아서 pHitAndStand에 조건으로 진행

			if (voP.getD_Down() == false) { // 더블다운을 진행하지 않을때 pHitAndStand 진행
				this.pHitAndStand();
			}

		}

		// 딜러의 블랙잭 판단
		this.checkBJ(voD);

//		 플레이어가 블랙잭이 아니고 동시에
//		 딜러가 블랙잭이 아니면 딜러가 힛앤스탠드를 진행한다.
		if (!voP.getBj() && !voD.getBj()) {
			this.dHitAndStand();
		}

		// 결과창
		this.gameResult();

		// 돈 반환
		this.scoreResult();

	}

	
	
	
	// TODO 베팅금 입력 메소드
	@Override
	public void bettingMoney() {

		while (true) {
			System.out.println("\n" + "-".repeat(lineNum));
			System.out.println("베팅금을 거세요.(100단위로 가능합니다.)");
			System.out.println("QUIT 입력시 메뉴로 돌아갑니다.");
			System.out.print(" 베팅금 입력 >> ");
			String strMoney = scan.nextLine();

			if (strMoney.equalsIgnoreCase("QUIT")) {
				betMoney = -1;
				break ;
			}
			
			try {
				betMoney = Integer.valueOf(strMoney);
				

			} catch (NumberFormatException e) {
				System.out.println("베팅금은 숫자 입력만 가능합니다.");
				continue;
			}

			if (betMoney  > voP.getMoney()) {
				System.out.println("베팅금은 소지금액보다 작아야합니다.");
				continue;
			}

			else if (!(betMoney % 100 == 0)) { // 베팅금은 100 단위만 가능
				System.out.println("베팅금은 100단위로만 가능합니다.");
				continue;
			}

			int sum = voP.getMoney() - betMoney ;
			voP.setMoney(sum);
			break;
		}
	}

	
	
	
	// TODO 카드 한 장을 보여주는 메소드
	protected void showOneCard(List<DeckVO> list, int num) {

		// list의 num번째 카드를 보여주는 메소드
		DeckVO vo = list.get(num);
		System.out.println(vo.getDeck());
	}
	
	
	
	
	
	// TODO 딜러와 플레이어가 처음 받은
		// 2장의 카드 보여주는 메소드
		protected void showCard() {

			// 딜러의 카드 보여줌
			System.out.println(voD.getName() + "의 카드는 다음과 같습니다.");
			// 첫번째 카드만 보여주고 한장은 히든카드로 남겨둠
			this.showOneCard(voD.getDeckList(), 0);
			System.out.println("????");
			System.out.println("-".repeat(lineNum));

			// 플레이어의 카드 보여줌
			System.out.println(voP.getName() + "의 카드는 다음과 같습니다.");
			this.showOneCard(voP.getDeckList(), 0);
			this.showOneCard(voP.getDeckList(), 1);

			// 만약에 플레이어가 블랙잭이라면
			if (voP.getBj()) {
				// 블랙잭 선언
				System.out.println("블랙잭!");
			} else {
				// 아니라면 점수 보여주기
				System.out.println(voP.getName() + "의 점수 합 : " + voP.getScore());
			}

			System.out.println("-".repeat(lineNum));
		}

		
		
		
	
		// TODO 게임 결과 창
		@Override
		public void gameResult() {

			System.out.println();
			System.out.println("*".repeat(lineNum));
			System.out.println("···게임결과···");
			System.out.println("*".repeat(lineNum));
			System.out.println();

			printResult(voP);
			printResult(voD);

		}

		
		
		
		
		
		// TODO 결과화면 출력 메소드
		public void printResult(PlayerVO vo) {

			System.out.println();
			System.out.println(vo.getName() + "님의 게임결과");
			System.out.println("-".repeat(lineNum));

			showResultCard(vo.getDeckList());

			System.out.println(".".repeat(lineNum));

			// 블랙잭 표시
			if (vo.getBj())
				System.out.println(vo.getName() + " 블랙잭!");

			// BUST 표시
			else if (vo.getBust())
				System.out.println(vo.getName() + " BUST!");

			// 둘 다 아니면 점수 출력
			else
				System.out.println(vo.getName() + "님의 점수 : " + vo.getScore());

			System.out.println("-".repeat(lineNum));

		}

		
		
		
		
		// TODO 카드 리스트 보여주는 메소드
		protected void showResultCard(List<DeckVO> list) {

			int nSize = list.size();
			for (int i = 0; i < nSize; i++) {
				this.showOneCard(list, i);
			}
		}
	
	
	

}
