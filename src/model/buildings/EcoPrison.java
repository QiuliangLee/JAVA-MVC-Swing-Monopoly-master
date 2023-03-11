package model.buildings;

import model.PlayerModel;

import context.GameState;


public class EcoPrison extends EcoPark {

	private String[] events = { "Participate in prison cultural performances,", "Plant trees in the prison,", "Participate in prison environmental activities with fellow inmates," };

	private PlayerModel player;

	public EcoPrison(int posX, int posY) {
		super(posX, posY);
		this.EcoPark = "EcoPrison";
	}

	public String[] getEvents() {
		return events;
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.PRISON_EVENT;
	}
}
