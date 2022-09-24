package energija;

import java.awt.Color;

@SuppressWarnings("serial")
public class Termoelektrana extends Proizvodjac {
	private int coef;

	public Termoelektrana(Baterija battery, Plac owner) {
		super('T', Color.ORANGE, 2000, owner, battery);
		coef = 2;
	}

	@Override
	public int generateEnergy() {
		return coef;
	}

}
