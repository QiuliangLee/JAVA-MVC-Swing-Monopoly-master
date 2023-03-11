package model.buildings;

import context.GameState;


public class Point extends EcoPark {

	private int point;


	public Point(int posX, int posY, int point) {
		super(posX, posY);
		this.EcoPark = point + "Ep";
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
