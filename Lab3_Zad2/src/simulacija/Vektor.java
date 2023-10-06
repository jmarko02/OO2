package simulacija;

import java.util.Random;

public class Vektor {
	
	private double x,y;
	
	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public Vektor ort() {
		Vektor ort = new Vektor(0,0) ;
		double magnituda = Math.sqrt(x*x + y*y);
		if (magnituda == 0) return null;
		ort.x =  (this.x/magnituda);
		ort.y =  (this.y/magnituda);
		return ort;
	}
	public static Vektor slucajanVektor() {
		/*Random random = new Random();
		double x ,y;
		do {
			x = random.nextDouble() *2 -1;
			y = random.nextDouble() *2 -1;
		}while(x==0 && y==0);
		return new Vektor(x,y);*/
		Random random = new Random();
		double x,y;
		while(true) {
			x = random.nextDouble()*2-1;
			y = random.nextDouble()*2-1;
			if(x != 0 || y != 0) break;
		}
		return new Vektor(x,y);

	}
	
}
