package com.heart.impl.correct;

import com.heart.model.DeckVO;

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

				if (hOs.equals("hit")) {
					voP.setScore(this.gamerHit()); // 플레이어가 현재 가진 카드합이 리턴되는 메서드
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
					System.out.println("!입력 오류!");
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
				voD.setScore(dealerHit());// 딜러 현재 가진 카드합이 리턴되는 메서드
				if (voD.getScore() > 16)
					break;
				else if (voD.getScore() == 21)
					break;
			} // while end (딜러)
		}// dHitAndStand end

		// TODO 장혜미
		// 히트 스탠드 묻는 메서드
		protected String askhOs() {

			System.out.println("HIT OR STAND?");
			System.out.println("HIT : hit");
			System.out.println("STAND : stnd");
			System.out.print(">> ");
			String answer = scan.nextLine();

			return answer;
		}

		// TODO 장혜미
		// 플레이어의 카드 점수가 합산되는 히트 메서드
		protected Integer gamerHit() {

			this.handDeck(playerList);
			Integer sum = 0;

			int nSize = playerList.size();
			for (int i = 0; i < nSize; i++) {
				DeckVO vo = playerList.get(i);
				sum += vo.getValue();
			}
			System.out.println("-".repeat(lineNum));
			for (int i = 0; i < nSize; i++) {
				this.showOneCard(playerList, i);
			}

			System.out.println("플레이어의 점수 합 : " + sum);
			System.out.println("-".repeat(lineNum));

			return sum;
			// 리턴 썸값
		}

		// TODO 장혜미
		// 딜러의 카드 점수가 합산되는 히트 메서드
		protected Integer dealerHit() {
			// 딜러 카드 가져오기
			this.handDeck(dealerList);
			Integer sum = 0;

			int nSize = dealerList.size();
			for (int i = 0; i < dealerList.size(); i++) {
				DeckVO vo = dealerList.get(i);
				sum += vo.getValue();
			}

			System.out.println("-".repeat(lineNum));
			for (int i = 0; i < nSize; i++) {
				this.showOneCard(dealerList, i);
			}
			System.out.println("딜러의 점수 합 : " + sum);
			System.out.println("-".repeat(lineNum));

			return sum;
		}

}
