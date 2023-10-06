package kafic;

public class RacunSaOgranicenjem extends Racun {
	
	private double maksimum = 0;
	private double trenutno = 0;
	
	public RacunSaOgranicenjem(double maksimum) {
		super();
		this.maksimum = maksimum;
	}
	
	public double getMaksimum() {
		return maksimum;
	}
	
	@Override
	public void dodajPice(Pice p) {
		if(p == null) return;
		if(!(p instanceof Pivo)) pica.add(p); 
		else {
			Pivo obj = (Pivo)p;
			if(trenutno + obj.getAlkohol() > maksimum) return;
			pica.add(obj);
			trenutno += obj.getAlkohol();
		}
	}
	
}
