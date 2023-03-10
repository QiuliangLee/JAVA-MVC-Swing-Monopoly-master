package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import model.buildings.EcoPark;


/**
 * ����ˢ�²�
 * 
 * @author MOVELIGHTS
 * 
 */
public class Buildings extends Layer {

	/**
	 * 
	 * ���ط�����Ϣ
	 * 
	 */
	private model.BuildingsModel informationBuilding;
	/**
	 * 
	 * ��������
	 * 
	 */
	private List<EcoPark> ecoPark;
	/**
	 * house ͼƬ 1-5����
	 * 
	 */
	private  Image HOUSE_01 = new ImageIcon("images/building/house01.png").getImage();
	/**
	 * house ͼƬ 1-5����
	 * 
	 */
	private  Image HOUSE_02 = new ImageIcon("images/building/house02.png").getImage();
	/**
	 * α͸��ͼƬ
	 * 
	 */
	public  Image TRANSPARENT = new ImageIcon("images/window/transparent.png").getImage();
	
	protected Buildings(int x, int y, int w, int h,
			model.BuildingsModel informationBuilding) {
		super(x, y, w, h);
		this.informationBuilding = informationBuilding;
	}

	public void paint(Graphics g) {
		// ���ƽ�����
		paintBuildings(g);
	}
	
	/**
	 * 
	 * ���ƽ�����
	 * 
	 */
	private void paintBuildings(Graphics g) {
		for(EcoPark temp : this.ecoPark){
			// ���ݻ���
			paintBuilding(temp,g);
		}
	}
	private void paintBuilding(EcoPark ecoPark, Graphics g) {
		int x = 0;
		int y = 0;
		if (ecoPark.getOwner() != null) {
			int level = ecoPark.getEcoParkLevel();
			int i = ecoPark.getPosX();
			int j = ecoPark.getPosY();
			Image temp = ecoPark.getOwner().getNumber() == 1 ? this.HOUSE_01
					: this.HOUSE_02;
			if (level > 0) {
				g.drawImage(temp, x + j * 60,
						y + i * 60 - (temp.getHeight(null) - 60), x + (j + 1)
								* 60, y + (i + 1) * 60, 60 * (level - 1), 0,
						60 * level, temp.getHeight(null), null);
			}
			// ͸�����ǰ���
			g.drawImage(this.TRANSPARENT, x + j * 60, y + i * 60, x + (j + 1)
					* 60, y + (i + 1) * 60, 0, 0, 60, 60, null);
			// ����ӵ����������ʾ
			g.drawString("" + ecoPark.getOwner().getName(), x + j * 60 + 4, y + i
					* 60 + 14);
		}
	}


	@Override
	public void startPanel() {
		this.ecoPark = this.informationBuilding.getBuilding();
	}

}
