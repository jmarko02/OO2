package simulacija;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.List;



public class Simulacija extends Frame {
	
	Scena scena;
	Panel panel = new Panel();
	boolean pauza = true;
	
	public Simulacija() {
		setBounds(700,200,400,300);
		setTitle("Simulacija");
		setResizable(false);
		
		populateWindow();

		pack();
		setVisible(true);
	}
	
	private void populateWindow() {
		
		scena = new Scena(this);
		
		setLayout(new BorderLayout());
		scena.setPreferredSize(new Dimension(getWidth(),getHeight()));
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		panel.add(scena);
		setFocusable(true);
		/*
		List chooseShape = new List(3);
		chooseShape.add("Disk");
		chooseShape.add("Zvezda");
		chooseShape.add("Tocak");
		*/
		Choice chooseShape = new Choice();
		chooseShape.add("Disk");
		chooseShape.add("Zvezda");
		chooseShape.add("Tocak");
		chooseShape.select(0);
		
		chooseShape.addItemListener((ie)->{
			String item = chooseShape.getSelectedItem();
			if(item.equals("Disk")) {
				scena.promeniFiguru("Disk");
			} else if(item.equals("Zvezda")) {
				scena.promeniFiguru("Zvezda");
			} else if (item.equals("Tocak")){
				scena.promeniFiguru("Tocak");
			}
			scena.requestFocus();
		});
		
		Panel listPanel = new Panel();
		listPanel.add(chooseShape);
		
		add(listPanel,BorderLayout.SOUTH);
		
		add(panel,BorderLayout.CENTER);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(scena != null) {
					scena.zaustaviTrajno();
				}
				dispose();
			}
		});
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char ev = e.getKeyChar();
				switch(ev) {
				case KeyEvent.VK_ESCAPE:
					requestFocus();
					scena.zaustaviTrajno();
					
					dispose();
				}
				//ostavljeno mesta za jos case-ova ako ih bude bilo
			}
		});
		/* 
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				scena.packScene();
				scena.repaint();
				pack();
			}
		});
		*/
	
		
	
			
	}

	public static void main(String[] args) {
		new Simulacija();
	}
}
