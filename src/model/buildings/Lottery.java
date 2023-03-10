package model.buildings;

import model.PlayerModel;
import context.GameState;

/**
 * 
 * ��͸
 * ��ɫ��������ʱ�����Խ�����ע��Ϸ��������Ӯ����.
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
		this.ecoHospitalName = "��͸";
	}
	
	@Override
	public int getEcoParkEvent() {
		return GameState.LOTTERY_EVENT;
	}
}
