package model.buildings;

import java.awt.Graphics;

import model.PlayerModel;

/**
 * 
 * 房屋抽象类
 * 
 * @author MOVELIGHTS
 * 
 */
public class EcoPark {
	/**
	 * 
	 * 房屋拥有者
	 * 
	 */
	protected PlayerModel owner = null;

	/**
	 * 房屋名称
	 */
	protected String ecoHospitalName;

	/**
	 * 可购买性
	 */
	protected boolean isEcoParkPurchasable = false;

	/**
	 * 购买空地的价格
	 */
	protected int ecoParkPrice;
	/**
	 * 税
	 */
	protected int ecoParkRevenue;
	/**
	 * 当前房屋等级
	 */
	protected int ecoParkLevel;

	/**
	 * 
	 * 坐标
	 * 
	 */
	protected int posX;
	protected int posY;
	/**
	 * 最大等级
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
	 * 是否可以升级
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
	 * 获取房屋总价值
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
	 * 触发事件
	 */
	public int getEcoParkEvent() { return 0;}
	
	/**
	 * 路过事件
	 */
	public int getEcoParkPassEvent() { return 0;}
	
	public void paint(Graphics g){}

	
}	
