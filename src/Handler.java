import java.awt.Graphics;
import java.util.LinkedList;

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
			
			//Special Update conditions for players and projectiles, restarting the game if
			//the player is dead and deleting projectiles if they move outside of the playable area
	
			if(tempObject.getId() == ObjectId.Player){
				if(((Player)tempObject).isDead())
					game.init();
			}
			if(tempObject.getId() == ObjectId.Projectile){
				if(tempObject.getY() > 1500 || tempObject.getY() < -1000)
					removeObject(tempObject);
				
				if(tempObject.getX() < -1000 || tempObject.getX() > 8000)
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
