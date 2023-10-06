package fakultet;

public class Student extends Osoba {
	
	
	private static int autoBrIndeksa = 0;
	private int brIndeksa = autoBrIndeksa++;
	
	private int godinaUpisa;
	private char oznaka = 'S';

	public Student(String ime, String prezime,int godinaUpisa) {
		super(ime, prezime);
		this.godinaUpisa = godinaUpisa;
	}

	@Override
	public char getOznaka() {
		return oznaka;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return sb.append(godinaUpisa)
		   .append("/")
		   .append(String.format("%04d",brIndeksa))
		   .append(" - ")
		   .append(super.toString()).toString();
	}
	
}



