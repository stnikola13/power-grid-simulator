package energija;

import java.awt.Color;

@SuppressWarnings("serial")
public class Hidroelektrana extends Proizvodjac {
	private int bodiesOfWater;

	public Hidroelektrana(Baterija battery, Plac owner) {
		super('H', Color.BLUE, 1500, owner, battery);
		bodiesOfWater = 0;
	}

	public void setSurruondingNumber(int num) {
		if (num > 0) bodiesOfWater = num;
	}

	@Override
	public int generateEnergy() {
		if (bodiesOfWater >= 1) return bodiesOfWater;
		else return 0;
	}
}
