package fr.avion.etat;

import java.awt.Canvas;
import java.awt.Graphics2D;

public abstract class SuperEtatDeMachine {

	private EtatDeMachine EtatDeMachine;
	
	public SuperEtatDeMachine(EtatDeMachine EtatDeMachine) {
		this.EtatDeMachine = EtatDeMachine;
	}
	
	public abstract void update(double delta);
	public abstract void draw(Graphics2D g);
	public abstract void init(Canvas canvas);
	
	public EtatDeMachine getEtatDeMachine() {
		return EtatDeMachine;
	}
}
