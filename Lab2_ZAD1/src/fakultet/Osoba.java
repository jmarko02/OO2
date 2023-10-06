package fakultet;

public abstract class Osoba {
	
	protected String ime, prezime;
	
	public Osoba(String ime, String prezime) {
		this.ime = ime ;
		this.prezime = prezime;
	}
	
	public abstract char getOznaka();
	
	@Override
	public String toString() {
		return ime + " " + prezime;
	
	}
}
