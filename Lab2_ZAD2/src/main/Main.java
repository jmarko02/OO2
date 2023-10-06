package main;

import kafic.*;

public class Main {

	public static void main(String[] args) {
		Pice[] pica = new Pice[] { new Sok("Ananas", 0.2f, 1100, Sok.Vrsta.NEGAZIRAN),
				new Sok("Coca Cola", 0.25f, 1200, Sok.Vrsta.GAZIRAN) };

		for (Pice pice : pica) {
			System.out.println(
					pice.getNaziv() + " " + pice.getZapremina() + " " + pice.getCena() + " " + pice.getOznaka());
			System.out.println(pice);
		}

		Sok sok = new Sok("Fanta", 0.25f, 1200, Sok.Vrsta.GAZIRAN);
		System.out.println(sok.getVrsta());

		
		KartaPica kartaPica = new KartaPica(); try { for (Pice pice: pica) {
		kartaPica.dodajPice(pice); } kartaPica.dodajPice(sok);
		kartaPica.dodajPice(sok); } catch (GPostoji e) {}
		 
		System.out.println(kartaPica.getBrojPica()); System.out.println(kartaPica);
		
	}

}
