package fr.avion.niveau;

import java.awt.Graphics2D;

import fr.avion.ecrandejeu.*;

public interface SuperLevel {

	void draw(Graphics2D g);
	void update(double delta, BasicBlocks blocks);
	void hasDirectionChange(double delta);
	void changeDurectionAllEnemys(double delta);
	
	boolean isGameOver();
	boolean isComplete();
	
	void destory();
	void reset();
}
