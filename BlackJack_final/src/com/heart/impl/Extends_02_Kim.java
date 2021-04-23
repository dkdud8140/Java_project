package com.heart.impl;

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

	// TODO 김소정
	// 플레이어의 정보 입력
	@Override
	public void inputGamer() {

		while (true) {
			System.out.println("\n" + "-".repeat(50));
			System.out.println("플레이어의 이름을 입력하세요.(QUIT : 종료)");
			System.out.print(" 이름 입력 >> ");
			String id = scan.nextLine();
			if (id.trim().equals("")) {
				System.out.println("아이디는 빈칸으로 사용하실 수 없습니다");
				continue;
			}

			if (id.trim().equalsIgnoreCase("QUIT")) {
				voP.setName(id);
				break;
			}

			this.loadGame(id);
			voP.setName(id);

			break;
		} // while end

	}

	// TODO 김소정
	// 플레이어의 정보 불러오기
	public void loadGame(String id) {
		id = voP.getName();

		while (true) {
			System.out.println("게임을 불러옵니다");

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
				System.out.printf("%s 님의 잔액은 %d 입니다.\n", id, voP.getMoney());
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

	// TODO 김소정
	// 블랙잭 판단 메소드
	@Override
	public void checkBJ(PlayerVO vo) {

		List<DeckVO> voList0 = vo.getDeckList();
		DeckVO vo0 = voList0.get(0);

		List<DeckVO> voList1 = vo.getDeckList();
		DeckVO vo1 = voList1.get(1);

		if (vo0.getValue() == 1 && vo1.getValue() == 10) {
			vo.setBj(true);
		} else if (vo1.getValue() == 1 && vo0.getValue() == 10) {
			vo.setBj(true);
		}

	}

	// TODO 김소정
	// 게임 저장하기
	public void saveGame(String id, Integer money) {

		/*
		 * 입력한 아이디로 게임을 저장한다 voP.setName(this.inputGamer());
		 */

		while (true) {
			System.out.println("진행상황을 저장합니다." + "이미 저장되었다면 덮어씌움");

			FileWriter fileWriter = null;
			PrintWriter out = null;

			try {
				fileWriter = new FileWriter(basePath + id);
				out = new PrintWriter(fileWriter);

				out.printf("%s:%d\n", id, money);
				out.flush();
				out.close();
				System.out.println("\n-save complete-");
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

}
