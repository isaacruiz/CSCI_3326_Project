
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;



public class Projectile extends GameObject{

	float gravity = 0.5f;
	int diameter = 20;
	
	public Projectile(float x, float y, float velX, float velY, ObjectId id) {
		super(x, y, id);
		this.velX = velX;
		this.velY = velY;
		
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

		y += velY;
		x += velX;
		
		if (falling)
			velY += gravity;
		
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.red);
		g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
		g2d.draw(getBounds());
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)(x), (int)(y), diameter, diameter);
	}
	
}
