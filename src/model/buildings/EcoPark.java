package model.buildings;

import java.awt.Graphics;

import model.PlayerModel;

/**
 * 
 * ���ݳ�����
 * 
 * @author MOVELIGHTS
 * 
 */
public class EcoPark {
	/**
	 * 
	 * ����ӵ����
	 * 
	 */
	protected PlayerModel owner = null;

	/**
	 * ��������
	 */
	protected String ecoHospitalName;

	/**
	 * �ɹ�����
	 */
	protected boolean isEcoParkPurchasable = false;

	/**
	 * ����յصļ۸�
	 */
	protected int ecoParkPrice;
	/**
	 * ˰
	 */
	protected int ecoParkRevenue;
	/**
	 * ��ǰ���ݵȼ�
	 */
	protected int ecoParkLevel;

	/**
	 * 
	 * ����
	 * 
	 */
	protected int posX;
	protected int posY;
	/**
	 * ���ȼ�
	 */
	protected int maxLevel;

	
	public EcoPark(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public boolean isEcoParkPurchasable() {
		return isEcoParkPurchasable;
	}

	public void setEcoParkPurchasable(boolean ecoParkPurchasable) {
		this.isEcoParkPurchasable = ecoParkPurchasable;
	}

	/**
	 * �Ƿ��������
	 */
	public boolean canEcoParkUpLevel() {
		return this.ecoParkLevel < maxLevel;
	}

	public PlayerModel getOwner() {
		return owner;
	}

	public void setOwner(PlayerModel owner) {
		this.owner = owner;
	}

	public int getEcoParkLevel() {
		return ecoParkLevel;
	}

	public void setEcoParkLevel(int ecoParkLevel) {
		this.ecoParkLevel = ecoParkLevel;
	}

	public String getEcoHospitalName() {
		return ecoHospitalName;
	}
	public String getUpName() {
		return ecoHospitalName;
	}

	public int getUpLevelPrice() {
		return ecoParkPrice;
	}
	/**
	 * 
	 * ��ȡ�����ܼ�ֵ
	 * 
	 */
	public int getEcoParkAllPrice() {
		return 0;
	}
	public int getEcoParkRevenue() {
		return ecoParkRevenue;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	/**
	 * �����¼�
	 */
	public int getEcoParkEvent() { return 0;}
	
	/**
	 * ·���¼�
	 */
	public int getEcoParkPassEvent() { return 0;}
	
	public void paint(Graphics g){}

	
}	
