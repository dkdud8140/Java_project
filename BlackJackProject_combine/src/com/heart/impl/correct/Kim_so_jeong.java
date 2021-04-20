package com.heart.impl.correct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.heart.model.PlayerVO;

public class Kim_so_jeong extends BlackJackRuleImplV2 {
	protected final Integer 유저명 = 0;
	protected final Integer 저장된점수 = 1;
//	protected List<PlayerVO> playerInfo;
	protected PlayerVO playerVO;
	protected String playerName;
	protected Integer playerMoney;

	public Kim_so_jeong() {

//		playerInfo = new ArrayList<PlayerVO>();
		playerVO = new PlayerVO();
		//클래스를 인스턴스화하면 바로 호출될수 있도록 생성자에 메인 화면의 메서드를 호출한다.
		
		inputGamer();
		
		// 현재 메소드 2곳이상 계속 쓰이는 변수이므로 생성자에 지정한다
		// 여기에 저장된 VO는 불러올파일에있는 정보도 포함된다
		playerName = playerVO.getName();
		playerMoney = playerVO.getMoney();

	}
/*
 * 해결해야할 문제점 : 플레이어 머니가 계속 null값이 책정된다
 * V1에서는 배팅금이 1만원으로 생성자에서 정해져잇으나 
 * 아무래도 생성자에서는 지우고 초기 ID설정하면서 1만원의 배팅금을 같이 배정해주는게 좋을거같다.
 * 아래에 세이브게임에서 다시 VO를 불러와 Name과 Money에 대입하는 유사 초기화작업을 해줬는데 과연 필요한일인가 생각해봐야할듯하다
 */
	public String inputGamer() {
		// TODO 플레이어의 정보 입력
		System.out.println("-".repeat(lineNum));
		System.out.println("1. 새 게임 시작"); 
		System.out.println("2. 이전 게임 불러오기");
		System.out.println("3. EXIT ");
		System.out.println("   :   ");
		System.out.print(">> ");
		// 메뉴입력 프롬프트
	
		String strEnt = scan.nextLine();
		while (true) {
			try {
				Integer intEnt = Integer.valueOf(strEnt);
				//1번 입력시 아이디 입력할지, 다시입력할지를 정하는 초기화면
				if (intEnt == 1) {
					setId(); //처음 ID입력과, 초기 배팅금 1만원 지정
					gameMainScreen(playerName,playerMoney); //setId에서 반환한 결과값을 넣어주는 게임의 메인 스크린
					break;
				} else if (intEnt == 2) {
					loadGame(); 
				} else if (intEnt == 3) {
					break;
				}
			} catch (NumberFormatException e) {
//			e.printStackTrace();
				System.out.println("정확한 메뉴의 번호를 입력해주세요");
				continue;
			}
		}//while end
		return strEnt;
	}

	// 진입시 ID를 입력한다
	// 그리고 playerVO에 저장을 하고 이후에 저장 메서드를 위해 미리 PlayInfo리스트에 추가한다
	private String setId() {
		while (true) {
			System.out.println("이름을 입력합니다.같은 이름의 진행상황을 덮어씌웁니다.");
			System.out.println("-".repeat(lineNum));
			System.out.print(" 이름 입력 >> ");
			String id = scan.nextLine();
			Integer playerMoney = 10000;
			if (id.equals("QUIT")) {
				return null;
			}
			playerVO.setName(id);
			playerVO.setMoney(playerMoney);
//			playerInfo.add(playerVO);
			break;
			// 추가를 완료하면 game스크린을 불러온다
		}
		return playerName;
	}

	private void gameMainScreen(String playerName1,Integer playerMoney1) {
		
		// TODO 게임 첫 화면
		playerName1 = playerVO.getName();
		playerMoney1 = playerVO.getMoney();
		System.out.println("*".repeat(lineNum));
		System.out.println("*" + "            " + "블랙잭게임" + "            " + "*");
		System.out.println("*".repeat(lineNum));
		
		
		if (playerName1 == null) {
			System.out.println(" ** 게임을 종료합니다. ** ");
			System.out.println(" ... ");
			this.saveGame();
			return;
		}
		while (true) {
			System.out.println("\n현재" + playerName1 + "님의 재산은" + playerMoney1 + "원 입니다.");
			System.out.println("\n" + "-".repeat(lineNum));
			System.out.println("게임을 시작하시겠습니까?");
			System.out.println("▷ GO : 게임하기");
			System.out.println("▷ QUIT : 그만하기");
			System.out.println("-".repeat(lineNum));
			System.out.print(" ▷ ");
			String goQuit = scan.nextLine();
			// 게임 선택
			if (goQuit.equals("GO")) {
				this.playScreen();
			}
			// 게임종료 선택
			else if (goQuit.equals("QUIT")) {
				saveGame();
				System.out.println("\n게임을 종료합니다.");
				return;
			}
			// 잘못입력
			else {
				System.out.println("입력은 GO / QUIT 만 가능합니다.");
				continue;
			}

		}

	}
	private void saveGame() { // 저장파트
		System.out.println("게임을 저장합니다");
		while (true) {
			FileWriter fileWriter = null;
			PrintWriter out = null;
			String saveName = playerVO.getName();
			Integer saveMoney = playerVO.getMoney();

			try {
				fileWriter = new FileWriter("src/com/heart/impl/" + saveName + ".txt",true);
				out = new PrintWriter(fileWriter);

				out.println(saveName + ":" + saveMoney);

				out.flush();
				out.close();
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
//			e.printStackTrace();
				System.out.println("저장하는도중에 문제가 발생했습니다");
				continue;
			}
		} // while end
	}
	//새로운 게임을 시작하는데 아이디를 입력한다
	//입력하는 아이디가 저장이 되어있으면 점수를 불러오고 
	//값이 null이라면 그아이디가 새로운 아이디가 된다
	//이명령어는 로드게임에서 파일명을 확인하고 익셉션이 발생하면 newGame을 호출하는 방식으로 진행된다
		private void newGame() {
			this.gameMainScreen(playerName,playerMoney);
		}
	// setid에 입력했던 player이름을 파일목록에서 로드한다.
	// 만약 목록에 파일이 없으면 ,
	// 익셉션예외처리로 플레이어의 이름을 매개변수로 넣은 newGame을 호출한다
	// 만약 IO익셉션이 발생하면 저장파일이 문제가 잇꺼나 리드하는 상황에 문제가 있으므로
	// 다른 아이디를 입력받도록 한다
	// 이후문제
	private PlayerVO loadGame() {
		String fileName = ("src/com/heart/impl/" + playerName + ".txt");
		FileReader fileReader = null;
		BufferedReader buffer = null;
		
		
		
		try {
			fileReader = new FileReader(fileName);
			buffer = new BufferedReader(fileReader);
			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				String[] player = reader.split(":");
				playerVO = new PlayerVO();
				playerVO.setName(player[유저명]);
				playerVO.setMoney(Integer.valueOf(player[저장된점수]));
			} // while end
			buffer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			newGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("저장했던 파일에 문제가 있으므로 다른 이름을 입력하세요.");
		}
		return playerVO;
	}
}