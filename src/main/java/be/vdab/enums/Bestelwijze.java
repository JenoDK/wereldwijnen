package be.vdab.enums;

public enum Bestelwijze {
	AFHALEN(0), OPSTUREN(1);

	private int bestelWijzeInt;

	private Bestelwijze(int bestelWijzeInt) {
		this.bestelWijzeInt = bestelWijzeInt;
	}

	public int getBestelWijzeInt() {
		return bestelWijzeInt;
	}

}
