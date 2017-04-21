import java.awt.image.BufferedImage;

public class Texture {
	
	public static BufferedImage block;
	public static BufferedImage background = null;
	public static BufferedImage complete = null;
	public static BufferedImage startmsg = null;
	public static BufferedImage health = null;
	public static BufferedImage instruction = null;
	
	public Texture(){
	
		ImageLoader loader = new ImageLoader();
		block = loader.loadImage("stone.jpg");
		background = loader.loadImage("/trees3.png");
		complete = loader.loadImage("/level_complete.png");
		startmsg = loader.loadImage("/escape_the_missle.png");
		health = loader.loadImage("/health.png");
		instruction = loader.loadImage("/Instructions.png");
		
		
	}
	
}
