import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {
	
	public static List<Blocks> blocks = new ArrayList<Blocks>();
 
	public World() {
		
		for (int x = 0; x < 15; x ++) {
			blocks.add(new Blocks(x * 32, 0));
		}
		
		for (int x = 0; x < 15; x ++) {
			blocks.add(new Blocks(x * 32, 480 - 32));
		}
		
		for (int y = 0; y < 15; y ++) {
			blocks.add(new Blocks(0, y * 31));
		}
		
		for (int y = 0; y < 15; y ++) {
			blocks.add(new Blocks(480 - 32, y * 32));
		}
		
	}
	
	// Collisions
	public static boolean isFree(int x, int y) {
		
		for (int i = 0; i < blocks.size(); i++) {
			Blocks currentBlocks = blocks.get(i);
			if (currentBlocks.intersects(new Rectangle(x, y, 32, 32))) {
				return false;
			}
		}
		return true;
		
	}
	
	public void render(Graphics g) {

		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).render(g);
		}
		
	}
	
}
