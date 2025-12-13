package Utils;

import java.awt.geom.Rectangle2D;

import main.Game;

import static Utils.Constans.Projectiles.*;

public class Projectile {
    private Rectangle2D.Float hitbox;
    private int dir;
    private boolean active =true;

	public Projectile(int x, int y, int dir) {
		int xOffset = (int) (-3 * DisplayManager.SCALE);
		int yOffset = (int) (-10 * DisplayManager.SCALE);

		if (dir == 1)
			xOffset = (int) (29 * DisplayManager.SCALE);

		hitbox = new Rectangle2D.Float(x + xOffset, y + yOffset, BulletWidth, BulletHeight);
		this.dir = dir;
	}

	public void updatePos() {
		hitbox.x += dir * Speed;
	}

	public void setPos(int x, int y) {
		hitbox.x = x;
		hitbox.y = y;
	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
}

