package model.buildings;

import java.awt.Image;

import javax.swing.ImageIcon;

import context.GameState;


public class ForestPark extends EcoPark {
	
	private Image[] imgageEvents = { EVENT_PARK_1, EVENT_PARK_2, EVENT_PARK_3,
			EVENT_PARK_4 };
	
	public static Image EVENT_PARK_1 = new ImageIcon("images/event/park01.jpg")
			.getImage();
	
	public static Image EVENT_PARK_2 = new ImageIcon("images/event/park02.jpg")
			.getImage();
	
	public static Image EVENT_PARK_3 = new ImageIcon("images/event/park03.jpg")
			.getImage();
	
	public static Image EVENT_PARK_4 = new ImageIcon("images/event/park04.jpg")
			.getImage();


	public ForestPark(int posX, int posY) {
		super(posX, posY);
		this.EcoPark = "ForestPark";
	}
	
	public Image[] getImgageEvents() {
		return imgageEvents;
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.PARK_EVENT;
	}

}
