package fr.avion.etat;

import java.awt.Canvas;

import java.awt.Graphics2D;
import java.util.ArrayList;
import fr.avion.ecrandejeu.*;
import fr.avion.ecranmenu.*;

public class EtatDeMachine {

	private ArrayList<SuperEtatDeMachine> states = new ArrayList<SuperEtatDeMachine>();
	private Canvas canvas;
	private byte selectState = 0;
	
	public EtatDeMachine(Canvas canvas){
		SuperEtatDeMachine game = new GameScreen(this);
		SuperEtatDeMachine menu = new MenuScreen(this);
		states.add(menu);
		states.add(game);
		
		this.canvas = canvas;
	}
	
	public void draw(Graphics2D g){
		states.get(selectState).draw(g);
	}
	
	public void update(double delta){
		states.get(selectState).update(delta);
	}
	
	public void setState(byte i){
		for(int r = 0; r < canvas.getKeyListeners().length; r++)
			canvas.removeKeyListener(canvas.getKeyListeners()[r]);
		selectState = i;
		states.get(selectState).init(canvas);
	}

	public byte getStates() {
		return selectState;
	}
}
