package model.buildings;

import context.GameState;

/**
 * 
 * 10 30 50 ���λ ��ɫ������λ��ʱ�򣬿��Ի����Ӧ ��� �Ľ��
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class Point extends EcoPark {

	private int point;


	public Point(int posX, int posY, int point) {
		super(posX, posY);
		this.ecoHospitalName = point + "���λ";
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
