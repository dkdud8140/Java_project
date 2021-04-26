package com.heart.impl;

import com.heart.model.PlayerVO;

public class Extends_04_Jang extends Extends_03_Lee{

	
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

	         if (hOs.equalsIgnoreCase("hit")) {
	            this.hit(voP); // 플레이어가 현재 가진 카드합이 리턴되는 메서드

	            if (voP.getScore() > 21) {
	               voP.setBust(true);
	               System.out.println("!BUST!");
	               break;
	            } else if (voP.getScore() == 21) {
	               System.out.println("Perfect");
	               break;
	            }
	            // if 점수가 21이상(bust)이면 break, 아니면 반복(이미됨)
	         } else if (hOs.equalsIgnoreCase("stand")) {
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
	         // 딜러 현재 가진 카드합이 리턴되는 메서드
	         if (voD.getScore() > 16) {
	            break;
	         } else {
	            this.hit(voD);
	         }
	      } // while end (딜러)

	      // 만약 딜러가 버스트라면
	      // voD의 bust판단 변수를 true로 처리하여 결과에서
	      // Bust때 딜러의 승리로 판정되지 않도록 한다.
	      if (voD.getScore() > 21)
	         voD.setBust(true);

	   }

	
	
	// TODO 장혜미
	// 히트 스탠드 묻는 메서드
	protected String askhOs() {

		System.out.println();
		System.out.println("HIT OR STAND?");
		System.out.println("HIT : hit");
		System.out.println("STAND : stand");
		System.out.print(">> ");
		String answer = scan.nextLine();

		return answer;
	}

	
	// TODO 장혜미
	// 카드 점수와 카드 리스트를 출력해주는 메소드
	protected void hit(PlayerVO vo) {
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
}
