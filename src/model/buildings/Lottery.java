package model.buildings;

import model.PlayerModel;
import context.GameState;

/**
 * 
 * 乐透
 * 角色到达这里时，可以进行下注游戏，可能输赢奖金.
 * 
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class Lottery extends EcoPark {

	private PlayerModel player;
	
	public Lottery(int posX, int posY) {
		super(posX, posY);
		this.ecoHospitalName = "乐透";
	}
	
	@Override
	public int getEcoParkEvent() {
		return GameState.LOTTERY_EVENT;
	}
}
