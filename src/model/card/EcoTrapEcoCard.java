package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoTrapEcoCard extends EcoCard {

	public EcoTrapEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoTrapEcoCard";
		this.cName = "EcoTrapEcoCard";
		this.price = 120;
	}

	@Override
	public int useCard() {
		return GameState.CARD_TRAP;
	}

}
