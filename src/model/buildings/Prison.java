package model.buildings;

import model.PlayerModel;

import context.GameState;

/**
 * 
 * ���� ��ҵ���������������߷��������¼�
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class Prison extends EcoPark {

	private String[] events = { "ȥ�����������ѣ�", "��ԩ��������", "����������Աץȥ��ɨ������" };

	private PlayerModel player;

	public Prison(int posX, int posY) {
		super(posX, posY);
		this.ecoHospitalName = "����";
	}

	public String[] getEvents() {
		return events;
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.PRISON_EVENT;
	}
}
