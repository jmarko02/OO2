package fakultet;

import java.util.ArrayList;
import java.util.List;

public class Saradnik extends Osoba {

	
	public static enum Zvanje{sar,as}
	private Zvanje zvanje ;
	
	private List<Predmet> ovlascenja = new ArrayList<>();
	
	public Saradnik(String ime, String prezime, Zvanje zvanje) {
		super(ime, prezime);
		this.zvanje = zvanje;
	}

	@Override
	public char getOznaka() {
		// TODO Auto-generated method stub
		return 'R';
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(zvanje == Zvanje.sar) sb.append("ms");
		sb.append(super.toString());
		return sb.toString();
		}

	public void add(Predmet predmet) {
		for(Predmet p : ovlascenja) {
			if(p == predmet) ovlascenja.remove(predmet);
			return;
		}
		ovlascenja.add(predmet);
	}
	
}
