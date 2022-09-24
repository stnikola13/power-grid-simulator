package energija;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class EnergetskiSistem extends Frame {
	private Plac area;
	private Baterija battery;
	private Button addProducerButton;
	private Choice choice;
	private CheckboxGroup chboxgrp;
	private Label batteryState;
	
	public EnergetskiSistem(int rows, int columns, int batteryCapacity) {
		super("Energetski sistem");
		this.area = new Plac(rows, columns);
		this.battery = new Baterija(batteryCapacity);
		this.addProducerButton = new Button("Dodaj");
		
		//battery.discharge(); // OBRISATI OVO
		
		setBounds(1100, 550, 500, 500);
		setResizable(false);
		populateWindow();
		setVisible(true);
	}
	
	private void populateWindow() {	
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) { 
		    	area.stopProducers(); 
		    	dispose();
		    }
		});
		
		addProducerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ch = chboxgrp.getSelectedCheckbox().getSelectedObjects()[0].toString();
				if (ch == "Proizvodjac") {
					String c = choice.getSelectedItem();
					if (c == "Hidroelektrana") area.addProducer(new Hidroelektrana(battery, area));	
					else if (c == "Termoelektrana") area.addProducer(new Termoelektrana(battery, area));	
				}
				else if (ch == "Potrosac") {
					String c = choice.getSelectedItem();
					if (c == "Fabrika") area.addConsumer(new Fabrika(battery, area));
					else if (c == "TrafoStanica") area.addConsumer(new TrafoStanica(battery, area));
				}
				batteryState = battery.getLabel();
				batteryState.revalidate();
			}
		});
		
		chboxgrp = new CheckboxGroup();
		Checkbox c1 = new Checkbox("Proizvodjac", true, chboxgrp);
		Checkbox c2 = new Checkbox("Potrosac", false, chboxgrp);
		
		choice = new Choice();
		choice.add("Hidroelektrana");
		choice.add("Termoelektrana");
		choice.select(0);		
		
		c1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				choice.remove(0);
				choice.remove(0);
				choice.add("Hidroelektrana");
				choice.add("Termoelektrana");
				choice.select(0);
			}		
		});
		
		c2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				choice.remove(0);
				choice.remove(0);
				choice.add("Fabrika");
				choice.add("TrafoStanica");
				choice.select(0);
			}		
		});
	
		this.setLayout(new BorderLayout());
		Panel top = new Panel();
		Panel top1 = new Panel(), top2 = new Panel(), top3 = new Panel(), top4 = new Panel();
		top1.add(c1);
		top1.add(c2);
		top2.add(choice);
		top3.add(addProducerButton);
		
		batteryState = battery.getLabel();
		top4.add(batteryState);
		
		choice.setVisible(true);
		
		top.add(top1); top.add(top2); top.add(top3); top.add(top4);
		
		
		this.add(top, BorderLayout.NORTH);
		this.add(area, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		new EnergetskiSistem(5, 5, 50);
	}

}
