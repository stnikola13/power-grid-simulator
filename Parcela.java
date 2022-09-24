package energija;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Parcela extends Label {
	protected char label;
	protected Color color;
	protected Plac owner;
	
	public Parcela(char character, Color color, Plac owner) {
		this.label = character;
		this.color = color;
		this.owner = owner;
		
		this.setBackground(color);
		this.setText("" + label);
		this.setAlignment(Label.CENTER);
		this.setFont(new Font("Serif", Font.BOLD, 14));
		this.setForeground(Color.WHITE);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				owner.changeCur(Parcela.this);
			}
		});
	}
	
	public void changeBackgroundColor(Color color) {
		this.color = color;
		this.setBackground(color);
	}

}
