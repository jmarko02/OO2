package simulacija;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	
	private Vektor pomeraj,polozaj;
	private double poluprecnikOpisane = 20;
	
	
	public Figura(Vektor polozaj,Vektor pomeraj,double r) {
		this.polozaj = polozaj;
		this.pomeraj = pomeraj;
		this.poluprecnikOpisane = r;
	}
	public Figura(Vektor polozaj,Vektor pomeraj) {
		this.polozaj = polozaj;
		this.pomeraj = pomeraj;
	}

	public Vektor getPomeraj() {
		return pomeraj;
	}

	public Vektor getPolozaj() {
		return polozaj;
	}

	public double getPoluprecnikOpisane() {
		return poluprecnikOpisane;
	}
	
	public boolean pomerajUOkviruOpisane(Vektor vektorPolozaja) {
		double distance = Math.sqrt(Math.pow(vektorPolozaja.getX()-polozaj.getX(),2)+Math.pow(vektorPolozaja.getY()- polozaj.getY(),2));
		return distance <= poluprecnikOpisane;
	}
	
	public boolean poklapanjeOpisanihKruznica(Figura f) {
		if(f == null) return false;
		double x = f.polozaj.getX();
		double y = f.polozaj.getY();
		double distancaCentara = Math.sqrt(Math.pow(x - polozaj.getX(),2) + Math.pow(y-polozaj.getY(),2));
		return distancaCentara <= f.poluprecnikOpisane + poluprecnikOpisane ;
		
	}
	
	
	public void setPoluprecnikOpisane(double poluprecnikOpisane) {
		this.poluprecnikOpisane = poluprecnikOpisane;
	}
	
	public abstract void setBoju(Color color);
	public abstract Color getBoju();
	
	public abstract void iscrtajFiguru(Graphics g); //ova metoda nema veze sa paint iz awt jer ova klasa nije izvedena iz odg klasa
	
}
