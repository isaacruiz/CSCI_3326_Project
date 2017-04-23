import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
//import java.awt.Graphics2D;

public class Platform extends GameObject {

	protected int toggle;
	private float width;
	private float height;
	private float delX = 0;
	private float delY = 0;
	private float centerX;
	private float centerY;
//	private float initX; //Not needed unless drawing movwment boundaries
//	private float initY;
	public float lbMove;
	public float rbMove;
	public float ubMove;
	private float bbMove;
	public boolean dynamicColor = false;
	protected Color color;
	
	public Platform(float x, float y, float width, float height, Color color, ObjectId id) {
		super(x, y, id);
//		initX = x;
//		initY = y;
		this.color = color;
		this.width = width;
		this.height = height;
		centerX = x + width/2;
		centerY = y + height/2;
		velX = 0;
		velY = 0;
		toggle = 0;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if(x + width > rbMove || x < lbMove)
			velX *= -1;
		
		if(y + height > ubMove || y < bbMove)
			velY *= -1;
		
		if(dynamicColor){
			toggle++;
			toggleColor();
		}
	}

	public void render(Graphics g) {
//		g.setColor(color);
//		g.fillRect((int) x, (int) y, (int)width, (int)height);
//		
//		g.setColor(Color.black);
//		g.drawRect((int)x, (int) y, (int)width, (int)height);
		g.drawImage(Texture.greenPlat, (int)x, (int)y, (int)width, (int)height, null);
		
//		-----------------------Platform movement boundaries------------------------
//		g.setColor(Color.red);
//		Graphics2D g2d = (Graphics2D)g;
//		
//		//draw initial spawn
//		g2d.drawRect((int)initX, (int)initY, (int)width, (int)height);
//		
//		//draw center
//		g2d.drawLine((int)centerX-5, (int)centerY, (int)centerX + 5, (int)centerY);
//		g2d.drawLine((int)centerX, (int)centerY-5, (int)centerX, (int)centerY + 5);
//		
//		//Draw bounds (up-down movement)
//		g2d.drawLine((int)initX, (int)(ubMove), (int)(initX + width), (int)(ubMove));
//		g2d.drawLine((int)initX, (int)(bbMove), (int)(initX + width), (int)(bbMove));
//		
//		//Draw bounds (left-right movement)
//		g2d.drawLine((int)(lbMove), (int)(initY - delX/2), (int)(lbMove), (int)(initY + delX/2));
//		g2d.drawLine((int)(rbMove), (int)(initY - delX/2), (int)(rbMove), (int)(initY + delX/2));
	}

	public void toggleColor(){
		if (toggle % 60 == 0){
			
			if(color == Color.green)
				color = Color.yellow;
			else
				color = Color.green;
		}	
	}
	
	public void setDynamicColor(boolean c){
		dynamicColor = c;
	}
	
	public void setColor(Color c){
		color = c;
	}
	public void setMovement(float vel, float radius, int option){
		
		//Make cases for left-right, up-down, diagonal, circular
		delX = delY = radius;
		lbMove = centerX - width/2 - delX;
		rbMove = centerX + width/2 + delX;
		ubMove = centerY + height/2 + delY;
		bbMove = centerY - height/2 - delY;
		
		switch(option){
		case 1:
			velX = vel;
			break;
			
		case 2:
			velY = vel;
			break;
			
		default:
			velX = velY = vel;			
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
	
	public void setPosition(float xx, float yy){
		
		x += xx;
		y += yy;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int)width, (int)height);
	}

}