package fakultet;

public class GViseNastavnika extends Exception {
	
	@Override
	public String toString() {
		return "Ne moze biti vise od jednog nastavnika na predmetu!";
	}
}
