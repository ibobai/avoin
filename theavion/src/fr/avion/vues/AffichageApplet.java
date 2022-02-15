package fr.avion.vues;

import java.awt.BorderLayout;

import javax.swing.JApplet;

public class AffichageApplet extends JApplet{

	private static final long serialVersionUID = 1L;
	private Affichage Affichage = new Affichage();
	
	public void init(){
		setLayout(new BorderLayout());
		add(Affichage);
	}
	
	public void start(){
		Affichage.start();
	}
	
	public void stop(){
		Affichage.stop();
	}
}
