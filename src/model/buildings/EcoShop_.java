package model.buildings;

import java.util.ArrayList;
import java.util.List;

import context.GameState;
import control.GameRunning;


import model.card.*;


public class EcoShop_ extends EcoPark {

	
	public static int MAXITEMSIZE = 9;
	
	private List<EcoCard> ecoCards = new ArrayList<EcoCard>(MAXITEMSIZE);

	private GameRunning running = null;

	private EcoShop_ shopUI;

	public EcoShop_(int posX, int posY) {
		super(posX, posY);
		this.EcoPark = "EcoShop";
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.SHOP_EVENT;
	}

	
	public void createCards() {

		this.ecoCards = new ArrayList<EcoCard>(MAXITEMSIZE);

		for (int i = 0; i < MAXITEMSIZE; i++) {
			int random = (int) (Math.random() * 12);
			switch (random) {
			case 0:
				ecoCards.add(new EcoRobEcoCard(null));
				break;
			case 1:
				ecoCards.add(new AveragerPoorEcoCard(null));
				break;
			case 2:
				ecoCards.add(new EcoChangeEcoCard(null));
				break;
			case 3:
				ecoCards.add(new EcoControlDiceEcoCard(null));
				break;
			case 4:
				ecoCards.add(new EcoCrossingEcoCard(null));
				break;
			case 5:
				ecoCards.add(new EcoHaveEcoCard(null));
				break;
			case 6:
				ecoCards.add(new EcoReduceLevelEcoCard(null));
				break;
			case 7:
				ecoCards.add(new EcoRobEcoCard(null));
				break;
			case 8:
				ecoCards.add(new EcoStopEcoCard(null));
				break;
			case 9:
				ecoCards.add(new EcoTallageEcoCard(null));
				break;
			case 10:
				ecoCards.add(new EcoTortoiseEcoCard(null));
				break;
			case 11:
				ecoCards.add(new EcoTrapEcoCard(null));
				break;
			}
		}
	}
	public List<EcoCard> getCards() {
		return ecoCards;
	}
}
