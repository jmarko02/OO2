package skijanje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Staza {

	private List<Deonica> deonice = new ArrayList<>();
	private String naziv;
	
	public Staza(String naziv) {
		this.naziv = naziv;
	}
	
	public void dodaj(Deonica deonica) {
		if(deonica == null) return;
		deonice.add(deonica);
	}
	
	public int broj() {
		/*
		 * int count = 0; for(Deonica d : deonice) { count++; }
		 */
		return deonice.size();
	}
	
	public double duzina() {
		double ukupna = 0;
		for(Deonica d: deonice) {
			ukupna += d.duzina();
		}
		return ukupna;
	}
	
	public double nagib() {
		double maks = 0;
		for(Deonica d : deonice) {
			if(d.nagib() > maks) maks = d.nagib();
		}
		return maks;
	}
	
public char oznaka() throws GOznaka {
		
		if(deonice.isEmpty()) throw new GOznaka();
		
		char[] oznake = new char[deonice.size()];
        for (int i = 0; i < deonice.size(); i++) {
            oznake[i] = deonice.get(i).oznaka();
        }
        
       
        Arrays.sort(oznake);
       
        char najcescaOznaka = oznake[0];
        int najcescaOznakaBrojac = 1;
        int trenutnaOznakaBrojac = 1;
        for (int i = 1; i < oznake.length; i++) {
            if (oznake[i] == oznake[i-1]) {
                trenutnaOznakaBrojac++;
            } else {
                if (trenutnaOznakaBrojac > najcescaOznakaBrojac) {
                    najcescaOznaka = oznake[i-1];
                    najcescaOznakaBrojac = trenutnaOznakaBrojac;
                }
                trenutnaOznakaBrojac = 1;
            }
        }
        
        if (trenutnaOznakaBrojac > najcescaOznakaBrojac) {
            najcescaOznaka = oznake[oznake.length-1];
        }
        
        return najcescaOznaka;
	}
	
	public double brzina(double pocetna) {
		if(deonice.isEmpty()) return pocetna;
		double krajnja = pocetna;
		for(Deonica d : deonice) {
			krajnja = d.brzina(krajnja);
		}
		return krajnja;
	}
	
	public double vreme(double pocetna) {
		if(deonice.isEmpty()) return 0;
		double krajnja = pocetna;
		double vreme = 0;
		for(Deonica d : deonice) {
			vreme += d.vreme(krajnja);
			krajnja = d.brzina(krajnja);
		}
		return vreme;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv).append("|")
		.append(broj()).append("|")
		.append(duzina()).append("|")
		.append(nagib()).append('\n');
		sb.append("[");
		if(deonice.size()>0) {
			sb.append(deonice.get(0));
			for(int i=1; i < broj(); i++) {
				sb.append(",");
				sb.append(deonice.get(i));
			}
		}
		sb.append("]");
		return sb.toString();	
		// za sb postoji sb.deleteCharAt(sb.lentgh()-1);
	}
}
