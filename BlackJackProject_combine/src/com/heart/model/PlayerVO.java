package com.heart.model;

public class PlayerVO {
   
   private String name; // 이름
   private Integer money; // 재산
   private Integer score; // 오픈된 카드들의 점수 합
   private Boolean bj; // 블랙잭 여부
   private Boolean bust; // BUST 여부
   
   public PlayerVO() {
	   
	   money =0 ;
	   score = 0 ;
	   bj = false ;
	   bust = false ;
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
   public void setScore(Integer score) {
      this.score = score;
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
   
   @Override
   public String toString() {
      return "PlayerVO [name=" + name + ", money=" + money + ", score=" + score + ", bj=" + bj + ", bust=" + bust
            + "]";
   }
   
}