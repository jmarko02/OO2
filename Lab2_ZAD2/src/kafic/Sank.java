package kafic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sank {
	
	private KartaPica kartaPica;
	//List<Pice> pica = new ArrayList<>();
	Map<Pice, Integer> pica;
	
	public Sank(KartaPica kartaPica) {
		this.kartaPica = kartaPica;
		pica = new HashMap<>();
		for(Pice p : kartaPica.getPica()) {
			pica.put(p, 0);
		}
	}
	
	public KartaPica getKartuPica() {
		return kartaPica;
	}
	
	public void snabdevanje(int index) throws GNePostoji {
		
		if(index < 0 || index > kartaPica.getPica().size()) throw new GNePostoji();
		
		Pice p = kartaPica.getPica().get(index);
		int count = pica.get(p);
		pica.put(p, count+1);
		
	}
	
	
	public void naruciPice(Pice p) {
		
	}
	
	public void otvoriRacun() {
		
	}
}
