package simulacija;

import java.awt.Color;
import java.awt.Graphics;

public class Zvezda extends Figura {

	private static Color[] boje = new Color[] {Color.RED, Color.PINK, Color.GREEN};
	private Color boja;
	
	private int brojSudara = 0;
	
	public void setBrojSudara(int broj) {
		brojSudara = broj;
		boja = boje[brojSudara%boje.length];
	}
	public int getBrojSudara() {
		return brojSudara;
	}
	public Zvezda(Vektor polozaj, Vektor pomeraj) {
		super(polozaj, pomeraj, 15);
		brojSudara = 0;
		boja = boje[brojSudara%boje.length];
	}

	@Override
	public void setBoju(Color color) {
		boja = color;
	}

	@Override
	public Color getBoju() {
		return boja;
	}

	@Override
	public void iscrtajFiguru(Graphics g) {
		
		Color prethodna = g.getColor();
		g.setColor(boja);
		
		int[] xPoints = new int[10];
		int[] yPoints = new int[10];
		double angle = Math.toRadians(36);
		for(int i=0; i< 5 ; i++) {
			double x = getPolozaj().getX() + getPoluprecnikOpisane()*Math.cos(angle*i*2);
			double y = getPolozaj().getY() + getPoluprecnikOpisane()*Math.sin(angle*2*i);
			
			xPoints[2*i] = (int)x;
			yPoints[2*i] = (int)y;
			
			double x1 = getPolozaj().getX() + getPoluprecnikOpisane()/2 * Math.cos(angle*(i*2+1));
			double y1 = getPolozaj().getY() + getPoluprecnikOpisane()/2 * Math.sin(angle*(i*2+1));
		
			xPoints[2*i+1] = (int)x1;
			yPoints[2*i+1] = (int)y1;
		}
		g.fillPolygon(xPoints,yPoints,10);
		g.setColor(prethodna);
		
	}

}
