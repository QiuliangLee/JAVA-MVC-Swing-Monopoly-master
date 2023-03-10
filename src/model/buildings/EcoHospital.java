package model.buildings;


import context.GameState;

/**
 * 
 * 医院 角色到达此地点，可以让病患出院；角色住院完毕后，会从这里继续游戏 住院时间：1 - 4天
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
		player.showTextTip(player.getName() + text + "停留" + (days - 1) + "天.", 3);
		new Thread(this).start();
		*/
		return GameState.HOSPITAL_EVENT;
	}
}
