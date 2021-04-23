package com.heart.impl;

public class Extends_03_Lee extends Extends_02_Kim{

	// TODO 이유빈
	// 더블다운 여부를 물어보는 메소드
	public void askD_Down() {
		if (voP.getMoney() - betMoney >= 0) {
			// playScreen 에서 bettingMoney 를 실행해서 이미 voP.getMoney() 에서 betMoney가 빠져있는상태
			// 그 값에서 - 다시한번 betMoney 를 뺀값이 0 이상이면 실행(doubleDown은 베팅금 2배)

			while (true) {
				System.out.println("더블다운을 진행 하시겠습니까?( Y / N)");
				System.out.print(" ▷ ");
				String strAnswer = scan.nextLine();
				if (strAnswer.trim().equals("")) {
					// 아무것도 입력안했을때
					System.out.println(" Y 와 N 중 반드시 선택해 주세요");
					continue;
				}
				if (strAnswer.trim().equalsIgnoreCase("Y")) {
					this.doubleDown();

				} else if (!(strAnswer.trim().equalsIgnoreCase("N"))) {// Y 값도 아니고 N 값도 아닌 그 외의 값
					System.out.println("Y 또는 N 중에 선택해 주세요");
					continue;
				}
				break;
			}
		}
	}
	
	
	
	// TODO 이유빈
	// 더블다운이 실행되는 메소드
	// 이름때문에 더블다운 진행한거 같은 그냥 askDDown 에서 진행
	public void doubleDown() {
		voP.setD_Down(true);// true 로 변경
		this.handDeck(voP);// 악 한장만 받고 끝

		int sum = voP.getMoney() - betMoney;
		voP.setMoney(sum);

		betMoney *= 2; // 베팅금 2배로
		System.out.println("더블다운하셨으므로 베팅금이 두배가 됩니다.");

		if (voP.getScore() > 21) {
			voP.setBust(true);
			System.out.println("!BUST!");
		}

	}
	
	
	
	
	
}
