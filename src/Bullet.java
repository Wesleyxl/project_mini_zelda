import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int direction = 1;
	public int speed = 8;
	public int frames = 0;
	
	
	public Bullet(int x, int y, int direction) {
		super(x, y, 10,10);
		this.direction = direction;
	}
	
	public void tick() {
		x += speed * direction;
		frames++;
		if (frames == 60 ) {
			Player.bullets.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillOval(x + 16, y + 16, width, height);
		
	}
	
}
