
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;



public class Projectile extends GameObject{

	float gravity = 0.5f;
	protected int diameter = 20;
	protected Color color;
	
	public Projectile(float x, float y, float velX, float velY, Color color, ObjectId id) {
		super(x, y, id);
		this.velX = velX;
		this.velY = velY;
		this.color = color;
	}
	
	public Projectile(float x, float y, ObjectId id) {
		super(x, y, id);
		velX = 0;
		velY = 0;
		}
	
	public void tick(LinkedList<GameObject> object) {
		y += velY;
		x += velX;
		
		if (falling)
			velY += gravity;
	}

	public void render(Graphics g) {
		
//		g.setColor(color);
//		g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
		if(color == Color.green){
		g.drawImage(Texture.greenProj,  (int)x, (int)y, null);
		}
		else if (color == Color.yellow){
			g.drawImage(Texture.yellowProj, (int)x, (int)y, null);
		}
//		g.setColor(Color.red);
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.draw(getBounds());
	}

	public Rectangle getBounds() {
		return new Rectangle((int)(x), (int)(y), diameter, diameter);
	}
	
	public Color getColor(){
		
		return color;
	}
	
	public float getY(){
		
		return y;
	}
	
}
