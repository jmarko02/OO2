package kafic;

import java.util.ArrayList;
import java.util.List;

public class Racun {

	protected List<Pice> pica = new ArrayList<>();
	
	private static int StaticID = 0;
	private int id = ++StaticID;

	public void dodajPice(Pice p) {
		if(p == null) return;
		pica.add(p);
	}
	public double iznos() {
		int iznos = 0;
		for(Pice p : pica) iznos += p.getCena();
		return iznos;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Pice p : pica) {
			sb.append(p.toString()).append('\n');
		}
		sb.append(iznos());
		return sb.toString();
	}
}
