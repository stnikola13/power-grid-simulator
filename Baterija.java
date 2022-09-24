package energija;

import java.awt.Label;

public class Baterija {
	private int curEnergy;
	private int maxEnergy;
	private Label state;
	
	public Baterija(int capacity) {
		maxEnergy = capacity;
		curEnergy = capacity;
		state = new Label("Baterija: " + curEnergy + "/" + maxEnergy);
	}
	
	public synchronized void addEnergy(int energy) {
		curEnergy += energy;
		if (curEnergy > maxEnergy) curEnergy = maxEnergy;
	}
	
	public void discharge() {
		curEnergy = 0;
	}
	
	public boolean isFullyCharged() {
		if (curEnergy == maxEnergy) return true;
		else return false;
	}
	
	public String printEnergy() { // MOJA METODA NEKA (OBRISATI)
		return curEnergy + " / " + maxEnergy;
	}
	
	public void updateBatteryLabel() {
		state.setText("Baterija: " + printEnergy());
	}
	
	public Label getLabel() {
		return state;
	}

}
