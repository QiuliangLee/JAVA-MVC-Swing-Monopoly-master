package model.buildings;

import model.PlayerModel;
import context.GameState;

/**
 * 
 * ��� ���ͨ��ʱ������Ǯ
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class Origin extends EcoPark {
	/**
	 * ͨ��ʱ�����Ľ�Ǯ
	 */
	private int passReward;
	/**
	 * ͣ��ʱ������Ǯ
	 */
	private int reward;

	private PlayerModel player;

	public Origin(int posX, int posY) {
		super(posX, posY);
		this.ecoHospitalName = "���";
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
