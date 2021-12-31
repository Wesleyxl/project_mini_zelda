import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public static BufferedImage spriteSheet;
	public static BufferedImage[] player_front;
	public static BufferedImage[] enemy_front;
	public static BufferedImage tileWall;

	public SpriteSheet() {
		
		try {
			spriteSheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//spriteSheet player
		player_front = new BufferedImage[2];
		player_front[0] = SpriteSheet.getSprite(0, 11, 16, 16);
		player_front[1] = SpriteSheet.getSprite(16, 11, 16, 16);
		
		//spriteSheet enemy
		enemy_front = new BufferedImage[2];
		enemy_front[0] = SpriteSheet.getSprite(165, 11, 16, 16);
		enemy_front[1] = SpriteSheet.getSprite(184, 11, 16, 16);

		tileWall = SpriteSheet.getSprite(280, 202, 16, 16);
		
	}
	
	// get subImages
	public static BufferedImage getSprite(int x, int y, int width, int height) {

		return spriteSheet.getSubimage(x, y, width, height);

	}
	
}
