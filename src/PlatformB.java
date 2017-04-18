import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public class PlatformB extends GameObject {

	protected Color color;
	protected int toggle;
	private float width;
	private float height;
	private float delX = 0;
	private float delY = 0;
	private float centerX;
	private float centerY;
	
	public PlatformB(float x, float y, float width, float height, Color color, ObjectId id) {
		super(x, y, id);
		this.color = color;
		this.width = width;
		this.height = height;
		centerX = x + width/2;
		centerY = y + width/2;
		velX = 0;
		velY = 0;
		toggle = 0;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if(x > centerX + delX || x < centerX - delX)
			velX *= -1;
		
		if(y > centerY + delY || y < centerY - delY)
			velY *= -1;
	
		
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
	public void setMovement(float vel, float radius, int option){
		
		//Make cases for left-right, up-down, diagonal, circular
		delX = delY = radius;
		
		switch(option){
		case 1:
			velX = vel;
			break;
			
		case 2:
			velY = vel;
			break;
			
		case 3:
			velX = velY = vel;
			break;
			
		default:
			
			break;
			
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