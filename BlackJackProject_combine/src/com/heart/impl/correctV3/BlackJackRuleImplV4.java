package com.heart.impl.correctV3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.heart.model.PlayerVO;

public class BlackJackRuleImplV4 implements BlackjackRule3 {

	protected final int lineNum = 36;

	protected Scanner scan;
	protected Random rnd;

	protected List<DeckVO3> deckList; // 셔플된 덱을 저장하는 리스트
	protected int deckIndex = 0; // deckList에서 하나씩 차례대로 선택할수있도록 참조하는 인덱스값

	protected BlackJackYubinV3 makeDeck; // 카드덱을 만드는 메소드가 있는 클래스
	protected DeckVO3 DeckVO3; // 덱VO 객체 생성

	protected PlayerVO3 voP; // 플레이어 정보 저장 값
	protected PlayerVO3 voD; // 딜러 정보 저장 값

	protected int betMoney; // 플레이어가 베팅한 금액

	protected boolean inYN; // 인슈어런스 판단 변수
	protected int inMoney; // 인슈어런스 머니

	// TODO 생성자
	public BlackJackRuleImplV4() {
		scan = new Scanner(System.in);
		rnd = new Random();

		makeDeck = new BlackJackYubinV3();
		DeckVO3 = new DeckVO3();
		deckList = new ArrayList<DeckVO3>();

		voP = new PlayerVO3();
		voD = new PlayerVO3();

		voD.setName("딜러");
		voP.setMoney(10000);// 플레이어의 돈 10000원으로 디폴트값 설정

	}

