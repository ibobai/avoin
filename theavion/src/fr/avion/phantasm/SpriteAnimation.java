package fr.avion.phantasm;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import fr.avion.temps.Timer;

public class SpriteAnimation {

	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private byte currentSprite;

	private boolean loop = false;
	private boolean play = false;
	private boolean destoryAfterAnim = false;

	private Timer timer;
	private int animationSpeed;
	private double xPos, yPos;
	private int width, height;
	private int limit;

	public SpriteAnimation(double xPos, double yPos, int rows, int columns, int animationSpeed, String imgPath) {
		this.animationSpeed = animationSpeed;
		this.xPos = xPos;
		this.yPos = yPos;
		
		try{
			URL url = this.getClass().getResource(imgPath);
			BufferedImage pSprite = ImageIO.read(url);
			int spriteWidth = pSprite.getWidth() / columns;
			int spriteHeight = pSprite.getHeight() / rows;
			for(int y = 0; y < rows; y++) {
				for(int x = 0; x < columns; x++){
					addSprite(pSprite
							, 0 + (x * spriteWidth)
							, 0 + (y * spriteHeight)
							, spriteWidth
							, spriteHeight);
				}
			}
					
			
		}catch(IOException e){};

		timer = new Timer();
		limit = sprites.size() - 1;
	}

	public void draw(Graphics2D g) {
		if (isSpriteAnimDestroyed())
			return;
		
		g.drawImage(sprites.get(currentSprite), (int) getxPos(), (int) getyPos(), width, height, null);
	}

	public void update(double delta) {
		if (isSpriteAnimDestroyed())
			return;

		if (loop && !play)
			loopAnimation();
		if (play && !loop)
			playAnimation();
	}

	public void stopAnimation() {
		loop = false;
		play = false;
	}

	public void resetSprite() {
		loop = false;
		play = false;
		currentSprite = 0;
	}

	private void loopAnimation() {
		if (timer.isTimerReady(animationSpeed) && currentSprite == limit) {
			currentSprite = 0;
			timer.resetTimer();
		}else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
			currentSprite++;
		} 
	}

	private void playAnimation() {
		if (timer.isTimerReady(animationSpeed) && currentSprite != limit && !isDestoryAfterAnim()) {
			play = false;
			currentSprite = 0;
		} else if (timer.isTimerReady(animationSpeed) && currentSprite == limit && isDestoryAfterAnim()) {
			sprites = null;
		}else if (timer.timerEvent(animationSpeed) && currentSprite != limit) {
			currentSprite++;
		}
	}
	
	public byte getCurrentSprite() {
		return currentSprite;
	}

	public void setCurrentSprite(byte currentSprite) {
		this.currentSprite = currentSprite;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public boolean isSpriteAnimDestroyed() {
		if (sprites == null)
			return true;

		return false;
	}

	public void addSprite(BufferedImage spriteMap, int xPos, int yPos,
			int width, int height) {
		sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
	}

	public void setPlay(boolean play, boolean destoryAfterAnim) {
		if(loop) {
			loop = false;
		}
		
		this.play = play;
		this.setDestoryAfterAnim(destoryAfterAnim);
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public boolean isDestoryAfterAnim() {
		return destoryAfterAnim;
	}

	public void setDestoryAfterAnim(boolean destoryAfterAnim) {
		this.destoryAfterAnim = destoryAfterAnim;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAnimationSpeed() {
		return animationSpeed;
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		if(limit > 0) {
			this.limit = limit - 1;
		} else {
			this.limit = limit;
		}
	}
	
	public void resetLimit() {
		limit = sprites.size() - 1;
	}

	public boolean isPlay() {
		return play;
	}
}
