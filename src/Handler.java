
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
			
			
			if(tempObject.getId() == ObjectId.Player){
				if(((Player)tempObject).isDead())
					game.init();
			}
			
			if(tempObject.getId() == ObjectId.Projectile){
				if(((Projectile)tempObject).getY() > 1000)
					removeObject(tempObject);
				
				if(((Projectile)tempObject).getY() < -1000)
					removeObject(tempObject);
			}
			
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
		
}
