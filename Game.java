package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 7364682855700581664L;
	
	// declare width and height as final
	public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9;
	// game runs within this thread (single threaded)
	private Thread thread;
	private boolean running = false;
	
	
	// create instance of handler
	private Handler handler;
	
	private Random r;
	
	private HUD hud;
	
	// creates instance of game
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "NAME GOES HERE", this);
		
		hud = new HUD();
		
		r = new Random();
		
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
		
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));
		
	}

	// initializes thread and sets game to run
	public synchronized void start() {

		this.requestFocus();
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	// checks for and reports back errors, stops thread
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// game loop used to update itself
	public void run() {
		// request focus reads key input w/out needing to click on window
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop();
		
	}
	
	private void tick() {
		
		handler.tick();
		hud.tick();
		
	}
	
	// renders graphics
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		// draws a background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max ) {
			return var = max;
		} else if( var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	// main method
	public static void main(String args[]) {
		new Game();
	}

}
