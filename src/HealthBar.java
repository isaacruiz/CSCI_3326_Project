import java.awt.Color;
import java.awt.Graphics;

public class HealthBar {
	
	Player player;
	Color color = Color.green;
	
	public HealthBar(Player p){
		player = p;
	}
	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillRect(7, 7, 206, 31);
		g.setColor(color);
		g.fillRect(10, 10, player.getHealth(), 25);
	}
	
	public void tick(){
		if(player.getHealth() < 100)
			color = Color.yellow;
		
		if(player.getHealth() < 75)
			color = Color.red;	
	}

}
