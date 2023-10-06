package EnergetskiSistem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {

	
	Plac plac ;
	Baterija baterija;
	Panel izborParcele = new Panel();
	
	public EnergetskiSistem(int redovi,int kolone,int kapacitet) {
		plac = new Plac(redovi,kolone);
		baterija = new Baterija(kapacitet);
		setBounds(700,200,500,500);
		setTitle("Energetski sistem");
		setResizable(false);
		
		Panel buttonPanel = new Panel();
		Button button = new Button("Dodaj");
		
		baterija.isprazniBateriju();
		Panel finalSouthPanel = new Panel(new BorderLayout());
		Panel leftPanel = new Panel(new GridLayout(3,1));
		leftPanel.setBackground(Color.BLUE);
		Panel rightPanel = new Panel();
		
		rightPanel.add(baterija.getLabel());
		
		CheckboxGroup grupa = new CheckboxGroup();
		Checkbox h = new Checkbox("Hidroelektrana",true,grupa);
		Checkbox m = new Checkbox("Mlin", false, grupa);

		Label l = new Label("Izaberite: ");
		l.setAlignment(Label.LEFT);
		
		leftPanel.add(l);
		leftPanel.add(h);
		leftPanel.add(m);
		
		
		
		finalSouthPanel.add(leftPanel,BorderLayout.WEST);
		finalSouthPanel.add(rightPanel,BorderLayout.EAST);
		
		
		
		button.addActionListener((ae)->{
			if(h.getState()==true) {
				plac.dodajProizvodjaca(new Hidroelektrana(baterija));
			} else {
				plac.dodajPotrosaca(new Mlin(baterija));
			}
		});
		
		buttonPanel.add(button);
		add(buttonPanel,BorderLayout.NORTH);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustaviSveProizvodjace();
				dispose();
			}
		});
		
		Menu menu = new Menu("Akcija");
		MenuItem quitMenu = new MenuItem("Ugasi",new MenuShortcut(KeyEvent.VK_E));
		
		
		quitMenu.addActionListener((ae)->{
			dispose();
		});
		menu.add(quitMenu);
		MenuBar bar = new MenuBar();
		bar.add(menu);
		setMenuBar(bar);
		
		add(plac,BorderLayout.CENTER);
		add(finalSouthPanel,BorderLayout.SOUTH);
		
		setVisible(true);
		
		
	}
	
	
	public static void main(String[] args) {
		new EnergetskiSistem(5,5,100);

	}

}
