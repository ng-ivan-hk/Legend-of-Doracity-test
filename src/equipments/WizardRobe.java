
public class PriorityClaw extends Equipment {

	/**
	 * Card Number: 20
	 */
	public PriorityClaw() {
		super(20, false, false, true, true, true, true);
	}

	@Override
	public void equipmentEffect(Character c) {

		c.changeAttack(1, Character.FOR_EQUIPMENT);
		c.changeDefM(2, Character.FOR_EQUIPMENT);
	}

	@Override
	protected void removeEquipmentEffect(Character c) {
		//completed
	

	}

}
