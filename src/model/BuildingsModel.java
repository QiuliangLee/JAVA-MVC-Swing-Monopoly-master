package model;

import java.util.ArrayList;
import java.util.List;

import model.buildings.EcoPark;
import model.buildings.EcoHospital;
import model.buildings.EcoHouse;
import model.buildings.Lottery;
import model.buildings.News;
import model.buildings.Origin;
import model.buildings.Park;
import model.buildings.Point;
import model.buildings.Prison;
import model.buildings.Shop_;

/**
 * 全局房屋信息
 * 
 * @author MOVELIGHTS
 * 
 */
public class BuildingsModel extends Tick implements Port{
	/**
	 * 房屋链表
	 */
	private List<EcoPark> ecoParks = null;
	
	private LandModel land = null;

	
	public BuildingsModel (LandModel land){
		this.land = land;
	}


	/**
	 * 
	 * 初始化房屋
	 * 
	 */
	private void initBuilding() {
		// 初始化链表
		ecoParks = new ArrayList<EcoPark>();
		// 对应地图加入房屋
		int[][] temp = this.land.getLand();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				switch (temp[i][j]) {
				case LandModel.SPACE:
					EcoPark tempBuidling = new EcoHouse(i, j);
					// 设置空地的属性为可以购买的
					tempBuidling.setEcoParkPurchasable(true);
					ecoParks.add(tempBuidling);
					break;
				case LandModel.HOSPITAL:// 医院
					ecoParks.add(new EcoHospital(i, j));
					//设置全图医院点
					LandModel.hospital = new java.awt.Point(j * 60,i * 60);
//					System.out.println(LandModel.hospital );
					break;
				case LandModel.LOTTERY:
					ecoParks.add(new Lottery(i, j));
					break;
				case LandModel.NEWS:
					ecoParks.add(new News(i, j));
					break;
				case LandModel.ORIGIN:
					ecoParks.add(new Origin(i, j));
					break;
				case LandModel.PARK:
					ecoParks.add(new Park(i, j));
					break;
				case LandModel.PIONT_10:
					ecoParks.add(new Point(i, j, 10));
					break;
				case LandModel.PIONT_30:
					ecoParks.add(new Point(i, j, 30));
					break;
				case LandModel.PIONT_50:
					ecoParks.add(new Point(i, j, 50));
					break;
				case LandModel.SHOP:
					ecoParks.add(new Shop_(i, j));
					break;
				case LandModel.PRISON:// 监狱
					ecoParks.add(new Prison(i, j));
					//设置全图监狱点
					LandModel.prison = new java.awt.Point(j * 60, i * 60);
//					System.out.println(LandModel.prison );
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * 
	 * 获得房屋表
	 * 
	 * @return
	 */
	public List<EcoPark> getBuilding(){
		return ecoParks;
	}
	/**
	 * 
	 * 获得当前位置房屋
	 * 
	 */
	public EcoPark getBuilding(int x, int y){
		for (EcoPark temp : this.ecoParks){
			if (temp.getPosX() == x && temp.getPosY() == y){
				return temp;
			}
		}
		return null;
	}
	/**
	 * 
	 * 开始游戏设置
	 * 
	 */
	public void startGameInit (){
		// 初始化房屋
		initBuilding();
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;
		
	}
}