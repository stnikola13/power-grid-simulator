package energija;

import java.awt.Color;

@SuppressWarnings("serial")
public class Fabrika extends Potrosac {

	public Fabrika(Baterija battery, Plac owner) {
		super('F', Color.GRAY, 1300, owner, battery);
	}

	@Override
	public int consumeEnergy() {
		return (int)(Math.random() * 5);
	}

}
