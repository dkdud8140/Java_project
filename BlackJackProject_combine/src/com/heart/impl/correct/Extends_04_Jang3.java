package com.heart.impl.correct;

import java.util.List;

import com.heart.model.DeckVO;


public class Extends_04_Jang3 extends Extends_03_Lee {
	
	@Override
	public void pHitAndStand() {
		while (true) {
			String hOs = this.askhOs(); // 히트 스탠드를 묻는 프롬프트와 입력받기

			if (hOs.equals("hit")) {
				voP.setScore(this.hit(playerList)); // 플레이어가 현재 가진 카드합이 리턴되는 메서드
				
				if (voP.getScore() > 21) {
					voP.setBust(true);
					System.out.println("!BUST!");
					break;
				} else if (voP.getScore() == 21) {
					break;
				}

			} else if (hOs.equals("stnd")) {
				System.out.println("-".repeat(lineNum));
				System.out.println("플레이어가 STAND를 선언했습니다");
				break;
			} else {
				System.out.println("!입력 오류!");
				System.out.println("HIT와 STAND 중 하나를 선택해주세요");
				System.out.println("블랙잭은 중도 포기할 수 없습니다");
				continue;
			}
		} // while end (플레이어)

	}// pHitAndStand end
	
	@Override
	public void dHitAndStand() {
		while (true) {
			voD.setScore(hit(dealerList));// 딜러 현재 가진 카드합이 리턴되는 메서드
			if (voD.getScore() > 16)
				break;
			else if (voD.getScore() == 21)
				break;
		} // while end (딜러)
	}// dHitAndStand end
	
	protected String askhOs() {

		System.out.println("HIT OR STAND?");
		System.out.println("HIT : hit");
		System.out.println("STAND : stnd");
		System.out.print(">> ");
		String answer = scan.nextLine();
		return answer;
	}
	protected Integer hit(List<DeckVO> list) {
		this.handDeck(list); //점수 합을 어떻게 넣을지

		if(list.equals(playerList)) {
			voP.setScore(deckVO.getValue());
			return voP.getScore();
		} else if(list.equals(dealerList)) {
			voD.setScore(deckVO.getValue());
		}
		return voD.getScore();		
		
//		Integer sum = 0;
//
//		int nSize = list.size();
//		for (int i = 0; i < nSize; i++) {
//			DeckVO vo = list.get(i);
//			sum += vo.getValue();
//		}
//		System.out.println("-".repeat(lineNum));
//		for (int i = 0; i < nSize; i++) {
//			System.out.println(list.get(i).getDeck());
//		}
//		System.out.println("점수 합 : " + sum);
//		System.out.println("-".repeat(lineNum));
//

	}
}