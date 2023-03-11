package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoControlDiceEcoCard extends EcoCard {

	int diceNumber;
	
	public EcoControlDiceEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoControlDiceEcoCard";
		this.cName = "EcoControlDiceEcoCard";
		this.price = 30;
	}

	@Override
	public int useCard() {
		
		return GameState.CARD_CONTROLDICE;
	}
}
