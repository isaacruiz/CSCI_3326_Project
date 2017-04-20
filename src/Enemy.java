import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends GameObject{

	Color color;
	int width = 40;
	int height = 40;
	int toggle = 0;
	boolean dynamicColor;
	int fireRate;
	Handler handler;
	protected boolean projGravity = true;
	int counter = 0;
	Random rand = new Random();
	
	public Enemy(float x, float y, Handler handler, Color color, boolean colorChange, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		dynamicColor = colorChange;
		
		fireRate = rand.nextInt(30) + 30;
		this.color = color;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		if (dynamicColor){
			toggleColor();
		}
		if (counter % fireRate == 0) {
			float vpx, vpy;
			vpx = rand.nextInt(20) - 10;
			vpy = -1 * (rand.nextInt(15) + 10);
			Projectile p = new Projectile(x, y, vpx, vpy, color, ObjectId.Projectile);
			handler.addObject(p);

		}
		toggle++;
		counter++;
	}
	
	protected void toggleColor(){
		if(toggle % 100 == 0){
			if(color == Color.green)
				color = Color.yellow;
			
			else
				color = Color.green;
		}
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.black);
		
		g.drawOval((int)x, (int)y - 20, width, height);
		g.drawRect((int)x, (int)y, width, height);
		
		g2d.setColor(color);
		g2d.fillOval((int)x, (int)y - 20, width, height);
		g2d.fillRect((int)x, (int)y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setFireRate(int fr){
		fireRate = fr;
	}
	
	public void toggleProjGrav(){
		projGravity = false;
	}

}
