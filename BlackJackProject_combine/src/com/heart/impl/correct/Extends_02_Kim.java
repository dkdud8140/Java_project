package com.heart.impl.correct;

import java.util.List;

import com.heart.model.DeckVO;

public class Extends_02_Kim extends Extends_01_basic{
	
	// TODO 김소정
		// 블랙잭 판단 메소드
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
	
	
}
