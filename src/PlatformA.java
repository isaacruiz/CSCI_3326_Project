

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class PlatformA extends GameObject {

	protected Color color = Color.blue;
	protected int toggle = 0;
	private int width = 100;
	private int height = 32;
	
	public PlatformA(float x, float y, Color color, ObjectId id) {
		super(x, y, id);
		this.color = color;
	}

	public void tick(LinkedList<GameObject> object) {
		toggle++;
		toggleColor();
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

	}

	public void toggleColor(){
		if (toggle % 60 == 0){
			
			if(color == Color.green)
				color = Color.yellow;
			else
				color = Color.green;
		}
		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	public Color getColor(){
		return color;
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int) x, (int) y, 100, 32);
	}

}

