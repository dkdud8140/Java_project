package com.heart.impl;

import com.heart.model.DeckVO;
import com.heart.model.PlayerVO;

public class Extends_05_Cho extends Extends_04_Jang{

	
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

			int dSize = deckList.size();

			for (int i = 0; i < 50; i++) {
				int num = rnd.nextInt(dSize);
				DeckVO vo1 = deckList.get(i);
				DeckVO voNum = deckList.get(num);
				DeckVO tempVO = vo1;

				vo1 = voNum;
				voNum = tempVO;

				deckList.set(i, vo1);
				deckList.set(num, voNum);

			}


		}
		
		
		
		
		// TODO 조아영
		// 카드를 한 장씩 나눠주는 메소드
		@Override
		public void handDeck(PlayerVO vo) {

			// 랜덤셔플된 deckList에서
			// deckList번째에 해당하는 카드 정보 하나를 불러와서
			// vo의 deckList에 add한다

			vo.setDeckList(deckList, deckIndex);
			// deckList.add(vo.get(num));

			// 카드를 한 장씩 나눠줄 때 마다
			// deckList의 deckIndex번째 카드의 점수를
			// vo의 score에 누적한다.
			DeckVO vo1 = deckList.get(deckIndex);
			vo.setAddScore(vo1.getValue());

			deckIndex++; // deckIndex 값은 handDeck이 실행될때마다 증가
		}
		
		
		
		
		

		// TODO 조아영 인슈런스
		public void insurance() {

			while (true) {

				DeckVO vo1 = voD.getDeckList().get(0);

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
