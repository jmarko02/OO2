package EnergetskiSistem;

import java.awt.Color;

public abstract class   Potrosac extends Parcela implements Runnable {

	private Baterija baterija;
	private Thread thread;
	protected int brojTravnatih;
	
	public Potrosac(char oznaka, Color boja,Baterija baterija) {
		super(oznaka, boja);
		this.baterija = baterija;
		thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		Color prethodna = getForeground();

		System.out.println("CEKAAAM");
		try {
			while(!Thread.interrupted()) {
				while(baterija.puna() == false) {
					
					synchronized(baterija) {
						baterija.wait();
					}
				}

				baterija.isprazniBateriju();
				setForeground(Color.MAGENTA);
				cekaj();
				setForeground(prethodna);
			}
			
		}catch(InterruptedException e) {}
		
	}
	public abstract void cekaj();
	
	public void finish() {
		if(thread == null) return;
		thread.interrupt();
		try {
			thread.join();
			thread = null;	
		}catch(InterruptedException e) {}
	}
	public synchronized void postaviBrojTravnatih(int broj) {
		this.brojTravnatih = broj;
	}
	

}
