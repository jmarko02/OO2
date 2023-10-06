 package EnergetskiSistem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label {
	
	//moze biti apstraktna klasa
	
	private char oznaka;
	private static final Color bojaOznake = Color.WHITE;
	private static final Font fontOznake = new Font(Font.SERIF, Font.BOLD,14);
	
	public Parcela(char oznaka, Color boja) {
		super();
		setFont(fontOznake);
		setForeground(bojaOznake);
		setBackground(boja);
		this.oznaka = oznaka;
		setText(""+this.oznaka);
		setAlignment(Label.CENTER);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Plac p = (Plac)getParent();
				p.izaberiParcelu(Parcela.this);
				//adapterska anonimna klasa pa mora Parcela.this da bi se dohvatilo
			}
		});
	}
	public void promeniBojuPozadine(Color novaboja) {
		setBackground(novaboja);
	}
	
}
