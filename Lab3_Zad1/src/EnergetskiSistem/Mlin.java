package EnergetskiSistem;

import java.awt.Color;

public class Mlin extends Potrosac {

	public Mlin(Baterija baterija) {
		super('M', Color.ORANGE, baterija);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cekaj() {
			try {
				Thread.sleep(7000-brojTravnatih*1000);
			} catch (InterruptedException e) {}
	}

}
