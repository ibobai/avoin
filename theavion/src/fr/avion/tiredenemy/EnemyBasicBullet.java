package fr.avion.tiredenemy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import fr.avion.ecrandejeu.BasicBlocks;
import fr.avion.ecrandejeu.Player;
import fr.avion.vues.Affichage;
import fr.avion.ecrandejeu.*;


public class EnemyBasicBullet extends EnemyWeaponType{

	private Rectangle bullet;
	private double speed = 2.5d;
	private int xPos, yPos;
	
	public EnemyBasicBullet(double xPos, double yPos) {
		bullet = new Rectangle((int) xPos, (int) yPos, 5, 5);
		setxPos((int) xPos); 
		setyPos((int) yPos); 
	}
	
	@Override
	public void draw(Graphics2D g) {
		if (bullet == null) {
			return;
		}
		
		g.setColor(Color.RED);
		g.fill(bullet);
	}

	@Override
	public void update(double delta, BasicBlocks blocks, Player player) {
		if (bullet == null) {
			return;
		}
		
		setyPos((int) (getyPos() + (delta * speed))); 
		bullet.y = getyPos();
		
		isOutofBounds();
		wallCollide(blocks);
	}

	@Override
	public boolean collision(Rectangle rect) {
		if (bullet != null && bullet.intersects(rect)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean destory() {
		return false;
	}

	@Override
	protected void wallCollide(BasicBlocks blocks) {
		if (bullet == null) {
			return;
		}
		
		for (int w = 0; w < blocks.wall.size(); w++) {
			if(bullet.intersects(blocks.wall.get(w))) {
				blocks.wall.remove(w);
				bullet = null;
				break;
			}
		}
	}

	@Override
	protected void isOutofBounds() {
		if(bullet != null && bullet.y < 0 || bullet.y > Affichage.HEIGHT || bullet.x < 0 || bullet.x > Affichage.WIDTH){
			bullet = null;
		}
	}

	public Rectangle getBullet() {
		return bullet;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
}
