package energija;

import java.awt.Color;

@SuppressWarnings("serial")
public abstract class Proizvodjac extends Parcela implements Runnable {
	protected int baseTime;
	protected Baterija battery;
	protected Thread thread;

	public Proizvodjac(char character, Color color, int time, Plac owner, Baterija battery) {
		super(character, color, owner);
		this.baseTime = time;
		this.battery = battery;
	}

	@Override
	public void run() {
		try {
			while (!thread.isInterrupted()) {
				Thread.sleep(baseTime + (int)(Math.random() * 300));
				int energy = generateEnergy();
				if (energy > 0) {
					battery.addEnergy(energy);	
					this.setForeground(Color.RED);					
					Thread.sleep(300);					
					this.setForeground(Color.WHITE);					
					battery.updateBatteryLabel();
				}
				else {
					Thread.sleep(300);
					battery.updateBatteryLabel();
				}
			}
		}
		catch (InterruptedException e) {}
	}
	
	protected abstract int generateEnergy();
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		if (thread != null) thread.interrupt();
	}
}
