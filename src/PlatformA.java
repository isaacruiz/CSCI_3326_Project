

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class PlatformA extends GameObject {

	protected Color color;
	protected int toggle;
	private float width;
	private float height;
	
	public PlatformA(float x, float y, float width, float height, Color color, ObjectId id) {
		super(x, y, id);
		this.color = color;
		this.width = width;
		this.height = height;
		toggle = 0;
	}

	public void tick(LinkedList<GameObject> object) {
		toggle++;
		toggleColor();
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, (int)width, (int)height);

	}

	public void toggleColor(){
		if (toggle % 60 == 0){
			
			if(color == Color.green)
				color = Color.yellow;
			else
				color = Color.green;
		}
		
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	public Color getColor(){
		return color;
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, (int)width, (int)height);
	}

}

