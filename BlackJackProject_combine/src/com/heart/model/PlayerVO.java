package com.heart.model;

import java.util.List;

public class PlayerVO {
	
	private String name; // 이름
	private Integer money; // 재산
	private Integer score; // 오픈된 카드들의 점수 합
	private Boolean bj; // 블랙잭 여부

	private Boolean bust; // BUST 여부
	private Boolean d_Down; // 더블다운 여부
	
	private List<DeckVO> deckList ;
	
	
	public PlayerVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public List<DeckVO> getDeckList() {
		return deckList;
	}

	public void setDeckList(List<DeckVO> deckList) {
		this.deckList = deckList;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getScore() {
		return score;
	}
	
	
	
	public void setScore(Integer value) { // 뽑은 카드의 숫자값 매개변수로 받아와서 기존 점수합에 더함
		this.score += value;
	}
	public Boolean getBj() {
		return bj;
	}
	public void setBj(Boolean bj) {
		this.bj = bj;
	}
	public Boolean getBust() {
		return bust;
	}
	public void setBust(Boolean bust) {
		this.bust = bust;
	}
	
	public Boolean getD_Down() {
		return d_Down;
	}
	public void setD_Down(Boolean d_Down) {
		this.d_Down = d_Down;
	}
	
	@Override
	public String toString() {
		return "PlayerVO [name=" + name + ", money=" + money + ", score=" + score + ", bj=" + bj + ", bust=" + bust
				+ ", d_Down=" + d_Down + "]";
	}
	
}