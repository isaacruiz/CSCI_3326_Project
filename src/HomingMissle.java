import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.lang.Math;

public class HomingMissle extends Projectile{
	
	private Player player;
	private Color color = Color.yellow;
	private float velocity = 3f;
	int toggle = 0;
	
	HomingMissle(float x, float y, Player p, ObjectId id){
		super(x, y, id);
		player = p;
		diameter = 30;
	}

	//Changes color while following player across the map
	public void tick(LinkedList<GameObject> object) {		
		calcVelX();
		calcVelY();
		toggleColor();
		x += velX;
		y += velY;
		toggle++;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int)x,(int) y, (int)diameter, (int)diameter);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)(x), (int)(y), diameter, diameter);
	}
	
	private void toggleColor(){
		if(toggle % 5 == 0){
		if(color == Color.green)
			color = Color.yellow;
		else
			color = Color.green;
		}
	}
	
	//calcVelX and calcVelY calculate the x and y componenets of velocity depending on where
	//the player is relative to the missle
	private void calcVelX(){
		double theta;
		theta = Math.atan((player.getY() - y)/(player.getX() - x));
		velX = velocity * (float)Math.cos(theta);
		
		if(player.getX() < x)
			velX *= -1;
	}
	
	private void calcVelY(){
		double theta;
		theta = Math.atan((player.getY() - y)/(player.getX() - x));
		velY = velocity * (float)Math.sin(theta);
		
		if(player.getX() < x)
			velY *= -1;
	}
}
