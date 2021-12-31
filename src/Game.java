import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	// variables
	public static int SCALE = 1;
	public static int WIDTH = 480, HEIGHT = 480;
	
	// importa
	public Player player;
	public World world;
	
	public Game() {
		
		// key listener
		this.addKeyListener(this);
		
		// screen dimension
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		player = new Player(32, 32);
		world = new World();
		
	}
	
	// logic 
	public void tick() {
		player.tick();
	}
	
	// render
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		} 
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		world.render(g);
		
		bs.show();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		// set frame configurations
		frame.add(game);
		frame.setTitle("Mini Zelda Clone");
		frame.pack();
		// set screen to center
		frame.setLocationRelativeTo(null);
		// close the java process on close 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);
		
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

		// moving player to up and down
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
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
