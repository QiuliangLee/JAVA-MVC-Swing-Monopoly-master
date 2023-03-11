package model.card;

import context.GameState;

import model.PlayerModel;


public class EcoStopEcoCard extends EcoCard {

	public EcoStopEcoCard(PlayerModel owner) {
		super(owner);
		this.name = "EcoStopEcoCard";
		this.cName ="EcoStopEcoCard";
		this.price = 50;
	}

	@Override
	public int useCard() {
		return GameState.CARD_STOP;
	}
	
	@Override
	public int cardBuff(){
		return GameState.CARD_BUFF_STOP;
	}
}
