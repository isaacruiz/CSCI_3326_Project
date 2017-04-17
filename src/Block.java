

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class Block extends GameObject{

	int width = 100;
	int height = 16;
	
	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}


	public void tick(LinkedList<GameObject> object) {
		
	}


	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect((int)x, (int)y, width, height);
		
	}


	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, width, height);
	}

}