	// TODO 게임 메인 화면
	// 모든 메소드가 콘트롤 되는 장소
	@Override
	public void gameMainScreen() {
		System.out.println("*".repeat(lineNum));
		System.out.println("*" + "            " + "블랙잭게임" + "            " + "*");
		System.out.println("*".repeat(lineNum));

		// 플레이어 이름 입력
		// 기존에 있는 이름이라면 파일 로드
		voP.setName(this.inputGamer());

		// inputGame에서 Quit 입력시 게임종료
		if (voP.getName() == null) {
			System.out.println("\n ** 게임을 종료합니다. ** ");
			return;
		}

		// 메뉴 입력
		while (true) {
			System.out.println("\n현재 " + voP.getName() + "님의 재산은 " + voP.getMoney() + "원 입니다.");
			System.out.println("\n" + "-".repeat(lineNum));
			System.out.println("게임을 시작하시겠습니까?");
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
				System.out.println("숫자 1, 2, 3만 입력하세요");
				continue;
			}

			// 게임 선택
			if (intM == 1) {
				this.playScreen();
			}

			// 게임 저장하기 선택
			else if (intM == 2) {
				// 게임저장 메소드
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

	// TODO 게임 플레이 전반을 콘트롤 하는 장소
	@Override
	public void playScreen() {

		makeDeck.createDeck(deckList); // 새 게임이 시작될 때 마다 새로운 덱
		deckIndex = 0; // 새 게임이 시작될 때 마다 초기화

		voP.setDeckList(null); // 플레이어가 가진 카드 리스트 초기화
		voD.setDeckList(null); // 딜러가 가진 카드 리스트 초기화

		voP.setScore(0); // 플레이어의 점수 초기화
		voD.setScore(0); // 딜러의 점수 초기화

		this.shuffleDeck(); // 새로운 덱을 셔플하여 deckList에 다시 저장

		System.out.println("\n게임을 시작합니다.");

		// 베팅금 메소드를 실행하여 베팅금을 변수에 저장
		betMoney = this.bettingMoney();

		System.out.println("\n" + "-".repeat(lineNum));
		System.out.println("플레이어와 딜러에게 카드를 두 장씩 드립니다.");
		System.out.println("-".repeat(lineNum));

		// 딜러 카드 리스트에 카드 두 장 추가
		this.handDeck(voD);
		this.handDeck(voD);

		// 플레이어 카드 리스트에 카드 두 장 추가
		this.handDeck(voP);
		this.handDeck(voP);

		// 블랙잭 확인하기 위한 디버깅 코드
//		DeckVO3 vo0 = playerList.get(0);
//		vo0.setValue(1);
//		DeckVO3 vo1 = playerList.get(1);
//		vo1.setValue(10);

		// 플레이어 블랙잭 판단.
		voP.setBj(this.checkBJ(voP.getDeckList()));

		// 카드 보여주는 메소드
		this.showCard();

		// 인슈어런스 여부 물어보기
		this.insurance();

		// 플레이어가 블랙잭이 아닐 경우 hit & stand 진행
		if (voP.getBj())
			System.out.println("블랙잭입니다!");
		else if (!voP.getBj())
			this.pHitAndStand();

		// 딜러의 블랙잭 판단
		voD.setBj(this.checkBJ(voD.getDeckList()));

		// 플레이어가 블랙잭이 아니고 동시에
		// 딜러가 블랙잭이 아니면 딜러가 힛앤스탠드를 진행한다.
		if (!voP.getBj() && !voD.getBj()) {
			this.dHitAndStand();

			// 만약 딜러가 버스트라면
			// voD의 bust판단 변수를 true로 처리하여 결과에서
			// Bust때 딜러의 승리로 판정되지 않도록 한다.
			if (voD.getScore() > 21)
				voD.setBust(true);
		}

		// 결과창
		this.gameResult();

		// 돈 반환
		this.gamerMoney();

	}

	// TODO 김소정
	// 플레이어의 정보 입력
	@Override
	public String inputGamer() {

		// 플레이어의 이름을 입력받아서 동일한 이름의 파일이 있다면
		// 그 파일을 불러오거나 혹은 새로 시작할지 물어보기

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
	protected void showOneCard(List<DeckVO3> list, int num) {

		// list의 num번째 카드를 보여주는 메소드
		DeckVO3 vo = list.get(num);
		System.out.println(vo.getDeck());
	}

	// TODO 초기 카드 보여주는 메소드
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

	// TODO 조아영
	// 덱을 셔플하는 메소드
	@Override
	public void shuffleDeck() {

		/*
		 * 생성된 덱을 셔플하는 메소드
		 * 
		 * 셔플 후 리스트 deckList에 저장됨 deckIndex 값을 참조하여 deckList에서 차례대로 덱을 하나씩 뽑아내면
		 * deckList는 이미 셔플된 덱의 리스트이기 때문에 랜덤으로 덱을 뽑을 수 있음과 동시에 중복성검사도 할 필요가 없다.
		 * 
		 */
//		Collections.shuffle(deckList); 

		int dSize = deckList.size();

		for (int i = 0; i < 50; i++) {
			int num = rnd.nextInt(dSize);
			DeckVO3 vo1 = deckList.get(i);
			DeckVO3 voNum = deckList.get(num);
			DeckVO3 tempVO = vo1;

			vo1 = voNum;
			voNum = tempVO;

			deckList.set(i, vo1);
			deckList.set(num, voNum);

		}

		// 디버깅 코드
//		for(int i = 0 ; i < dSize ; i ++) {
//			DeckVO3 vo = deckList.get(i) ;
//			System.out.println(vo.toString());
//		}

	}

	// TODO 조아영
	// 카드를 한 장씩 나눠주는 메소드
	@Override
	public void handDeck(PlayerVO3 vo) {

		// 랜덤셔플된 deckList에서
		// deckList번째에 해당하는 카드 정보 하나를 불러와서
		// vo의 deckList에 add한다

		vo.setDeckList(deckList, deckIndex);
		// deckList.add(vo.get(num));

		// 카드를 한 장씩 나눠줄 때 마다
		// deckList의 deckIndex번째 카드의 점수를
		// vo의 score에 누적한다.
		DeckVO3 vo1 = deckList.get(deckIndex);
		vo.setAddScore(vo1.getValue());

		deckIndex++; // deckIndex 값은 handDeck이 실행될때마다 증가
	}

	
	
	// TODO 김소정
	// 블랙잭 판단 메소드
	@Override
	public Boolean checkBJ(List<DeckVO3> list) {

		DeckVO3 vo0 = list.get(0);
		DeckVO3 vo1 = list.get(1);

		if (vo0.getValue() == 1 && vo1.getValue() == 10) {
			return true;
		} else if (vo1.getValue() == 1 && vo0.getValue() == 10) {
			return true;
		} else {
			return false;
		}

	}

	// 일단 진행하려고
	// 혜미씨 메소드 PlayerVO 수정했어요

	// TODO 장혜미
	// 플레이어와 딜러의 히트 스탠드를 진행한다
	@Override
	public void pHitAndStand() {
		// 플레이어와 딜러 모두 카드를 2장씩 들고 있는 상황
		// 블랙잭이 아님
		// 플레이어가 히트와 스탠드 중 선택할 수 있음

		// 플레이어 진행
		while (true) {
			String hOs = this.askhOs(); // 히트 스탠드를 묻는 프롬프트와 입력받기

			if (hOs.equals("hit")) {
				this.hit(voP); // 플레이어가 현재 가진 카드합이 리턴되는 메서드

				if (voP.getScore() > 21) {
					voP.setBust(true);
					System.out.println("!BUST!");
					break;
				}

				else if (voP.getScore() == 21)
					break;

				// if 점수가 21이상(bust)이면 break, 아니면 반복(이미됨)
			} else if (hOs.equals("stnd")) {
				System.out.println("-".repeat(lineNum));
				System.out.println("플레이어가 STAND를 선언했습니다");
				break;
			} else {
				System.out.println("\n!입력 오류!");
				System.out.println("HIT와 STAND 중 하나를 선택해주세요");
				System.out.println("블랙잭은 중도 포기할 수 없습니다");
				continue;
			}
		} // while end (플레이어)

	}// pHitAndStand end

	
	
	
	// TODO 장혜미
	// 딜러의 히트앤 스탠드 진행
	@Override
	public void dHitAndStand() {
		
		
		// 선택지 없이 카드만 공개
		while (true) {
			this.hit(voD);
			;// 딜러 현재 가진 카드합이 리턴되는 메서드
			if (voD.getScore() > 16)
				break;
			else if (voD.getScore() == 21)
				break;
		} // while end (딜러)
	}// dHitAndStand end

	
	
	
	
	// TODO 장혜미
	// 히트 스탠드 묻는 메서드
	protected String askhOs() {

		System.out.println();
		System.out.println("HIT OR STAND?");
		System.out.println("HIT : hit");
		System.out.println("STAND : stnd");
		System.out.print(">> ");
		String answer = scan.nextLine();

		return answer;
	}

	
	
	
	
	// TODO 장혜미
	// 카드 점수가 합산되는 히트 메서드
	protected void hit(PlayerVO3 vo) {
		this.handDeck(vo);

		int nSize = vo.getDeckList().size();

		System.out.println("-".repeat(lineNum));

		System.out.println(vo.getName() + "의 카드는 다음과 같습니다");
		for (int i = 0; i < nSize; i++) {
			System.out.println(vo.getDeckList().get(i).getDeck());
		}

		System.out.println("점수 합 : " + vo.getScore());
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
	public void printResult(PlayerVO3 vo) {

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
	protected void showResultCard(List<DeckVO3> list) {

		int nSize = list.size();
		for (int i = 0; i < nSize; i++) {
			this.showOneCard(list, i);
		}
	}

	
	
	
	// TODO 최선영 돈계산
	@Override
	public void gamerMoney() {

		if (voD.getBj()) {
			System.out.println("인슈어런스 보상금을 돌려드립니다.");
			System.out.println((inMoney * 2) + "원 지급");
		}

		// 플레이어, 딜러 둘 다 블랙잭,BUST 아님
		// 양 쪽 점수 비교
		if (voP.getBust()) {
			lose();
		} else if ((voP.getScore() > voD.getScore()) || voD.getBust()) {
			win();
		} else if (voD.getScore() > voP.getScore()) {
			lose();
		} else if (voD.getScore() == voP.getScore()) {
			push();
		}

		if (voP.getBj()) {
			if (voP.getBj()) {
				this.push();
			} else {
				this.win_bj();
			}
		}

	}

	
	
	
	
	// TODO 최선영 돈계산
	public void win_bj() {
		// TODO 플레이어가 블랙잭으로 이겼을 경우
		System.out.println("\n" + voP.getName() + "님 BlackJack으로 승리");
		voP.setMoney((int) (voP.getMoney() + (betMoney * 2.5)));
	}
	
	
	
	

	// TODO 최선영 돈계산
	public void win() {
		// TODO 플레이어가 이겼을 경우 돈계산
		// 양쪽 카드 오픈 후 플레이어 점수 합이 더 높을 때
		// (플레이어는 BUST가 아니고) 딜러가 BUST일 때

		// 배팅금의 2배 돌려받음
		System.out.println("\n" + voP.getName() + "님 승리");
		voP.setMoney((voP.getMoney() + (betMoney * 2)));
	}

	
	
	
	// TODO 최선영 돈계산
	public void lose() {
		// TODO 플레이어가 졌을 경우 돈계산
		// 플레이어가 BUST일 때
		// 양쪽 카드 오픈 후 딜러 점수 합이 더 높을 때
		// (플레이어는 블랙잭이 아니고) 딜러가 블랙잭일 때

		// 배팅금 잃은 거라 그대로임

		System.out.println("\n" + voP.getName() + "님 패배");
		return;
	}
	
	
	

	// TODO 최선영 돈계산
	public void push() {
		// TODO 비겼을 경우 돈계산
		// 양쪽 카드 오픈 후 플레이어와 딜러 점수 합이 같을 때
		// 양쪽 다 블랙잭인 경우

		// 배팅금 다시 돌려줌
		System.out.println("\n" + "무 승 부");
		voP.setMoney((voP.getMoney() + betMoney));
	}
	
	
	
	

	// TODO 조아영 인슈런스
	public void insurance() {

		while (true) {

			DeckVO3 vo1 = voD.getDeckList().get(0);

			// 딜러VO 의
			// deckList(지니고 있는 카드 리스트)의
			// 첫번째 카드의 값이 ( 공개된 카드)
			// A이면 플레이어에게 인슈런스 할 건지 물어본다.
			if (vo1.getValue() == 1) {

				// 인슈런스 금액은 베팅금의 절반이다
				inMoney = betMoney / 2;
				System.out.println("\n" + "-".repeat(lineNum));
				System.out.println("\n인슈어런스 하시겠습니까?");
				System.out.println(" Y : 네  /  N : 아니오");
				String strIn = scan.nextLine();

				if (strIn.equals("Y")) {

					// 인슈러언스 선택시
					System.out.println("\n인슈어런스로 " + inMoney + "를 지불하였습니다.");
					System.out.println("-".repeat(lineNum));

					// 플레이어는 인슈어런스 금액 지불
					voP.setMoney(voP.getMoney() - inMoney);

					// 인슈어런스 여부를 true로 출력 후
					// 딜러가 블랙잭이 나올 경우 쓸 수 있도록
					inYN = true;
					return;
				}

				// 인슈어런스 진행 X
				else if (strIn.equals("N")) {
					System.out.println("\n게임을 계속 진행합니다.");
					System.out.println("-".repeat(lineNum));
					return;
				}

				// 입력 오류
				else {
					System.out.println("Y 나 N만 입력하세요.");
					continue;
				}
			}

			//// 딜러VO 의
			// deckList(지니고 있는 카드 리스트)의
			// 첫번째 카드의 값이 ( 공개된 카드)
			// A가 아니면 그냥 리턴
			else
				return;

		} // end while

	}



}
