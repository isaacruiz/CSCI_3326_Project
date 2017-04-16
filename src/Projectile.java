
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;



public class Projectile extends GameObject{

	float gravity = 0.4f;
	
	public Projectile(float x, float y, ObjectId id) {
		super(x, y, id);
		velX = 5;
		velY = -20;
		
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		y += velY;
		x+= velX;
		
		if (falling)
			velY += gravity;
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int)x, (int)y, 10, 10);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
