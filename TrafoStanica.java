package energija;

import java.awt.Color;

@SuppressWarnings("serial")
public class TrafoStanica extends Potrosac {

	public TrafoStanica (Baterija battery, Plac owner) {
		super('S', Color.YELLOW, 1000, owner, battery);
	}

	@Override
	public int consumeEnergy() {
		return (int)(Math.random() * 5);
	}
}
