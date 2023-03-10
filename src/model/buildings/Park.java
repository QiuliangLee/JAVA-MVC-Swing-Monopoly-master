package model.buildings;

import java.awt.Image;

import javax.swing.ImageIcon;

import context.GameState;

/**
 * 
 * ��԰ ���������Ĺ�԰����ɫ������ʲô����Ҳ���ᷢ��
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class Park extends EcoPark {
	/*
	 * 
	 * �¼�ͼƬ
	 */
	private Image[] imgageEvents = { EVENT_PARK_1, EVENT_PARK_2, EVENT_PARK_3,
			EVENT_PARK_4 };
	/**
	 * �¼�ͼƬ
	 */
	public static Image EVENT_PARK_1 = new ImageIcon("images/event/park01.jpg")
			.getImage();
	/**
	 * �¼�ͼƬ
	 */
	public static Image EVENT_PARK_2 = new ImageIcon("images/event/park02.jpg")
			.getImage();
	/**
	 * �¼�ͼƬ
	 */
	public static Image EVENT_PARK_3 = new ImageIcon("images/event/park03.jpg")
			.getImage();
	/**
	 * �¼�ͼƬ
	 */
	public static Image EVENT_PARK_4 = new ImageIcon("images/event/park04.jpg")
			.getImage();


	public Park(int posX, int posY) {
		super(posX, posY);
		this.ecoHospitalName = "��԰";
	}
	
	public Image[] getImgageEvents() {
		return imgageEvents;
	}

	@Override
	public int getEcoParkEvent() {
		return GameState.PARK_EVENT;
	}

}
