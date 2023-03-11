package model.buildings;

import model.PlayerModel;
import context.GameState;


public class Origin extends EcoPark {
	
	private int passReward;
	
	private int reward;

	private PlayerModel player;

	public Origin(int posX, int posY) {
		super(posX, posY);
		this.EcoPark = "Origin";
		this.reward = 500;
		this.passReward = 200;
	}
	@Override
	public int getEcoParkEvent() {
		return GameState.ORIGIN_EVENT;
	}
	
	public int getPassReward() {
		return passReward;
	}
	public int getReward() {
		return reward;
	}
	@Override
	public int getEcoParkPassEvent() {
		return GameState.ORIGIN_PASS_EVENT;
	}
}
