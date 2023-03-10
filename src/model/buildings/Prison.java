package model.buildings;

import model.PlayerModel;

import context.GameState;

/**
 * 
 * 监狱 玩家到这里可以入狱或者发生其他事件
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class Prison extends EcoPark {

	private String[] events = { "去监狱看望好友，", "被冤枉入狱，", "被监狱管理员抓去打扫卫生，" };

	private PlayerModel player;

	public Prison(int posX, int posY) {
		super(posX, posY);
		this.ecoHospitalName = "监狱";
	}

	public String[] getEvents() {
		return events;
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.PRISON_EVENT;
	}
}
