import java.awt.Color;
import java.util.Random;

public class Level1 {

	Handler handler;
	Enemy e; //temp instances to modify objects before adding to handler
	Platform p;
	Random r;
	
	public Level1(Handler handler){
		this.handler = handler;
	}
	public void createLevel() {
		
/*		Constructor Arguments:
		
		Block - posX, posY, ObjectId
		Enemy - posX, posY, handler, color (1->yellow, 2->green, 3->red), color-changing(T/F), ObjectID
		PlatformA - posX, posY, width, height, color, ObjectId
		PlatformB - posX, posY, width, height, color, ObjectId
		Platform - posX, posY, width, height, color, ObjectId	
*/
		int xx = 0;		//Keep track of position in game with xx and yy and make objects
		int yy = 0;		//located relative to each other
		float platVel = 3;

		
//		----------------------------First section of level----------------------------
		//Introduction
		yy += 600;
		addFloor(xx, yy, 1200);
		
//		----------------------------Second section of level----------------------------
		//Up-down and diagonal platforms
		xx += 1600;
		
		e = new Enemy(xx, yy - 200, handler, Color.yellow, true, ObjectId.Enemy);
		handler.addObject(e);
		
		
		//Up-down platform
		xx -= 384;
		p = new Platform(xx, yy - 230, 200, 32, Color.gray, ObjectId.Platform);
		p.setMovement(platVel, 234, 2);
		p.setPosition(0, -200);
		handler.addObject(p);
		
		xx += 216;
		addFloor(xx, 132, 128);
		
		//Diagonal Platform
		xx += 360;
		p = new Platform(xx, yy - 230, 180, 32, Color.gray, ObjectId.Platform);
		p.setMovement(platVel, 234, 3);
		handler.addObject(p);
		
//		----------------------------Third section of level----------------------------
//		Left-Right moving platforms		
		xx += 416;
		addFloor(xx, yy, 160);
		xx = 2600;
		
		p = new Platform(xx, yy, 100, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, 150, 1);
		p.setPosition(-150, 0);
		handler.addObject(p);
		
		e = new Enemy(xx + 30, yy + 200, handler, Color.green, false, ObjectId.Enemy);
		handler.addObject(e);
		
		xx += 500;
		p = new Platform(xx, yy, 100, 32, Color.yellow, ObjectId.Platform);
		p.setPosition(150, 0);
		p.setMovement(3, 150, 1);
		
		handler.addObject(p);
		
		xx += 500;
		p = new Platform(xx, yy, 100, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, 150, 1);
		p.setPosition(-150, 0);
		handler.addObject(p);
		
		e = new Enemy(xx + 30, yy + 200, handler, Color.green, false, ObjectId.Enemy);
		handler.addObject(e);
		
		xx += 500;
		p = new Platform(xx, yy, 100, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, 150, 1);
		p.setPosition(150, 0);
		handler.addObject(p);
//		----------------------------Fourth section of level----------------------------
		//Rotating platforms
		xx = 4500;
		addFloor(xx, yy - 50, 300);

		xx += 600;
		e = new EnemyB(xx, yy - 50, handler, Color.green, true, ObjectId.Enemy);
		e.toggleProjGrav();
		handler.addObject(e);
		
		p = new Platform(xx, yy - 50, 150, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(150, 150);
		p.setVelX(platVel);
		p.setVelY(-platVel);
		handler.addObject(p);
		
		p = new Platform(xx, yy - 50, 150, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(-150, -150);
		p.setVelX(-platVel);
		p.setVelY(platVel);
		handler.addObject(p);
		
		p = new Platform(xx, yy - 50, 150, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(-150, 150);
		p.setVelX(platVel);
		p.setVelY(platVel);
		handler.addObject(p);
		
		p = new Platform(xx, yy - 50, 150, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(150, -150);
		p.setVelX(-platVel);
		p.setVelY(-platVel);
		handler.addObject(p);
		
//		----------------------------Fifth section of level----------------------------
		//Sequential small up-down platforms
		xx = 5600;
		
		addFloor(xx, yy - 30, 300);
		
		r = new Random();
		
		xx += 400;
		platVel = 5;
		int moveRad = 200;
		for(int i = xx; i < 7200; i += 200){
			p = new Platform(i, yy - 30, 50, 32, Color.yellow, ObjectId.Platform);
			
			if(i/200 % 2 == 0)
				p.setColor(Color.green);
			
			p.setMovement(platVel, moveRad, 2);
			p.setPosition(0, r.nextInt(moveRad) - moveRad/2);
			handler.addObject(p);
		}
//		----------------------------Last section of level----------------------------
		//Color changing stair-case and complete floor
		xx = 7200;
		yy = 400;
		for(int i = 0; i < 5; i++){
			p = new Platform(xx, yy, 100, 32, Color.green, ObjectId.Platform);
			
			if(i % 2 == 0)
				p.setColor(Color.yellow);
			
			p.setDynamicColor(true);
			handler.addObject(p);
			xx += 200;
			yy -= 100;
		}
		xx = 8200;
		addFloor(xx, yy, 500);
//		---------------------------------End of level----------------------------------
	}
	
	//Method to add floors
	public  void addFloor(int x, int y, int len){
		for(int i = x; i < x + len; i += Block.WIDTH){
			handler.addObject(new Block(i, y, ObjectId.Block));
			handler.addObject(new Block(i, y + Block.HEIGHT, ObjectId.Block));
		}
	}
}
