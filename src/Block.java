

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Block extends GameObject{

	int width = 32;
	int height = 32;
	
	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}


	public void tick(LinkedList<GameObject> object) {
		
	}


	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect((int)x, (int)y, width, height);
		g.setColor(Color.black);
		g.drawLine((int) x,  (int) y,  (int)x + width, (int)y + height);
		g.drawLine((int) x,  (int) y + height,  (int)x + width, (int)y);
		g.drawRect((int)x, (int)y, width, height);
		g.setColor(Color.red);
		Graphics2D g2d = (Graphics2D)g;
		g2d.draw(getBounds());
	}
	
	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, width, height);
	}

}
