package gameEx;

import com.callor.game.impl.GameRuleImplV1;
import com.callor.game.model.DeckVO;
import com.callor.game.service.GameRule;

public class Ex_01 {
	public static void main(String[] args) {

//		DeckVO deckVO = new DeckVO();
//		
//		
//		deckVO.cardNameMaker();
//		deckVO.cardScore();
//		deckVO.printDebuging();
//		

		GameRule blackJack = new GameRuleImplV1();

		blackJack.gameMain();

	}
}
