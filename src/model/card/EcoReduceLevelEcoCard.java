package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoReduceLevelEcoCard extends EcoCard {

	public EcoReduceLevelEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoReduceLevelEcoCard";
		this.cName = "EcoReduceLevelEcoCard";
		this.price = 30;
	}

	@Override
	public int useCard() {
		return GameState.CARD_REDUCELEVEL;
		}

}
