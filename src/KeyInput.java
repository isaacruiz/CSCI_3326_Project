

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyInput extends KeyAdapter {
	
	Handler handler;
	
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
						tempObject.setVelY(-10);
				}
						
				if(key == KeyEvent.VK_E) p.toggleColor();	
			}	
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
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
