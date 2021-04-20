package com.heart.impl.correct;

import java.util.List;

import com.heart.model.DeckVO;
import com.heart.model.PlayerVO;

public class Extends_02_Kim extends Extends_01_basic{
	// TODO 김소정
	/*
	 *  블랙잭 판단 메소드
	 *  플레이어 이름을 저장하는 메소드
	 *  플레이어 이름을 로딩하는 메소드
	 */
	
	protected PlayerVO playerVO;// 클래스를 완성하기위해 임시로 초기화함 지워도됨
	protected String basePath = "com/heart/impl/correct";
	  public Extends_02_Kim() {
	  }
		@Override
		public Boolean checkBJ(List<DeckVO> list) {

			DeckVO vo0 = list.get(0);
			DeckVO vo1 = list.get(1);

			if (vo0.getValue() == 1 && vo1.getValue() == 10) {
				return true;
			} else if (vo1.getValue() == 1 && vo0.getValue() == 10) {
				return true;
			} else {
				return false;
			}

		}
		private void saveScore() {
			while(true) {
				
				System.out.println("저장할 이름을 입력하세요");
				
				
			}//while end
		
			
			
			
		}
	
	
}
