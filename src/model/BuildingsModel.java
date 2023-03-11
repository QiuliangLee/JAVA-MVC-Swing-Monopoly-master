package model;

import java.util.ArrayList;
import java.util.List;

import model.buildings.EcoPark;
import model.buildings.EcoHospital;
import model.buildings.EcoHouse;
import model.buildings.EcoLottery;
import model.buildings.News;
import model.buildings.Origin;
import model.buildings.ForestPark;
import model.buildings.Point;
import model.buildings.EcoPrison;
import model.buildings.EcoShop_;


public class BuildingsModel extends Tick implements Port{
	
	private List<EcoPark> ecoParks = null;
	
	private LandModel land = null;

	
	public BuildingsModel (LandModel land){
		this.land = land;
	}


	
	private void initBuilding() {

		ecoParks = new ArrayList<EcoPark>();

		int[][] temp = this.land.getLand();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				switch (temp[i][j]) {
				case LandModel.SPACE:
					EcoPark tempBuidling = new EcoHouse(i, j);

					tempBuidling.setEcoParkPurchasable(true);
					ecoParks.add(tempBuidling);
					break;
				case LandModel.HOSPITAL:
					ecoParks.add(new EcoHospital(i, j));

					LandModel.hospital = new java.awt.Point(j * 60,i * 60);

					break;
				case LandModel.LOTTERY:
					ecoParks.add(new EcoLottery(i, j));
					break;
				case LandModel.NEWS:
					ecoParks.add(new News(i, j));
					break;
				case LandModel.ORIGIN:
					ecoParks.add(new Origin(i, j));
					break;
				case LandModel.PARK:
					ecoParks.add(new ForestPark(i, j));
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
					ecoParks.add(new EcoShop_(i, j));
					break;
				case LandModel.PRISON:
					ecoParks.add(new EcoPrison(i, j));

					LandModel.prison = new java.awt.Point(j * 60, i * 60);

					break;
				default:
					break;
				}
			}
		}
	}

	
	public List<EcoPark> getBuilding(){
		return ecoParks;
	}
	
	public EcoPark getBuilding(int x, int y){
		for (EcoPark temp : this.ecoParks){
			if (temp.getPosX() == x && temp.getPosY() == y){
				return temp;
			}
		}
		return null;
	}
	
	public void startGameInit (){

		initBuilding();
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;
		
	}
}