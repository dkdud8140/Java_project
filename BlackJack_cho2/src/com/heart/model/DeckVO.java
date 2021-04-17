package com.heart.model;

public class DeckVO {

	String deck;
	Integer value;

	Integer valueSum;

	
	public Integer getValueSum() {
		return valueSum;
	}

	public void setValueSum(Integer valueSum) {
		this.valueSum = valueSum;
	}

	@Override
	public String toString() {
		return "DeckVO [deck=" + deck + ", value=" + value + "]";
	}

	public String getDeck() {
		return deck;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}