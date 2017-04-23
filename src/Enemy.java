import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends GameObject{

	
	int width = 40;
	int height = 40;
	int toggle = 0;
	int counter = 0;
	int fireRate;
	float vpx, vpy;
	protected boolean dynamicColor;
	protected boolean projGravity = true;
	
	Handler handler;
	Color color;
	Random rand = new Random();

	public Enemy(float x, float y, Handler handler, Color color, boolean colorChange, ObjectId id) {
		
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		dynamicColor = colorChange;
		fireRate = rand.nextInt(30) + 30;
	}

	public void tick(LinkedList<GameObject> object) {

		if (dynamicColor){
			toggleColor();
		}
		//Creates a projectile with a random trajectory and adds it to the handler
		if (counter % fireRate == 0) {
			
			//float vpx, vpy;
			vpx = rand.nextInt(20) - 10;
			vpy = -1 * (rand.nextInt(15) + 10);
			
			Projectile p = new Projectile(x + 12, y + 12, vpx, vpy, color, ObjectId.Projectile);
			handler.addObject(p);
		}
		toggle++;
		counter++;
	}
	
	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D)g;
//		
//		g2d.setColor(Color.black);
//		
//		g.drawOval((int)x, (int)y - 20, width, height);
//		g.drawRect((int)x, (int)y, width, height);
//		
//		g2d.setColor(color);
//		g2d.fillOval((int)x, (int)y - 20, width, height);
//		g2d.fillRect((int)x, (int)y, width, height);
		
		//g.drawImage(Texture.turret, (int)x, (int)y, null);
		Texture.drawRotatedImage(Texture.turret, x, y, Math.atan(vpy/vpx), g);
		
	}
	
	public Rectangle getBounds() {
		//Not needed for this class
		return null;
	}
	
	//Changes color of enemy periodically
	protected void toggleColor(){
		
		if(toggle % 120 == 0){
			
			if(color == Color.green)
				color = Color.yellow;
			
			else
				color = Color.green;
		}
	}
	
	public void toggleProjGrav(){
		projGravity = false;
	}

	public void setFireRate(int fr){
		fireRate = fr;
	}
}
