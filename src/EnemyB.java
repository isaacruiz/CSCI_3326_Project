import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class EnemyB extends Enemy {
	
	public EnemyB(float x, float y, Handler handler, Color color, boolean colorChange, ObjectId id){
		
		super(x, y, handler, color, colorChange, id);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		g2d.fillOval((int)x, (int)y, width, height);
	}

public void tick(LinkedList<GameObject> object) {
		
		int vpx= 0;
		int vpy = 0;
		
		
		if(dynamicColor){
			toggleColor();
			toggle++;
		}
		// shoots projectile every firerate no of updates
		// (eg. fireRate = 60 and updates ~ 60/second so firerate = 1/second
		if (counter % fireRate == 0) {
			
			
				for(int i = 0; i < 3; i++){
				vpx = rand.nextInt(20) - 10;
				vpy = rand.nextInt(20) - 10;
				Projectile p = new Projectile(x, y, vpx, vpy, color, ObjectId.Projectile);
				p.gravity = 0;
				handler.addObject(p);
				
			}
		}

		counter++;

	}
}
