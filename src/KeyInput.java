

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyInput extends KeyAdapter {
	
	Handler handler;
	
	//Take handler in constructor to access game objects in the handler and modify their values
	//in accordance with key input
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0;i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player){
				Player p = (Player)tempObject;
				if(key == KeyEvent.VK_D) p.setMoveRight(true);
				if(key == KeyEvent.VK_A) p.setMoveLeft(true);
				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()){
						tempObject.setJumping(true);
						tempObject.setVelY(-13);
				}	
				if(key == KeyEvent.VK_SHIFT) p.toggleColor();	
			}	
		}
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0;i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player){
				Player p = (Player)tempObject;
				if(key == KeyEvent.VK_D) p.setMoveRight(false);
				if(key == KeyEvent.VK_A) p.setMoveLeft(false);
			}
		}
	}
}
