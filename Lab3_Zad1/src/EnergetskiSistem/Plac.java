package EnergetskiSistem;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

public class Plac extends Panel{
	
	private int redovi,kolone;
	private ArrayList<Parcela> parcele = new ArrayList<>(redovi*kolone);
	private ArrayList<Proizvodjac> proizvodjaci = new ArrayList<>();
	private ArrayList<Potrosac> potrosaci = new ArrayList<>();
	private Parcela izabranaParcela ;
	
	
	public Plac(int redovi, int kolone) {
		super(new GridLayout(redovi,kolone,3,3));
		this.redovi = redovi;
		this.kolone = kolone;
		
		for(int i=0; i < redovi;i++) {
			for(int j = 0; j < kolone; j++) {
				
				double ver=Math.random();
				if(ver>0.7) {
					VodenaPovrs v=new VodenaPovrs();
					add(v);
					parcele.add(v);
				}
				else {
					TravnataPovrs t=new TravnataPovrs();
					add(t);
					parcele.add(t);
				}
			}
		}
	}
	public void izaberiParcelu(Parcela novaParcela) {
		if(!parcele.contains(novaParcela)) return;
				
		if(izabranaParcela != null) {
			izabranaParcela.setFont(new Font(Font.SERIF, Font.BOLD,14));
			izabranaParcela.revalidate(); 
		}
		izabranaParcela = novaParcela;
		//moglo i sa new opet da se menja font ali ovo je drugi(lepsi) nacin
		Font stari = izabranaParcela.getFont();
		Font novi = stari.deriveFont(20f);
		izabranaParcela.setFont(novi);
		izabranaParcela.revalidate();
		
	}
	
	public void dodajProizvodjaca(Proizvodjac proizvodjac) {
		if(izabranaParcela == null) {
			proizvodjac.zaustavi();
			return;
		}
		//ako selektujemo parcelu na kojoj se vec nalazi proizvodjac onda gasimo prethodnog proizvodjaca i stavljamo novog
		if(izabranaParcela instanceof Proizvodjac) {
			((Proizvodjac)izabranaParcela).zaustavi();
			proizvodjaci.remove(izabranaParcela);
		}
		
		//izbacujemo parcelu sa tog mesta u listi i na to mesto stavljamo proizvodjaca
		int index = parcele.indexOf(izabranaParcela);
		parcele.remove(index);
		parcele.add(index,proizvodjac);
		proizvodjaci.add(proizvodjac);
		
		remove(index);
		add(proizvodjac,index);
		revalidate();
		
		izaberiParcelu(proizvodjac);
		
		azurirajBrojVodenihPovrsi();
		
		
	}
	private void azurirajBrojVodenihPovrsi() {
		for(Proizvodjac p :proizvodjaci) {
			if(p instanceof Hidroelektrana) {
				Hidroelektrana hidroelektrana = (Hidroelektrana)p;
				int noviBroj = BrojOkruzujucihVodenihPovrsi(hidroelektrana);
				hidroelektrana.setBrojVodenihPovrsina(noviBroj);
			}
		}
	}
	
	private int BrojOkruzujucihVodenihPovrsi(Hidroelektrana h) {
		int broj = 0;
		int index = parcele.indexOf(h);
		int red = index/kolone;
		int kolona = index % kolone;
		
		for(int i = red - 1; i <= red+1;i++) {
			for(int j = kolona-1; j <= kolona+1; j++) {
				if(i < 0 || i>=redovi || j<0 || j>= kolone) continue;
				if(parcele.get(i*kolone + j) instanceof VodenaPovrs) broj++;
			}
		}
		return broj;
	}
	public  void zaustaviSveProizvodjace() {
		for(Proizvodjac p : proizvodjaci) {
			p.zaustavi();
		}
	}
	
	public void dodajPotrosaca(Potrosac potrosac) {
		if(izabranaParcela == null) {
			potrosac.finish();
			return;
		}
		//ako selektujemo parcelu na kojoj se vec nalazi proizvodjac onda gasimo prethodnog proizvodjaca i stavljamo novog
		if(izabranaParcela instanceof Potrosac) {
			((Potrosac)izabranaParcela).finish();
			potrosaci.remove(izabranaParcela);
		}
		
		//izbacujemo parcelu sa tog mesta u listi i na to mesto stavljamo proizvodjaca
		int index = parcele.indexOf(izabranaParcela);
		parcele.remove(index);
		parcele.add(index,potrosac);
		potrosaci.add(potrosac);
		
		remove(index);
		add(potrosac,index);
		revalidate();
		
		izaberiParcelu(potrosac);
		
		azurirajBrojTravnatihPovrsi();
		
		
	}
		private void azurirajBrojTravnatihPovrsi() {
		for(Potrosac p :potrosaci) {
			if(p instanceof Mlin) {
				Mlin mlin = (Mlin)p;
				int noviBroj = BrojOkruzujucihTravnatihPovrsi(mlin);
				mlin.postaviBrojTravnatih(noviBroj);;
			}
		}
	}
		private int BrojOkruzujucihTravnatihPovrsi(Mlin h) {
			int broj = 0;
			int index = parcele.indexOf(h);
			int red = index/kolone;
			int kolona = index % kolone;
			
			for(int i = red - 1; i <= red+1;i++) {
				for(int j = kolona-1; j <= kolona+1; j++) {
					if(i < 0 || i>=redovi || j<0 || j>= kolone) continue;
					if(parcele.get(i*kolone + j) instanceof TravnataPovrs) broj++;
				}
			}
			return broj;
		}
		
		public  void zaustaviSvePotrosace() {
			for(Potrosac p : potrosaci) {
				p.finish();
			}
		}
	
}
