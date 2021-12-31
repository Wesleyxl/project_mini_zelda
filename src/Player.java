import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

	public int speed = 4;
	public boolean RIGHT, UP, DOWN, LEFT;
	
	public Player(int x, int y) {
		super(x,y, 32, 32);
	}
	
	public void tick() {
		
		if (RIGHT && World.isFree(x + speed, y)) {
			x += speed;
		} else if (LEFT && World.isFree(x - speed, y)) {
			x -= speed;
		}
		
		if (UP  && World.isFree(x, y - speed)) {
			y -= speed;
		} else if (DOWN && World.isFree(x, y + speed)) {
			y += speed;
		}
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		
	}
	
}
