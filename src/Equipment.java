/**
 * This represents an Equipment Card.
 * 
 * @author Ivan Ng
 * 
 */
abstract public class Equipment extends Card {

	private boolean saberOK;
	private boolean archerOK;
	private boolean casterOK;
	private boolean supportOK;
	private boolean doracityOK;
	private boolean academyOK;
	private boolean weapon; // if false, armor

	/**
	 * @param number
	 *            Equipment Number (e.g. E-001 -> pass 1)
	 * @param saberOK
	 *            Can Saber equip this?
	 * @param archerOK
	 *            Can Archer equip this?
	 * @param casterOK
	 *            Can Caster equip this?
	 * @param supportOK
	 *            Can Support equip this?
	 * @param doracityOK
	 *            Can a Doracity character equip this?
	 * @param academyOK
	 *            Can an Academy character equip this?
	 * @param weapon
	 *            Is this a weapon? If false, that means this is an armor.
	 */
	public Equipment(int number, boolean saberOK, boolean archerOK, boolean casterOK,
			boolean supportOK, boolean doracityOK, boolean academyOK, boolean weapon) {
		super(Lang.EquipmentTypes[number], number);
		this.saberOK = saberOK;
		this.archerOK = archerOK;
		this.casterOK = casterOK;
		this.supportOK = supportOK;
		this.doracityOK = doracityOK;
		this.academyOK = academyOK;
		this.weapon = weapon;
	}

	final public String getInfo() {

		String info = " " + (weapon ? Lang.weapon : Lang.armor) + "<br>" + Lang.availableJob
				+ "<font color=blue>";
		if (saberOK) {
			info += Lang.JobNames[Character.SABER] + Lang.comma2;
		}
		if (archerOK) {
			info += Lang.JobNames[Character.ARCHER] + Lang.comma2;
		}
		if (casterOK) {
			info += Lang.JobNames[Character.CASTER] + Lang.comma2;
		}
		if (supportOK) {
			info += Lang.JobNames[Character.SUPPORT] + Lang.comma2;
		}

		// Remove the last comma2
		if (info.length() > 0) {
			info = info.substring(0, info.length() - 1);
		}

		info += "</font><br><br>" + Lang.EquipmentInfos[number];

		return super.getInfo() + info;
	}

	final public boolean isWeapon() {
		return weapon;
	}

	/**
	 * Check if the Character can use the card (matches this Equipment's
	 * requirement).
	 * 
	 * @param c
	 *            the Character who uses the card (to be equipped)
	 * @return 0 if success<br>
	 *         1 if wrong job<br>
	 *         10 if not Academy<br>
	 *         11 if not Doracity
	 */
	final public int check(Character c) {

		if ((saberOK && c.getJob() == Character.SABER)
				|| (archerOK && c.getJob() == Character.ARCHER)
				|| (casterOK && c.getJob() == Character.CASTER)
				|| (supportOK && c.getJob() == Character.SUPPORT) || (c.getJob() == Character.NA)) {

			if (!doracityOK && c.isDoracity()) { // Not Academy
				return 10;
			} else if (!academyOK && !c.isDoracity()) { // Not Doracity
				return 11;
			} else {
				return 0;
			}

		} else { // Wrong Job
			return 1;
		}
	}

	/**
	 * Applies the Equipment's effect.Should be only called by
	 * {@link Character#setEquipment(Equipment)}.
	 * 
	 * @param c
	 */
	final public void useEquipment(Character c) {
		// Really equip!!!
		equipmentEffect(c);
	}

	/**
	 * Actions to be performed when equipping this Equipment. Should be only
	 * called by {@link Equipment#useEquipment(Character)}.
	 * 
	 * @param c
	 *            <code>Character</code> who equips this Equipment
	 */
	abstract protected void equipmentEffect(Character c);

	/**
	 * Actions to be performed when removing this Equipment. Should be only
	 * called by {@link Character#setEquipment(Equipment)}.
	 * 
	 * @param c
	 *            <code>Character</code> who equips this Equipment
	 */
	abstract protected void removeEquipmentEffect(Character c);

}
