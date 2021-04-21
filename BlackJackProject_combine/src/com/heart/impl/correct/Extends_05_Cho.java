package com.heart.impl.correct;

import java.util.List;

import com.heart.model.DeckVO;

public class Extends_05_Cho extends Extends_04_Jang2 {

	// TODO 조아영
	// 덱을 셔플하는 메소드
	@Override
	public void shuffleDeck() {
		/*
		 * 생성된 덱을 셔플하는 메소드 셔플 후 리스트 deckList에 저장됨 deckIndex 값을 참조하여 deckList에서 차례대로 덱을
		 * 하나씩 뽑아내면 deckList는 이미 셔플된 덱의 리스트이기 때문에 랜덤으로 덱을 뽑을 수 있음과 동시에 중복성검사도 할 필요가 없다.
		 */
//			Collections.shuffle(deckList); 

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

		// 디버깅 코드
//			for(int i = 0 ; i < dSize ; i ++) {
//				DeckVO vo = deckList.get(i) ;
//				System.out.println(vo.toString());
//			}

	}

	// TODO 조아영
	// 카드를 한 장씩 나눠주는 메소드
	@Override
	public void handDeck(List<DeckVO> list) {

		// 딜러와 플레이어의 카드 리스트를 매개변수로 받아서
		// deckList에서 카드정보(카드이름,점수)를 0번부터 차례대로 배분하여 나눠주는 메소드

		DeckVO vo = deckList.get(deckIndex); // 셔플된 dexkList에서 deckIndex번째 값을 호출하여 vo에 저장

		deckIndex++; // deckIndex 값은 handDeck이 실행될때마다 증가

		list.add(vo); // 매개변수로 받아온 list에 뽑은 카드를 추가
		
		voP.setScore(vo.getValue());

	}

	public void insurance() {

		DeckVO vo0 = dealerList.get(0);
		
		while (true) {
			if (vo0.getValue() == 1) {
				
				inMoney = betMoney / 2 ;
				System.out.println("\n"+"-".repeat(lineNum));
				System.out.println("\n인슈어런스 하시겠습니까?");
				System.out.println(" Y : 네  /  N : 아니오");
				String strIn = scan.nextLine();

				if (strIn.equals("Y")) {
					System.out.println("\n인슈어런스로 " + inMoney + "를 지불하였습니다.");
					System.out.println("-".repeat(lineNum));
					voP.setMoney(voP.getMoney() - inMoney );
					inYN = true;
					return;
				} else if (strIn.equals("N")) {
					System.out.println("\n게임을 계속 진행합니다.");
					System.out.println("-".repeat(lineNum));
					return;
				}

				else {
					System.out.println("Y 나 N만 입력하세요.");
					continue;
				}
			}
			
			else return ;

		} // end while

	}

}
