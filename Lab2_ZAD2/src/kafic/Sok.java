package kafic;

public class Sok extends Pice {
	
	public static enum Vrsta{GAZIRAN,NEGAZIRAN}
	
	private Vrsta vrsta;
	
	public Sok(String naziv, float zapremina, int cena, Vrsta vrsta) {
		super(naziv, zapremina, cena);
		this.vrsta = vrsta;
	}
	
	public Vrsta getVrsta() {
		return vrsta;
	}

	@Override
	public char getOznaka() {
		if(vrsta.name().equals("GAZIRAN")) return 'G'; // == Vrsta.GAZIRAN 
		else return 'N'; 
	}
	
}
