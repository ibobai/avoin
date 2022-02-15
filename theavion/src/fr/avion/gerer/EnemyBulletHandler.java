package fr.avion.gerer;

import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;


import fr.avion.ecrandejeu.Player;
import fr.avion.ecrandejeu.*;
import fr.avion.explosion.*;
import fr.avion.tiredenemy.*;


public class EnemyBulletHandler {
	
	private List<EnemyWeaponType> weaponTypes = new ArrayList<>();
	
	public void addBullet(EnemyWeaponType weaponType) {
		this.weaponTypes.add(weaponType);
	}

	public void draw(Graphics2D g) {
		for (EnemyWeaponType enemyWeaponType : weaponTypes) {
			enemyWeaponType.draw(g);
		}
	}
	
	public void update(double delta, BasicBlocks blocks, Player player) {
		for (int i = 0; i < weaponTypes.size(); i++) {
			weaponTypes.get(i).update(delta, blocks, player);
			if (weaponTypes.get(i).collision(player.getRect())) {
				ExplosionManager.createPixelExplosion(weaponTypes.get(i).getxPos(), weaponTypes.get(i).getyPos());
				weaponTypes.remove(i);
				player.hit();
			}
		}
	}

	public void reset() {
		weaponTypes.clear();
	}
	
}
