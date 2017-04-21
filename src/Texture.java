import java.awt.image.BufferedImage;

public class Texture {
	
	public static BufferedImage Block;
	
	public Texture(){
	
		ImageLoader loader = new ImageLoader();
		Block = loader.loadImage("stone.png");
		
	}
	
}
