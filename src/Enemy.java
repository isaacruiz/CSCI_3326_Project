import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

public class Enemy extends GameObject{

	Projectile projectile;
	Color color;
	int width = 40;
	int height = 40;
	int toggle = 0;
	boolean dynamicColor;
	int fireRate;
	Handler handler;
	int counter = 0;
	Random rand = new Random();
	
	public Enemy(float x, float y, Handler handler, int initColor, boolean colorChange, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		dynamicColor = colorChange;
		
		fireRate = rand.nextInt(10) + 60;
		
		switch(initColor){
		
		case 1:
			color = Color.yellow;
			break;
		
		case 2:
			color = Color.green;
			break;
		
		default:
			color = Color.red;
			break;
		}
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		int vpx = rand.nextInt(41) - 20;
		int vpy = -1 * (rand.nextInt(10) + 7);
		
		
		
		if(dynamicColor){
			toggleColor();
			toggle++;
		
			if(counter % fireRate == 0)
				handler.addObject(new Projectile(x,y, vpx, vpy, color, ObjectId.Projectile));
			counter++;
		}
	}
	private void toggleColor(){
		if(toggle % 80 == 0){
			if(color == Color.green)
				color = Color.yellow;
			
			else
				color = Color.green;
		}
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		g2d.fillOval((int)x, (int)y - 20, width, height);

		g2d.fillRect((int)x, (int)y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
