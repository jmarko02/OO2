package kafic;

public abstract class Pice {
	protected String naziv;
	protected float zapremina;
	protected int cena;

	Pice(String naziv, float zapremina, int cena) {
		this.naziv = naziv;
		this.zapremina = zapremina;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public float getZapremina() {
		return zapremina;
	}

	public float getCena() {
		return cena * zapremina;
	}

	public abstract char getOznaka();

	 @Override public boolean equals(Object obj) {
	 if (obj == null) return false;
	 if (obj == this) return true;
	 if (!(obj instanceof Pice)) return false;
	 Pice p = (Pice) obj; return this.naziv.equals(p.naziv) && this.zapremina == p.zapremina; }
	 

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naziv).append(" (")
		.append(String.format("%.2f", zapremina)).append(" L): ")
		.append(String.format("%.2f", getCena())).append(" RSD");

		return sb.toString();
	}

}
