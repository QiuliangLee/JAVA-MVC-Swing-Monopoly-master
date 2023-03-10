package model.card;

import context.GameState;

import model.PlayerModel;

/**
 * 
 * ������,��ǰ���ݽ���һ�������֣�
 * OK
 *
 */
public class ReduceLevelCard extends Card{

	public ReduceLevelCard(PlayerModel owner) {
		super(owner);
		this.name = "ReduceLevelCard";
		this.cName = "������";
		this.price = 30;
	}

	@Override
	public int useCard() {
		return GameState.CARD_REDUCELEVEL;
		}

}
