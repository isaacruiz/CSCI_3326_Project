
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;



public class Handler {

	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick(){
		for(int i = 0; i <object.size();i++){
			tempObject = object.get(i);
			
			tempObject.tick(object);
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
		addObject(new Block(0, 200, ObjectId.Block));

		addObject(new Block(100, Game.HEIGHT - 32, ObjectId.Block));

		for (int i = 0; i < 1000; i+=100){
			addObject(new Block(i, 600, ObjectId.Block));
		}
		addObject(new Block(100, 300, ObjectId.Block));

		addObject(new Block(150, 300, ObjectId.Block));
		
		addObject(new PlatformA(400, 400, Color.yellow, ObjectId.PlatformA));
		
		addObject(new PlatformA(600, 400, Color.green, ObjectId.PlatformA));
		
		addObject(new Block(400, 500, ObjectId.Block));
	}
}
