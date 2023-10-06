package fakultet;

public class Nastavnik extends Osoba {

	
	public enum Zvanje{doc, prof} 
	
	private Zvanje zvanje;
	private char oznaka = 'N';
	
	public Nastavnik(String ime, String prezime,Zvanje zvanje) {
		super(ime, prezime);
		this.zvanje = zvanje;
	}

	@Override
	public char getOznaka() {
		
		return oznaka;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(zvanje).append(". dr ").append(ime).append(" ").append(prezime);
		return sb.toString();
	}

}
