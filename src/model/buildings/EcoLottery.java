package model.buildings;

import model.PlayerModel;
import context.GameState;


public class EcoLottery extends EcoPark {

	private PlayerModel player;
	
	public EcoLottery(int posX, int posY) {
		super(posX, posY);
		this.EcoPark = "EcoLottery";
	}
	
	@Override
	public int getEcoParkEvent() {
		return GameState.LOTTERY_EVENT;
	}
}
