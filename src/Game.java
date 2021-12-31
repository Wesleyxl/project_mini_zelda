import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// variables
	public static int SCALE = 1;
	public static int WIDTH = 640, HEIGHT = 480;
	
	// imports
	public static Player player;
	public World world;
	public List<Enemy> enemies = new ArrayList<Enemy>(); 
	
	public Game() {
		
		// key listener
		this.addKeyListener(this);
		
		// screen dimension
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		new SpriteSheet();
		
		player = new Player(32, 32);
	
		world = new World();
		
		enemies.add(new Enemy(32, 32));
		enemies.add(new Enemy(36, 50));
		
	}
	
	// logic 
	public void tick() {
		player.tick();
		
		for (int i = 0; i < enemies.size(); i ++) {
			enemies.get(i).tick();
		}
	}
	
	// render
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		} 
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0, 135, 13));
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		
		player.render(g);
		for (int i = 0; i < enemies.size(); i ++) {
			enemies.get(i).render(g);
		}
		world.render(g);
		
		bs.show();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		// set frame configurations
		frame.add(game);
		frame.setTitle("Mini Zelda Clone");
		frame.setVisible(true);
		frame.pack();
		// set screen to center
		frame.setLocationRelativeTo(null);
		// close the java process on close 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		// looping
		new Thread(game).start();
		
	}
	
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		while (true) {
			
			tick();
			render();
			
			try {
				Thread.sleep(1000 / 60);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		// moving player left and right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.RIGHT = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.LEFT = true;
		}

		// bullet
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.shot = true;
		}
		
		// moving player to up and down
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.UP = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.DOWN = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		// stop moving player left and right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.RIGHT = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.LEFT = false;
		}

		// stop moving player to up and down
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.UP = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.DOWN = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
