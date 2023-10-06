package simulacija;

import java.awt.Color;
import java.awt.Graphics;

public class Disk extends Figura {
	
	public Disk(Vektor polozaj, Vektor pomeraj) {
		super(polozaj, pomeraj);
	}

	private Color boja = Color.BLUE;
	
	public Disk(Vektor polozaj, Vektor pomeraj, double r) {
		super(polozaj, pomeraj, r);
		
	}

	@Override
	public void iscrtajFiguru(Graphics g) {
		Color prethodna = g.getColor();
		g.setColor(boja);
		
		int[] xCoords = new int[8];
		int[] yCoords = new int[8];
	
		int angle = 0;
		for(int i=0 ; i<8 ;i++) {
			
			int x =  (int) (getPoluprecnikOpisane()*Math.cos(Math.toRadians(angle) )+ getPolozaj().getX());
			int y =   (int) (getPoluprecnikOpisane()*Math.sin(Math.toRadians(angle)) + getPolozaj().getY() );
			xCoords[i] = x;
			yCoords[i] = y;
			angle +=45;
			
		}
		
		g.fillPolygon(xCoords, yCoords, 8);
		
		g.setColor(prethodna);
		
	}

	@Override
	public Color getBoju() {
		return boja;
	}

	@Override
	public void setBoju(Color color) {
		boja= color;
	}

}
