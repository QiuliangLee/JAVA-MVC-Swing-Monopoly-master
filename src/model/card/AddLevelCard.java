package model.card;

import context.GameState;

import model.PlayerModel;

/**
 * 
 * �Ӹǿ�,��ǰ���ݼӸ�һ��
 * OK
 * 
 */
public class AddLevelCard extends Card {

	public AddLevelCard(PlayerModel owner) {
		super(owner);
		this.name = "AddLevelCard";
		this.cName = "�Ӹǿ�";
		this.price = 30;

	}

	@Override
	public int useCard() {
		return GameState.CARD_ADDLEVEL;
	}

}
