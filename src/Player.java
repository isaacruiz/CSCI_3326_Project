

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject {

	private float width = 48, height = 48;
	protected Color color = Color.green;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	
	private Handler handler;
	
	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(falling || jumping){
			velY += gravity;
			
			if(velY > MAX_SPEED){
				velY = MAX_SPEED;
			}
		}
		
		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block) {

				// Bottom Collision
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else
					falling = true;

				// Top Collision
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}

				// Left Collision
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
				}

				// Right Collision
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
			}

			if (tempObject.getId() == ObjectId.PlatformA) {

				PlatformA plat = (PlatformA) tempObject;

				if (color.equals(plat.getColor())) {

					// Bottom Collision
					if (getBounds().intersects(tempObject.getBounds())) {
						y = tempObject.getY() - height;
						velY = 0;
						falling = false;
						jumping = false;
					} else
						falling = true;

					// Top Collision
					if (getBoundsTop().intersects(tempObject.getBounds())) {
						y = tempObject.getY() + 32;
						velY = 0;
					}

					// Left Collision
					if (getBoundsLeft().intersects(tempObject.getBounds())) {
						x = tempObject.getX() + 32;
					}

					// Right Collision
					if (getBoundsRight().intersects(tempObject.getBounds())) {
						x = tempObject.getX() - width;
					}
				}
			}

		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		//g2d.draw(getBounds());
		//g2d.draw(getBoundsRight());
		//g2d.draw(getBoundsLeft());
		//g2d.draw(getBoundsTop());
		
	}
	
	public void toggleColor(){
		if (color == Color.green)
			color = Color.yellow;
		else
			color = Color.green;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)(x+(width/2)-((width/2)/2)), (int)((int)y+(height/2)), (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)((int)x+(width/2)-((width/2)/2)), (int)y, (int)width/2, (int)height/2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	
	

}
