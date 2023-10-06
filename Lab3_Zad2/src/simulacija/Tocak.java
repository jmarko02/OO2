package simulacija;

import java.awt.Color;
import java.awt.Graphics;

public class Tocak extends Figura {
	
	private Color boja  = Color.YELLOW ;
	
	private static int poslId = 0;
	private int id = ++poslId;

	public Tocak(Vektor polozaj, Vektor pomeraj, double r) {
		super(polozaj, pomeraj, r);
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
		
		g.fillOval((int)(getPolozaj().getX() - getPoluprecnikOpisane()),(int)(getPolozaj().getY()-getPoluprecnikOpisane()),(int)(getPoluprecnikOpisane()*2),(int) (getPoluprecnikOpisane()*2));
		
		g.setColor(prethodna);
	}

}
