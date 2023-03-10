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
 * ȫ�ַ�����Ϣ
 * 
 * @author MOVELIGHTS
 * 
 */
public class BuildingsModel extends Tick implements Port{
	/**
	 * ��������
	 */
	private List<EcoPark> ecoParks = null;
	
	private LandModel land = null;

	
	public BuildingsModel (LandModel land){
		this.land = land;
	}


	/**
	 * 
	 * ��ʼ������
	 * 
	 */
	private void initBuilding() {
		// ��ʼ������
		ecoParks = new ArrayList<EcoPark>();
		// ��Ӧ��ͼ���뷿��
		int[][] temp = this.land.getLand();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				switch (temp[i][j]) {
				case LandModel.SPACE:
					EcoPark tempBuidling = new EcoHouse(i, j);
					// ���ÿյص�����Ϊ���Թ����
					tempBuidling.setEcoParkPurchasable(true);
					ecoParks.add(tempBuidling);
					break;
				case LandModel.HOSPITAL:// ҽԺ
					ecoParks.add(new EcoHospital(i, j));
					//����ȫͼҽԺ��
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
				case LandModel.PRISON:// ����
					ecoParks.add(new Prison(i, j));
					//����ȫͼ������
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
	 * ��÷��ݱ�
	 * 
	 * @return
	 */
	public List<EcoPark> getBuilding(){
		return ecoParks;
	}
	/**
	 * 
	 * ��õ�ǰλ�÷���
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
	 * ��ʼ��Ϸ����
	 * 
	 */
	public void startGameInit (){
		// ��ʼ������
		initBuilding();
	}

	@Override
	public void updata(long tick) {
		this.nowTick = tick;
		
	}
}