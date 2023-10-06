package EnergetskiSistem;

import java.awt.Color;
import java.util.Optional;

public abstract class Proizvodjac extends Parcela implements Runnable{

	private int vreme;
	private Baterija baterija;
	
	private Thread proizvodjac;
	
	public Proizvodjac(char oznaka, Color boja,int vreme, Baterija baterija) {
		super(oznaka, boja);
		this.baterija = baterija;
		this.vreme = vreme;
		proizvodjac = new Thread(this);
		proizvodjac.start();
	}

	@Override
	public void run() {
		Color prethodna = getForeground();
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(vreme + (int)(Math.random()*300));
				
				//proizvodnja:
				int proizvedenaEnergija = 0;
				if(uspesnaProizvodnjaEnergije()) {
					proizvedenaEnergija = proizvodnjaEnergije();
					synchronized(baterija) {
						
						baterija.dodajEnergiju(proizvedenaEnergija);
					}
					setForeground(Color.RED);
					
				} 
				
				
				
				
				Thread.sleep(300);
				setForeground(prethodna);
			}
		}catch(InterruptedException e) {}
		
		synchronized(this) {
			proizvodjac = null;
			notify();
		}
	}
	
	protected abstract  int proizvodnjaEnergije();
	
	protected abstract boolean uspesnaProizvodnjaEnergije();

	public synchronized void zaustavi() {
		
		if (proizvodjac != null) proizvodjac.interrupt();
		while(proizvodjac != null) {
			try {
				wait();
			}catch(InterruptedException e) {}
		}
	}
}
