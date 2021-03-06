import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
 
public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -6261436164361361187L;
	public static int WIDTH,HEIGHT;
	private boolean running = false;
	
	private Thread thread;
	
	
	Handler handler;
	Camera camera;
	Player player;
	HealthBar healthBar;
	Level1 level1;
	Texture texture = new Texture();
	
	
	public void init(){
		
		WIDTH =  getWidth();
		HEIGHT = getHeight();
		
		handler = new Handler(this);
		camera = new Camera();
		level1 = new Level1(handler);
		
		level1.createLevel();
		
		player = new Player(0, 500, handler, camera, ObjectId.Player);
		healthBar = new HealthBar(player);
		
		//Add players and homing projectile to the beginning of the level
		handler.addObject(player);
		handler.addObject(new HomingMissle(-200, 500, player, ObjectId.HomingMissle));
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
		
		init(); //Initialize the game
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
//		long timer = System.currentTimeMillis();
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
//					
//			if(System.currentTimeMillis() - timer > 1000){
//				timer += 1000;
//				System.out.println("FPS: " + frames + " TICKS: " + updates);
//				frames = 0;
//				updates = 0;
//			}
		}
	}
	
	//Updates game objects and health bar
	private void tick(){		
		handler.tick();
		healthBar.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
	
		//******************************************Draw here******************************************	
	
		//Draw images
		g.drawImage(Texture.background, 0, 0, Game.WIDTH, Game.HEIGHT, this);
		g.drawImage(Texture.health, 10, 35, (int)(Texture.health.getWidth()*0.4), (int)(Texture.health.getHeight() * 0.4), this);
		healthBar.render(g);
		
		//Moves object in reference to camera position
		g2d.translate(camera.getX(), camera.getY());
		
		//Draw images
		g.drawImage(Texture.startmsg, -400, 400, (int)(0.5 * Texture.startmsg.getWidth()), (int)(0.5 * Texture.startmsg.getHeight()), this);
		g.drawImage(Texture.instruction, 200, 400, (int)(0.75 * Texture.instruction.getWidth()), (int)(0.75 * Texture.instruction.getHeight()), this);
		g.drawImage(Texture.complete, 8200, -300, this);
		
		//Draw game objects
		handler.render(g);
	
		g2d.translate(-camera.getX(), -camera.getY());
		
		//******************************************Draw here******************************************	
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]){
		new Window(1280, 720,"Java Final Project", new Game());
	}
	
}
