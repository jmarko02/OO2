package EnergetskiSistem;

import java.awt.Label;
import java.util.ArrayList;

public class Baterija  {
	private int trenutnaEnergija;
	private int kapacitet;
	
	private Label labela = new Label();
	
	ArrayList<Potrosac> potrosaci = new ArrayList();
	
	public Baterija(int kap) {
		this.kapacitet = kap;
		this.trenutnaEnergija = kap;
	}
	
	public void dodajEnergiju(int dodatna) {
		trenutnaEnergija += dodatna;
		if(trenutnaEnergija > kapacitet) trenutnaEnergija = kapacitet;
		System.out.println(trenutnaEnergija);
		if(trenutnaEnergija == kapacitet) {
			synchronized(this) {
				notify();
			}
		}
		labela.setText("Trenutno stanje baterije je: "+trenutnaEnergija);
	}
	public void isprazniBateriju() {
		trenutnaEnergija = 0;
		labela.setText("Trenutno stanje baterije je: "+trenutnaEnergija);
		
	}
	public boolean puna() {
		return trenutnaEnergija == kapacitet;
	}
	public void dodajPotrosaca(Potrosac p) {
		potrosaci.add(p);
	}
	public Label getLabel() {
		return labela;
	}
	
}
