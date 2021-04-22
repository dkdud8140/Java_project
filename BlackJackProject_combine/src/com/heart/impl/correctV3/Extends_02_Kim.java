package com.heart.impl.correctV3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.heart.model.DeckVO;
import com.heart.model.PlayerVO;

public class Extends_02_Kim extends BlackJackRuleImplV4 {

//	protected String basePath = "src/com/heart/game/";
//	protected PlayerVO pVO;
//	protected Integer newMoney = 10000;
	

	// 블랙잭 판단 메소드
//	@Override
	// public 이 아닌 protected로 변경요청
//	public Boolean checkBJ(List<DeckVO3> list) {

//		//DeckVO vo0 = list.get(0);
//		DeckVO vo1 = list.get(1);
//
//		if (vo0.getValue() == 1 && vo1.getValue() == 10) {
//			return true;
//		} else if (vo1.getValue() == 1 && vo0.getValue() == 10) {
//			return true;
//		} else {
//			return false;
//		}

//	}

	
	public void inputGamer() {
		

		System.out.println("\n" + "-".repeat(50));
		System.out.println("플레이어의 이름을 입력하세요.(QUIT : 종료)");
		System.out.print(" 이름 입력 >> ");
		String id = scan.nextLine();

		this.loadGame(id);
		
		if (id.equals("QUIT")) {
			return;
		}

		System.out.println("-".repeat(50));
	}
	
	
	
	
	
	
	public void loadGame(String id) {

		while (true) {
			System.out.println("게임을 불러옵니다");
			
			if (id.equals("")) {
				System.out.println("이름은 반드시 입력하세요");
				continue;
			}
			
			FileReader fileReader = null;
			BufferedReader buffer = null;

			try {
				fileReader = new FileReader(basePath + id);
				buffer = new BufferedReader(fileReader);

				
				String reader = buffer.readLine();
				String[] source = reader.split(":");
				
				System.out.println("저장된 기록을 불러왔습니다");
				
				
				voP.setName(id);
				voP.setMoney(Integer.valueOf(source[1]));
				System.out.printf("%s 님의 잔액은 %d 입니다.\n",id,voP.getMoney());
				buffer.close();
				return;

			} catch (FileNotFoundException e) {
				System.out.println("\n저장된 파일이 없습니다.");
				System.out.println("입력하신 이름으로 게임을 진행합니다.");
				voP.setName(id);
				return;
			} catch (IOException e) {
				System.out.println("\n파일에 문제가 있습니다");
				System.out.println("입력하신 이름으로 게임을 진행합니다.");
				voP.setName(id);
				return;
			}
		} // while end
		
	
	}
	
	
	
	
	}

	
