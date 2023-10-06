package kafic;

public class Pivo extends Pice {

	
	private double alkohol = 0;
	Pivo(String naziv, float zapremina, int cena,double alkohol) {
		super(naziv, zapremina, cena);
		// TODO Auto-generated constructor stub
		this.alkohol = alkohol;
	}
	
	double getAlkohol() {
		return alkohol;
	}
	
	@Override
	public char getOznaka() {
		// TODO Auto-generated method stub
		return 'P';
	}
	

}
