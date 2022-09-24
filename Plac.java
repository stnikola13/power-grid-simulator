package energija;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

@SuppressWarnings("serial")
public class Plac extends Panel {
	private int r, c;
	private Parcela grid[][];
	private Parcela cur;
	private GridLayout layout;
	
	public Plac(int rows, int columns) {
		r = rows;
		c = columns;
		grid = new Parcela[r][c];
		cur = null;
		layout = new GridLayout(r, c, 5, 5);
		
		this.setLayout(layout);							// Druga dva argumenta predstavljaju hor. i ver. gap izmedju labela
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				double rand = Math.random();
				if (rand < 0.7) grid[i][j] = new TravnataPovrs(this);
				else grid[i][j] = new VodenaPovrs(this);
				this.add(grid[i][j]);
			}
		}
	}
	
	public void changeCur(Parcela newParcel) {
		if (cur != null) cur.setFont(new Font("Serif", Font.BOLD, 14)); // Restovanje prethodno oznacene
		cur = newParcel;
		if (cur != null) cur.setFont(new Font("Serif", Font.BOLD, 20)); 
	}
	
	public synchronized void addProducer(Proizvodjac pro) {
		if (cur == null) return;
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == cur) {
					if (grid[i][j] instanceof Proizvodjac) ((Proizvodjac)grid[i][j]).stop();
					changeCur(null);
					grid[i][j] = pro;
					grid[i][j].revalidate();
					if (pro instanceof Hidroelektrana) adjustSurroundings(i, j);
					pro.start();		
					break;
				}
			}
		}
		this.removeAll();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				this.add(grid[i][j]);
			}
		}
		this.revalidate();
	}
	
	public synchronized void addConsumer(Potrosac pot) {
		if (cur == null) return;
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == cur) {
					if (grid[i][j] instanceof Potrosac) ((Potrosac)grid[i][j]).stop();
					changeCur(null);
					grid[i][j] = pot;
					grid[i][j].revalidate();
					pot.start();		
					break;
				}
			}
		}
		this.removeAll();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				this.add(grid[i][j]);
			}
		}
		this.revalidate();
	}
	
	private void adjustSurroundings(int x, int y) {
		int count = 0;
		if (x == 0 && y == 0) { // Gornji levi ugao
			if (grid[x + 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y + 1] instanceof VodenaPovrs) count++;
		}
		else if (x == 0 && y == r - 1) { // Gornji desni ugao
			if (grid[x + 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y - 1] instanceof VodenaPovrs) count++;
		}
		else if (x == c - 1 && y == 0) { // Donji levi ugao
			if (grid[x - 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y + 1] instanceof VodenaPovrs) count++;
		}
		else if (x == c - 1 && y == r - 1) { // Donji desni ugao
			if (grid[x - 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y - 1] instanceof VodenaPovrs) count++;
		}
		else if (x == 0) { // Prvi red
			if (grid[x + 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y + 1] instanceof VodenaPovrs) count++;
			if (grid[x][y - 1] instanceof VodenaPovrs) count++;
		}
		else if (x == c - 1) { // Poslednji red
			if (grid[x - 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y + 1] instanceof VodenaPovrs) count++;
			if (grid[x][y - 1] instanceof VodenaPovrs) count++;
		}
		else if (y == 0) { // Prva kolona
			if (grid[x - 1][y] instanceof VodenaPovrs) count++;
			if (grid[x + 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y + 1] instanceof VodenaPovrs) count++;
		}
		else if (y == r - 1) { // Poslednja kolona
			if (grid[x - 1][y] instanceof VodenaPovrs) count++;
			if (grid[x + 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y - 1] instanceof VodenaPovrs) count++;
		}
		else { // Negde unutra (nije ivica)
			if (grid[x - 1][y] instanceof VodenaPovrs) count++;
			if (grid[x + 1][y] instanceof VodenaPovrs) count++;
			if (grid[x][y + 1] instanceof VodenaPovrs) count++;
			if (grid[x][y - 1] instanceof VodenaPovrs) count++;
		}
		Hidroelektrana h = (Hidroelektrana)grid[x][y];
		h.setSurruondingNumber(count);
	}
	
	public void stopProducers() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] instanceof Proizvodjac) {
					((Proizvodjac)grid[i][j]).stop();
				}
				else if (grid[i][j] instanceof Potrosac) {
					((Potrosac)grid[i][j]).stop();
				}
			}
		}
	}
	
}
