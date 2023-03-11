package model.buildings;


import context.GameState;


public class EcoHospital extends EcoPark {
	
	private String[] events = {
			"Participating in environmental volunteer activities at the hospital." ,
			"Discovering ecological destruction in front of the hospital and actively calling for green travel." ,
			"Promote sustainable lifestyles in the hospital." ,
			"Visiting hospitalized friends in the hospital."
	};
	
	public EcoHospital(int posX, int posY) {
		super(posX, posY);
		this.EcoPark = "Eco hospital";
	}
	public String[] getEvents() {
		return events;
	}
	@Override
	public int getEcoParkEvent() {
		
		return GameState.HOSPITAL_EVENT;
	}
}
