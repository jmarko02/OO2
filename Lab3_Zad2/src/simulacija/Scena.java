package simulacija;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Scena extends Canvas implements Runnable  {
	
	private int factor = 3;
	private Simulacija simulacija;
	private ArrayList<Figura> figure = new ArrayList<>();
	private Thread thread;
	String string = "Disk";
	
	private boolean work = false;
	
	public Scena(Simulacija simulacija) {
		setBackground(Color.GRAY);
		this.simulacija = simulacija;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(work == false) {
					dodajFiguru(e.getX(), e.getY());
				}
			}
		});
		
		addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				
				char event = e.getKeyChar();
				switch(event) {
				
				case KeyEvent.VK_SPACE:
					
					work = !work;
					
					repaint();
					break;
				
				case KeyEvent.VK_ESCAPE:
					
					zaustaviTrajno();
					simulacija.dispose();
					break;
					
				}			
				
			}
		});
		
		thread = new Thread(this);
		thread.start();
	}
	/*
	public void setFactor(int factor) {
		this.factor = factor;
	}
	*/

	
	public synchronized void pokreni() {
		work = true;
	}
	public synchronized boolean radi() {
		return work;
	}
	public synchronized void pauziraj(Graphics g) {
		if(work == true) notify(); 
		
		int x = getWidth()/2 - 70;
		int y = getHeight()/2;
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,50));
		g.drawString("Pauza", x, y);
		 

	}
	public synchronized void zaustaviTrajno() {
		if(thread != null) {
			thread.interrupt();
		}
		while(thread != null) {
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		/*
		 * moglo je samo:
		 * if(thread != null) {
		 * 		thread.interrupt();
		 * 		try{
		 * 			thread.join();
		 * 		}catch(InterruptedException e) {}
		 * }
		 */
	}
	public synchronized void dodajFiguru(int x,int y) {
		
		Vektor polozaj = new Vektor(x,y);
		Vektor pomeraj = Vektor.slucajanVektor();
		//Disk figura = new Disk(polozaj,pomeraj);
		//Zvezda figura = new Zvezda(polozaj,pomeraj);
		//Tocak figura = new Tocak(polozaj,pomeraj,20);
		Figura figura ;
		if(string.equals("Disk")) {
			figura = new Disk(polozaj,pomeraj);
		} else if(string.equals("Zvezda")) {
			figura = new Zvezda(polozaj,pomeraj);
			
		} else {
			figura = new Tocak(polozaj,pomeraj,20);
			
		}
		
		if(x + figura.getPoluprecnikOpisane() > getWidth() || x - figura.getPoluprecnikOpisane() < 0 ||
			y + figura.getPoluprecnikOpisane() > getHeight() || y - figura.getPoluprecnikOpisane() < 0){
				return;
			}
		
		for(Figura f : figure) {
			if(figura.poklapanjeOpisanihKruznica(f)) {
				return;
			}
		}

		figure.add(figura);
		
		repaint();
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		
		for(Figura f:figure) {
			f.iscrtajFiguru(g);
		}
		if(work == false) {
			pauziraj(g);
		}
		
	}



	@Override
	public void run() {
		
		
		try {
			
			while(!Thread.interrupted()) {
				
				
				if(radi()) {
					pomeriFigure();
				}
				
				Thread.sleep(100);
				
			}
		}catch(InterruptedException e) {}
		
		synchronized (this) {
			thread = null;
			notify();
		}
		
	}



	private void pomeriFigure()  {
		for(Figura f: figure) {
			
			
			
			Vektor ort = f.getPomeraj().ort();
			double x = f.getPolozaj().getX() + ort.getX()*factor;
			double y = f.getPolozaj().getY() + ort.getY()*factor;
			
			f.getPolozaj().setX(x);
			f.getPolozaj().setY(y);
			

			kolizijaSaZidovima(f);
			kolizijaSaDrugimFigurama(f);
			
			
		}
		repaint();
	}


	private void kolizijaSaZidovima(Figura f) {
		
		double x = f.getPolozaj().getX();
		double y = f.getPolozaj().getY();
		//boolean sudar = false;
		if(x + f.getPoluprecnikOpisane() > getWidth() || x - f.getPoluprecnikOpisane() < 0 ){
			double x1 = f.getPomeraj().getX() * (-1);
			f.getPomeraj().setX(x1);
			if(f instanceof Zvezda) {
				Zvezda figura = (Zvezda)f;
				int br = figura.getBrojSudara();
				br++;
				figura.setBrojSudara(br);
			}
			
		}
		if(y + f.getPoluprecnikOpisane() > getHeight() || y - f.getPoluprecnikOpisane() < 0) {
			double y1 = f.getPomeraj().getY() * (-1);
			f.getPomeraj().setY(y1);
			if(f instanceof Zvezda) {
				Zvezda figura = (Zvezda)f;
				int br = figura.getBrojSudara();
				br++;
				figura.setBrojSudara(br);
			}
		}
		
	}
	private void kolizijaSaDrugimFigurama(Figura f) {
		for(Figura druga : figure) {
			
			if(f.poklapanjeOpisanihKruznica(druga) && f != druga) {
				
				if(f instanceof Zvezda) {
					Zvezda figura = (Zvezda)f;
					int br = figura.getBrojSudara();
					br++;
					figura.setBrojSudara(br);
				}
				if(druga instanceof Zvezda) {
					Zvezda figura = (Zvezda)druga;
					int br = figura.getBrojSudara();
					br++;
					figura.setBrojSudara(br);
				}
				Vektor pomeraj1 = f.getPomeraj();
				Vektor pomeraj2 = druga.getPomeraj();
				
				double x1 = pomeraj1.getX();
				double y1 = pomeraj1.getY();
				
				double x2 = pomeraj2.getX();
				double y2 = pomeraj2.getY();
				
				f.getPomeraj().setX(x2);
				f.getPomeraj().setY(y2);
				
				druga.getPomeraj().setX(x1);
				druga.getPomeraj().setY(y1);
				
				/*
				double angle = Math.atan2(druga.getPolozaj().getY()-f.getPolozaj().getY(), druga.getPolozaj().getX()-f.getPolozaj().getX());
				
				double velocity1X = f.getPomeraj().getY()*Math.sin(angle) + f.getPomeraj().getX()*Math.cos(angle),
						velocity2X = druga.getPomeraj().getY()*Math.sin(angle) + f.getPomeraj().getX()*Math.cos(angle),
						velocity1Y = f.getPomeraj().getY()*Math.cos(angle) - f.getPomeraj().getX()*Math.sin(angle),
						velocity2Y = druga.getPomeraj().getY()*Math.cos(angle) - druga.getPomeraj().getX()*Math.sin(angle); 
				
				f.getPomeraj().setY(velocity2X*Math.sin(angle)+velocity1Y*Math.cos(angle)); 
				f.getPomeraj().setX(velocity2X*Math.cos(angle)-velocity1Y*Math.sin(angle)); 
				druga.getPomeraj().setY(velocity1X*Math.sin(angle)+velocity2Y*Math.cos(angle)); 
				druga.getPomeraj().setX(velocity1X*Math.cos(angle)-velocity2Y*Math.sin(angle)); 
				*/ 
				
			}
		
		}
		
	}


	public void promeniFiguru(String str) {
		string = str;
	}

/*
	public void packScene() {
	
		int x = simulacija.panel.getWidth();
		int y = simulacija.panel.getHeight();
		int dim = Math.max(x, y);
		
		setPreferredSize(new Dimension(dim,dim));
	}
*/

	


	
	

}
