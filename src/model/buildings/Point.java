package model.buildings;

import context.GameState;

/**
 * 
 * 10 30 50 点卷位 角色到达点卷位的时候，可以获得响应 点卷 的金额
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class Point extends EcoPark {

	private int point;


	public Point(int posX, int posY, int point) {
		super(posX, posY);
		this.ecoHospitalName = point + "点卷位";
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.POINT_EVENT;
	}
}
