package kafic;

import java.util.ArrayList;
import java.util.List;

public class KartaPica {
	
	private List<Pice> pica = new ArrayList<>();
		
	public void dodajPice(Pice p) throws GPostoji{
		if(p == null) return;
		for(Pice pice : pica) {
			if(pice.equals(p)) throw new GPostoji();
		}
		pica.add(p);
	}

	public int getBrojPica() {
		return pica.size();
	}
	
	public List<Pice> getPica(){
		return pica;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("GAZIRANI SOKOVI").append('\n');
		for(Pice pice : pica) {
			if(pice.getOznaka() == 'G') sb.append(pice).append('\n');
		}
		sb.append("NEGAZIRANI SOKOVI").append('\n');
		for(Pice pice : pica) {
			if(pice.getOznaka() == 'N') sb.append(pice).append('\n');
		}
		sb.append("PIVO").append('\n');
		for(Pice pice : pica) {
			if(pice.getOznaka() == 'P') sb.append(pice).append('\n');
		}
		
		return sb.toString();
		}
}
