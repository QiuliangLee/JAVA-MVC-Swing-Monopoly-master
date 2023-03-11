package model.card;

import context.GameState;

import model.PlayerModel;


public class AveragerPoorEcoCard extends EcoCard {

	public AveragerPoorEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "AveragerPoorEcoCard";
		this.cName = "AveragerPoorEcoCard";
		this.price = 200;
	}

	@Override
	public int useCard() {
		return GameState.CARD_AVERAGERPOOR;
	}

}
