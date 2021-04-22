package com.heart.impl.correct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.heart.model.DeckVO;
import com.heart.model.PlayerVO;

public class Extends_02_Kim extends Extends_01_basic {

	protected String basePath = "src/com/heart/game/";
	protected PlayerVO pVO;
	protected Integer newMoney = 10000;
	
	public Extends_02_Kim() {
		// TODO 김소정
		
// Test가 안되서 임시로만든 생성자
		pVO = new PlayerVO();
		pVO.setName(this.inputGamer()); 
		pVO.setMoney(newMoney);
		this.loadGame(pVO.getName(), pVO.getMoney());
		this.saveGame(pVO.getName(), pVO.getMoney());
	}

	// 블랙잭 판단 메소드
	@Override
	// public 이 아닌 protected로 변경요청
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

	/*
	 * 입력한 아이디로 게임을 저장한다 voP.setName(this.inputGamer());
	 */
	private void saveGame(String id, Integer money) {
		while (true) {
			System.out.println("진행상황을 저장합니다."
					+ "이미 저장되었다면 덮어씌움");
			
			FileWriter fileWriter = null;
			PrintWriter out = null;

			try {
				fileWriter = new FileWriter(basePath+id,true);
				out = new PrintWriter(fileWriter);

				out.printf("%s:%d\n",id,money);
				out.flush();
				out.close();
				System.out.println("-save complete-");
				break;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(id + "파일 생성 오류");
				System.out.println("파일이름을 다시입력하세요");
				continue;
			}
		} // while end

	}

	// 파일이름을 불러온다
	// 건너뛰거나 파일에 문제가있으면 새로운 게임으로 시작한다.
	public void loadGame(String id, Integer money) {

		while (true) {
			System.out.println("게임을 불러옵니다");
			if (id.equals("")) {
				System.out.println("게임을 처음부터 시작합니다.");
				pVO = new PlayerVO();
				pVO.setName(id);
				pVO.setMoney(newMoney);
				return;
			}
			FileReader fileReader = null;
			BufferedReader buffer = null;

			try {
				fileReader = new FileReader(basePath + id);
				buffer = new BufferedReader(fileReader);

				String reader = buffer.readLine();
				String[] source = reader.split(":");
				System.out.println("저장된 기록을 불러왔습니다");
				this.pVO = new PlayerVO();
				pVO.setName(id);
				pVO.setMoney(Integer.valueOf(source[1]));
				System.out.printf("%s 님의 잔액은 %d 입니다.",id,pVO.getMoney());
				buffer.close();
				return;

			} catch (FileNotFoundException e) {
				System.out.println("저장된 파일이 없습니다.");
				pVO = new PlayerVO();
				pVO.setName(id);
				pVO.setMoney(newMoney);
				return;
			} catch (IOException e) {
				System.out.println("파일에 문제가 있습니다");
				pVO = new PlayerVO();
				pVO.setName(id);
				pVO.setMoney(newMoney);
				return;
			}
		} // while end

	}

	public String inputGamer() {

		System.out.println("\n" + "-".repeat(50));
		System.out.println("플레이어의 이름을 입력하세요.(QUIT : 종료)");
		System.out.print(" 이름 입력 >> ");
		String id = scan.nextLine();

		if (id.equals("QUIT"))
			return null;

		System.out.println("-".repeat(50));
		return id;
	}

}
