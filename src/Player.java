

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject {

	private float width = 48;
	private float height = 48;
	private float gravity = 0.5f;
	private int health = 200;
	private final float MAX_SPEED = 10;
	private boolean moveLeft;
	private boolean moveRight;
	private float moveSpeed = 8;
	private boolean dead = false;
	private Color color = Color.green;
	private Camera camera;
	private Handler handler;
	
	public Player(float x, float y, Handler handler, Camera camera, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		moveLeft = false;
		moveRight = false;
		this.camera = camera;	
	}

	public boolean isDead(){
		return dead;
	}
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if (y > 1000)
			dead = true;
		
		if(falling || jumping){
			velY += gravity;
			
			if(velY > MAX_SPEED){
				velY = MAX_SPEED;
			}
		}
		Collision(object);
		camera.tick(this);
	}

	public boolean isMoveLeft(){
		return moveLeft;
	}
	
	public boolean isMoveRight(){
		return moveRight;
	}
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
		if(moveLeft)
			velX = -1 * moveSpeed;
		
		else
			velX = 0;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
		if(moveRight)
			velX = moveSpeed;
		else
			velX = 0;
	}

	private void Collision(LinkedList<GameObject> object) {
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block)
				collisionBlock(tempObject);

			//Contact with static platform
			if (tempObject.getId() == ObjectId.Platform)
				collisionPlatform(tempObject);
		
			
			if(tempObject.getId() == ObjectId.Projectile || tempObject.getId() == ObjectId.HomingMissle)
				collisionProjectile(tempObject);
		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
		//Draw collision boxes
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBoundsBottom());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBounds());		
	}
	
	public void toggleColor(){
		if (color == Color.green)
			color = Color.yellow;
		else
			color = Color.green;
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int)(x + width/4), (int)(y + height - 5), (int)width/2, (int)height/8);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)(x + width/4), (int)y, (int)width/2, (int)height/8);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)(x + width - 5), (int)y + 5, (int)5, (int)height - 10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)(width), (int)(height));
		
	}
	
	private void collisionBlock(GameObject tempObject){
		
		//Bottom Collision
		if (getBoundsBottom().intersects(tempObject.getBounds())) {
			y = tempObject.getY() - height;
			velY = 0;
			falling = false;
			jumping = false;
		} else
			falling = true;
			
		// Top Collision
		if (getBoundsTop().intersects(tempObject.getBounds())) {
			y = tempObject.getY() + Block.HEIGHT;
			velY = 0;
		}

		// Left Collision
		if (getBoundsLeft().intersects(tempObject.getBounds())) {
			x = tempObject.getX() + Block.WIDTH;
		}

		// Right Collision
		if (getBoundsRight().intersects(tempObject.getBounds())) {
			x = tempObject.getX() - width;
		}
	}
	
	private void collisionPlatform(GameObject tempObject){
		Platform plat = (Platform) tempObject;

		//If color of player equals color of platform, no collision is detected and player
		//passes through platform
		if (!color.equals(plat.getColor())) {

			// Bottom Collision	
			if (getBoundsBottom().intersects(tempObject.getBounds())) {
				y = tempObject.getY() - height;
				if(moveRight){
					velX = plat.velX + moveSpeed;
				}
				else if(moveLeft){
					velX = plat.velX - moveSpeed;
				}
				else
					velX = plat.velX;
				
				falling = false;
				jumping = false;
				
			} else
				falling = true;

			// Top Collision
			if (getBoundsTop().intersects(tempObject.getBounds())) {
				y = tempObject.getY() + plat.getHeight();
				velY = 0;
			}

			// Left Collision
			if (getBoundsLeft().intersects(tempObject.getBounds())) {
				x = tempObject.getX() + plat.getWidth();
			}

			// Right Collision
			if (getBoundsRight().intersects(tempObject.getBounds())) {
				x = tempObject.getX() - width;
			}
		}
	}
	private void collisionProjectile(GameObject tempObject){

		//Collision with normal projectile
		if (tempObject.getId() == ObjectId.Projectile) {

			Projectile proj = (Projectile) tempObject;
			// Registers collision if projectile color is red or matches player
			if ((proj.getColor() == Color.red || !(color.equals(proj.getColor()))) && getBounds().intersects(proj.getBounds())) {
				health -= 25;
				handler.removeObject(tempObject);
			}
		}
		//Collision with homing missile
		else {
			HomingMissle h = (HomingMissle) tempObject;
			if (getBounds().intersects(h.getBounds()))
				health -= 25;
		}
		if (health == 0)
			dead = true;
	}
	public int getHealth(){
		return health;
	}
}
