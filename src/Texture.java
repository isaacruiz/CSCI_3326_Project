import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Texture {
	
	public static BufferedImage block;
	public static BufferedImage background = null;
	public static BufferedImage complete = null;
	public static BufferedImage startmsg = null;
	public static BufferedImage health = null;
	public static BufferedImage instruction = null;
	public static BufferedImage turret = null;
	private BufferedImage spriteSheet;
	
	public Texture(){
	
		ImageLoader loader = new ImageLoader();
		spriteSheet = loader.loadImage("SpriteSheet.png");
		//block = loader.loadImage("GrayBrick.png");
		background = loader.loadImage("trees3.png");
		complete = loader.loadImage("level_complete.png");
		startmsg = loader.loadImage("escape_the_missle.png");
		health = loader.loadImage("health.png");
		instruction = loader.loadImage("Instructions.png");
		turret = getSprite(1, 1, 64, 64);
		//turret = loader.loadImage("/turret.png");
	
	
	}

	public BufferedImage getSprite(int row, int col, int width, int height) {

		BufferedImage sprite = null;
		sprite = spriteSheet.getSubimage(col * width - width, row * height - height, width, height);

		return sprite;
	}

	public static void drawRotatedImage(BufferedImage image, double x, double y, double angle, Graphics g) {

		
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(angle, image.getWidth()/ 2, image.getHeight()/2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, at, null);
	}
}
