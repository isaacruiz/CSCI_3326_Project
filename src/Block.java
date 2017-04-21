

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.LinkedList;


public class Block extends GameObject{

	//Static dimensions so all classes can access dimensions
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		//Not needed for this class
	}

	//Draws a gray block of dimension width x height and 
	//a cross going from coner to corner of the block
	public void render(Graphics g) {
//		g.setColor(Color.gray);
//		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
//		g.setColor(Color.black);
//		g.drawLine((int) x,  (int) y,  (int)x + WIDTH, (int)y + HEIGHT);
//		g.drawLine((int) x,  (int) y + HEIGHT,  (int)x + WIDTH, (int)y);
//		g.drawRect((int)x, (int)y, WIDTH, HEIGHT);
		g.drawImage(Texture.block, (int)x, (int)y, WIDTH, HEIGHT, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}
}
