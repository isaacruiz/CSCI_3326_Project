import java.awt.Color;
import java.util.Random;

public class Level1 {

	Handler handler;
	
	public Level1(Handler handler){
		this.handler = handler;
	}
	public void createLevel() {
		
		/*Constructor Arguments:
		
		Block - posX, posY, ObjectId
		Enemy - posX, posY, handler, color (1->yellow, 2->green, 3->red), color-changing(T/F), ObjectID
		PlatformA - posX, posY, width, height, color, ObjectId
		PlatformB - posX, posY, width, height, color, ObjectId
		Platform - posX, posY, width, height, color, ObjectId
		
		
		*/
		Enemy e;
		int xx = 0;
		PlatformA a;
		PlatformB b;
		Platform p;
		float platVel = 3;
		
		//First fifth of the level x = 0 -> x = 1200
		addFloor(xx, 600, 1200);
		
		e = new Enemy(600, 700, handler, Color.green, true, ObjectId.Enemy);
		handler.addObject(e);
		
		e = new Enemy(1000, 400, handler, Color.yellow, true, ObjectId.Enemy);
		handler.addObject(e);
		
		e = new Enemy(1600, 400, handler, Color.red, false, ObjectId.Enemy);
		handler.addObject(e);
		
		//Second fifth of the level x = 1200 -> x = 2400
		xx = 1200;
		
		//Up-down platform
		p = new Platform(xx + 16, 370, 200, 32, Color.gray, ObjectId.Platform);
		p.setMovement(platVel, 234, 2);
		//p.setDynamicColor(true);
		handler.addObject(p);
		
		xx += 16 + 200;
		addFloor(xx, 132, 128);
	
		
		xx += 360;
		
		//Diagonal Platform
		p = new Platform(xx, 370, 180, 32, Color.gray, ObjectId.Platform);
		p.setMovement(platVel, 234, 3);
		handler.addObject(p);
		
		xx += 416;
		
		addFloor(xx, 600, 160);
		

		//Third fifth of the level x = 2400 -> x = 3600
		xx = 2600;
		p = new Platform(xx, 600, 100, 32, Color.gray, ObjectId.Platform);
		p.setMovement(platVel, 150, 1);
		p.setPosition(-150, 0);
		handler.addObject(p);
		
		e = new Enemy(xx + 30, 700, handler, Color.red, false, ObjectId.Enemy);
		handler.addObject(e);
		
		xx += 500;
		p = new Platform(xx, 600, 100, 32, Color.gray, ObjectId.Platform);
		p.setPosition(150, 0);
		p.setMovement(3, 150, 1);
		
		handler.addObject(p);
		
		xx += 500;
		p = new Platform(xx, 600, 100, 32, Color.gray, ObjectId.Platform);
		p.setMovement(platVel, 150, 1);
		p.setPosition(-150, 0);
		handler.addObject(p);
		
		e = new Enemy(xx + 30, 700, handler, Color.red, false, ObjectId.Enemy);
		handler.addObject(e);
		
		xx += 500;
		p = new Platform(xx, 600, 100, 32, Color.gray, ObjectId.Platform);
		p.setMovement(platVel, 150, 1);
		p.setPosition(150, 0);
		handler.addObject(p);
		
		
		
		//Fourth fifth of the level x = 3600 -> x = 4800\
		xx = 4500;
		addFloor(xx, 550, 300);
	
		
		xx += 600;
		
		e = new EnemyB(xx + 50, 550, handler, Color.red, false, ObjectId.Enemy);
		e.toggleProjGrav();
		handler.addObject(e);
		
		p = new Platform(xx, 550, 150, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(150, 150);
		p.setVelX(platVel);
		p.setVelY(-platVel);
		handler.addObject(p);
		
		p = new Platform(xx, 550, 150, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(-150, -150);
		p.setVelX(-platVel);
		p.setVelY(platVel);
		handler.addObject(p);
		
		p = new Platform(xx, 550, 150, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(-150, 150);
		p.setVelX(platVel);
		p.setVelY(platVel);
		handler.addObject(p);
//		
		p = new Platform(xx, 550, 150, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, 250, 4);
		p.setPosition(150, -150);
		p.setVelX(-platVel);
		p.setVelY(-platVel);
		handler.addObject(p);
		
		//Last fifth of the level x = 4800 -> x = 6000
		xx = 5600;
		
		addFloor(xx, 570, 300);
		
		Random r = new Random();
		
		xx += 400;
		platVel = 3;
		int moveRad = 200;
		p = new Platform(xx, 570, 50, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, moveRad, 2);
		p.setPosition(0, r.nextInt(moveRad) - moveRad/2);
		handler.addObject(p);
		
		xx += 200;
		p = new Platform(xx, 570, 50, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, moveRad, 2);
		p.setPosition(0, r.nextInt(moveRad) - moveRad/2);
		p.setVelY(-p.getVelY());
		handler.addObject(p);
		
		xx += 200;
		p = new Platform(xx, 570, 50, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, moveRad, 2);
		p.setPosition(0, r.nextInt(moveRad) - moveRad/2);
		handler.addObject(p);
		
		xx += 200;
		p = new Platform(xx, 570, 50, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, moveRad, 2);
		p.setPosition(0, r.nextInt(moveRad) - moveRad/2);
		p.setVelY(-p.getVelY());
		handler.addObject(p);
		
		xx += 200;
		p = new Platform(xx, 570, 50, 32, Color.yellow, ObjectId.Platform);
		p.setMovement(platVel, moveRad, 2);
		p.setPosition(0, r.nextInt(moveRad) - moveRad/2);
		handler.addObject(p);
		
		xx += 200;
		p = new Platform(xx, 570, 50, 32, Color.green, ObjectId.Platform);
		p.setMovement(platVel, moveRad, 2);
		p.setPosition(0, r.nextInt(moveRad) - moveRad/2);
		p.setVelY(-p.getVelY());
		handler.addObject(p);
		
		xx = 7200;
		int yy = 400;
		
		for(int i = 0; i < 5; i++){
			p = new Platform(xx, yy, 100, 32, Color.gray, ObjectId.Platform);
			handler.addObject(p);
			xx += 200;
			yy -= 100;
		}
		
		xx = 8200;
		addFloor(xx, yy, 500);
	}
	
	public  void addFloor(int x, int y, int len){
		for(int i = x; i < x + len; i += Block.width){
			handler.addObject(new Block(i, y, ObjectId.Block));
		}
	}
}
