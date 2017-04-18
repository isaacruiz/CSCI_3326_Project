
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;



public class Handler {

	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	private Game game;
	public Handler(Game game){
		this.game = game;
	}
	public void tick(){
		for(int i = 0; i <object.size();i++){
			tempObject = object.get(i);
			tempObject.tick(object);
			if(tempObject.getId() == ObjectId.Player){
				
				if(((Player)tempObject).isDead())
					game.init();
			}
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i <object.size();i++){
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
		
	public void createLevel() {
		for(int i = 0; i < Game.WIDTH*15; i+=32)
			addObject(new Block (i, 400, ObjectId.Block));
		
		for(int i = 0; i < Game.WIDTH*15; i+= Game.WIDTH)
			addObject(new Block (i, 100, ObjectId.Block));
		
		addObject(new PlatformA(200, 300, 200, 32, Color.yellow, ObjectId.PlatformA));
		PlatformB tempPlat = new PlatformB(400, 300, 400, 32, Color.yellow, ObjectId.PlatformB);
		tempPlat.setMovement(2, 300, 1);
		addObject(tempPlat);
		
		PlatformB tempPlat2 = new PlatformB(800, 200, 400, 32, Color.yellow, ObjectId.PlatformB);
		tempPlat2.setMovement(2, 300, 2);
		addObject(tempPlat2);
	}
}
