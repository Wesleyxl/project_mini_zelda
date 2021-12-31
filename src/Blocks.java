import java.awt.Graphics;
import java.awt.Rectangle;

public class Blocks extends Rectangle {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Blocks(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void render(Graphics g) {
		
		g.drawImage(SpriteSheet.tileWall, x, y, 32, 32, null);

	}
	
}
