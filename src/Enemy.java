import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Enemy extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//player
	public int speed = 2;
	public int RIGHT = 1, UP = 0, DOWN = 0, LEFT = 0;
	public int direction = 1;
	
	// animations 
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;
	
	// bullets
	public boolean shot = false;
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	

	public Enemy(int x, int y) {

		super(x,y, 32, 32);

	}
	
	public void followPlayer() {
		
		Player player = Game.player;
		if (x < player.x && World.isFree(x + speed, y)) {
			if (new Random().nextInt(100) < 50) {				
				x += speed;
			}
		} else if (x > player.x  && World.isFree(x - speed, y)) {
			if (new Random().nextInt(100) < 50) {				
				x -= speed;
			}
		}
		
		if (y < player.y  && World.isFree(x, y + speed)) {
			if (new Random().nextInt(100) < 50) {				
				y += speed;
			}
		} else if (y > player.y  && World.isFree(x, y - speed)) {
			if (new Random().nextInt(100) < 50) {				
				y -= speed;
			}
		}
		
	}
	
	public void tick() {
		
		boolean moved = true;
		
		followPlayer();
		
		if (moved) {			
			curFrames++;
			if (curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if (curAnimation == SpriteSheet.enemy_front.length) {
					curAnimation = 0;
				}
			}
		}
		
		if (shot) {
			shot = false;
			bullets.add(new Bullet(x, y, direction));
		}
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(SpriteSheet.enemy_front[curAnimation], x, y, 32, 32, null);
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
	}

	
}
