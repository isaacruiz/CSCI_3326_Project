import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

 
public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -6261436164361361187L;
	
	private boolean running = false;
	private Thread thread;
	private BufferedImage background = null;
	private BufferedImage complete = null;
	
	public static int WIDTH,HEIGHT;
	
	//Object
	Handler handler;
	Camera camera;
	
	public void init(){
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		ImageLoader loader = new ImageLoader();
		background = loader.loadImage("/trees3.png");
		complete = loader.loadImage("/level_complete.png");
		
		//loadImageBackground(background);
		handler = new Handler(this);
		camera = new Camera(0,0);
		
		Level1 level1 = new Level1(handler);
		level1.createLevel();
		handler.addObject(new Player(4700, 400, handler, camera, ObjectId.Player));
		//handler.addObject(new Player(0, 0, handler, camera, ObjectId.Player));
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void run(){
		
		init(); //Initialize 
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
//		int updates = 0;
//		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
//				updates++;
				delta--;
			}
			render();
//			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
//				frames = 0;
//				updates = 0;
			}
		}
	}
	
	private void tick(){
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, this);
		
		/////////////////Draw here/////////////////
		//g.setColor(Color.black);
		//g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(camera.getX(), camera.getY());
		handler.render(g);
		g.drawImage(complete, 8200, -300, this);
		//g2d.translate(-camera.getX(), -camera.getY());
		/////////////////////////////////
		
		g.dispose();
		bs.show();
	}
	
//	private void loadImageBackground(BufferedImage image){
//		int w = image.getWidth();
//		int h = image.getHeight();
//		//System.out.println(w + " " + h);
//	}
	
	public static void main(String args[]){
		new Window(1066,600,"Java Final Project", new Game());
	}
	
}
