package com.heart.impl.correct;

public class Extends_06_Choi extends Extends_05_Cho{

	// TODO 최선영 돈계산
		@Override
		public void gamerMoney() {
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

		public void win_bj() {
			// TODO 플레이어가 블랙잭으로 이겼을 경우
			System.out.println("\n" + voP.getName() + "님 BlackJack으로 승리");
			voP.setMoney((int) (voP.getMoney() + (betMoney * 2.5)));
		}

		public void win() {
			// TODO 플레이어가 이겼을 경우 돈계산
			// 양쪽 카드 오픈 후 플레이어 점수 합이 더 높을 때
			// (플레이어는 BUST가 아니고) 딜러가 BUST일 때

			// 배팅금의 2배 돌려받음
			System.out.println("\n" + voP.getName() + "님 승리");
			voP.setMoney((voP.getMoney() + (betMoney * 2)));
		}

		public void lose() {
			// TODO 플레이어가 졌을 경우 돈계산
			// 플레이어가 BUST일 때
			// 양쪽 카드 오픈 후 딜러 점수 합이 더 높을 때
			// (플레이어는 블랙잭이 아니고) 딜러가 블랙잭일 때

			// 배팅금 잃은 거라 그대로임

			System.out.println("\n" + voP.getName() + "님 패배");
			return;
		}

		public void push() {
			// TODO 비겼을 경우 돈계산
			// 양쪽 카드 오픈 후 플레이어와 딜러 점수 합이 같을 때
			// 양쪽 다 블랙잭인 경우

			// 배팅금 다시 돌려줌
			System.out.println("\n" + "무 승 부");
			voP.setMoney((voP.getMoney() + betMoney));
		}
}
