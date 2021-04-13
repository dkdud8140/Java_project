package com.callor.game.model;

import java.util.Scanner;

public class GamerMoneyVO {

	private int startMoney = 10000; 	//처음 자본은 10000원으로 동일
	private Integer bettingMoney ; 			//베팅금
	
	protected Scanner scan ;
	
	public GamerMoneyVO() {
		
		bettingMoney = 0; 
		scan = new Scanner(System.in) ;
		
	}
	
	public void setBettingMoney(int bettingMoney) {
		this.bettingMoney = bettingMoney;
	}
	
	
	public int getGamerMoney() {
		return startMoney ;
	}
	
}
