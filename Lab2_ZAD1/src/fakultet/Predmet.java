package fakultet;

import java.util.ArrayList;
import java.util.List;

public class Predmet {

	String naziv, sifra;
	List<Osoba> osobe = new ArrayList<>();
	
	public Predmet(String naziv, String sifra){
		this.naziv = naziv;
		this.sifra = sifra;
	}
	
	public String getSifra() {
		return sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void dodajOsobu(Osoba o) throws GViseNastavnika {
		
		if(o.getOznaka() == 'N') {
			for(int i=0; i<osobe.size(); i++) {
				if(osobe.get(i).getOznaka() == 'N') throw new GViseNastavnika();
			}
		}
		osobe.add(o);
	
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv).append(" (").append(sifra).append(")").append("\n");
		for(Osoba o: osobe) {
			sb.append(o).append('\n');
		}
		return sb.toString();
	}
	
	public Osoba dohvNastavnika() {
		for(Osoba o : osobe) {
			if(o.getOznaka() == 'N') return o;
		}
		return null;
	}
	
	public boolean daLiSlusa(Osoba student) {
		
		if(student == null) return false;
		if(osobe.contains(student) && student instanceof Student) return true;
		else return false;	
		
	}
	
	public void ovlasti(Nastavnik nastavnik, Saradnik saradnik) throws GPristup {
		if(nastavnik == null || saradnik == null) return;
		
		if(this.dohvNastavnika() == null)  throw new GPristup();
		saradnik.add(this);
	}
	
}
