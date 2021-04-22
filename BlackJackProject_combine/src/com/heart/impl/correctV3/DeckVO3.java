package com.heart.impl.correctV3;

public class DeckVO3 {

	protected String deck;
	protected Integer value;

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