package model.buildings;


import context.GameState;

/**
 * 
 * ҽԺ ��ɫ����˵ص㣬�����ò�����Ժ����ɫסԺ��Ϻ󣬻�����������Ϸ סԺʱ�䣺1 - 4��
 * 
 * 
 * @author MOVELIGHTS
 * 
 */
public class EcoHospital extends EcoPark {
	
	private String[] events = {
			"Participating in environmental volunteer activities at the hospital." ,
			"Discovering ecological destruction in front of the hospital and actively calling for green travel." ,
			"Promote sustainable lifestyles in the hospital." ,
			"Visiting hospitalized friends in the hospital."
	};
	
	public EcoHospital(int posX, int posY) {
		super(posX, posY);
		this.ecoHospitalName = "Eco hospital";
	}
	public String[] getEvents() {
		return events;
	}
	@Override
	public int getEcoParkEvent() {
		/*
		this.player = player;
		int days = (int) (Math.random() * 4) + 2;
		player.setInHospital(days);
		int random = (int) (Math.random() * events.length);
		String text = events[random];
		player.showTextTip(player.getName() + text + "ͣ��" + (days - 1) + "��.", 3);
		new Thread(this).start();
		*/
		return GameState.HOSPITAL_EVENT;
	}
}
