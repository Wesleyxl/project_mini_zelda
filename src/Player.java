import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public class Player extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//player
	public int speed = 4;
	public boolean RIGHT, UP, DOWN, LEFT;
	public int direction = 1;
	
	// animations 
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;
	
	// bullets
	public boolean shot = false;
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	

	public Player(int x, int y) {

		super(x,y, 32, 32);

	}
	
	public void tick() {
		
		boolean moved = false;
		

		if (RIGHT && World.isFree(x + speed, y)) {
			x += speed;
			moved = true;
			direction = 1;
		} else if (LEFT && World.isFree(x - speed, y)) {
			x -= speed;
			moved = true;
			direction = -1;
		}
		
		if (UP  && World.isFree(x, y - speed)) {
			y -= speed;
			moved = true;
		} else if (DOWN && World.isFree(x, y + speed)) {
			y += speed;
			moved = true;
		}
		
		if (moved) {			
			curFrames++;
			if (curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if (curAnimation == SpriteSheet.player_front.length) {
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
		
//		g.drawImage(SpriteSheet.player_front[curAnimation], x, y, 32, 32, null);
		g.drawImage(SpriteSheet.player_front[curAnimation], x, y, 32, 32, null);
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
	}

	
}
