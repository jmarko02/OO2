package EnergetskiSistem;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {

	private int brojVodenihPovrsina;
	
	public Hidroelektrana(Baterija baterija) {
		super('H', Color.BLUE, 1500, baterija);
		this.setBrojVodenihPovrsina(0);
	}

	@Override
	protected synchronized int proizvodnjaEnergije() {
		return brojVodenihPovrsina;
	}

	@Override
	protected synchronized boolean uspesnaProizvodnjaEnergije() {
		return brojVodenihPovrsina > 0;
	}

	public synchronized void setBrojVodenihPovrsina(int brojVodenihPovrsina) {
		this.brojVodenihPovrsina = brojVodenihPovrsina;
	}

}
