package model.card;

import context.GameState;
import model.PlayerModel;


public class EcoCrossingEcoCard extends EcoCard {

	public EcoCrossingEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoCrossingEcoCard";
		this.cName = "EcoCrossingEcoCard";
		this.price = 120;
	}

	@Override
	public int useCard() {
		return GameState.CARD_CROSSING;
	}

}
