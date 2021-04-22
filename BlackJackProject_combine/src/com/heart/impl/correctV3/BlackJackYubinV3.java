package com.heart.impl.correctV3;

import java.util.List;

public class BlackJackYubinV3{

	//public List<DeckVO3> deckList;
	protected String[] suit;
	protected String[] strNum;
	
	
	public BlackJackYubinV3() {
		//deckList= new ArrayList<DeckVO3>();
		suit =  new String[] {"Heart","Diamond","Clover","Space"};
		strNum = new String[] {"A","2","3","4","5","6","7","8","9","10","K","Q","J"};
	}
	
	public void createDeck(List<DeckVO3> deckList){ 
		//TODO 카드형식을 만들고 각 카드에 따른 값을 만듬
		for(int i = 0; i < suit.length ; i++) {
			for(int j = 0 ; j < strNum.length ; j++) {
				String deck = suit[i] + " "  + strNum[j];
				//Heart A Diamond 2 형식으로
				DeckVO3 deckVO = new DeckVO3();
				deckVO.setDeck(deck);
				try {//strNum 을 Integer형으로 바꿈
					//오류가 없으면 strNum 에 담긴 값은 숫자값 >> 숫가 그대로 
					//deckVO클래스 의 value값에 담고 deckList에 저장
					Integer intNum = Integer.valueOf(strNum[j]);
					deckVO.setValue(intNum);
					deckList.add(deckVO);
				} catch (NumberFormatException e) {
					// 만약 Integer 바꾸는데 오류가 났으면 숫자가 아닌 문자형이담긴 카드
					// 만약 A라면 value 1을 그외의 문자는 10을 deckVO클래스에 담고 List 에 저장
					if(strNum[j].equals("A")) {
						Integer intNum = 1;
						deckVO.setValue(intNum);
						deckList.add(deckVO);
					}else {
						Integer intNum = 10;
						deckVO.setValue(intNum);
						deckList.add(deckVO);
					}
				}
			}
		}
		
	}
	
}
