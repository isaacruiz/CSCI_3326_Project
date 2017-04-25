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
	public static BufferedImage greenBlock = null;
	public static BufferedImage yellowBlock = null;
	public static BufferedImage grayBlock = null;
	public static BufferedImage greenProj = null;
	public static BufferedImage yellowProj = null;
	public static BufferedImage greenEnemy = null;
	public static BufferedImage yellowEnemy = null;
	private BufferedImage spriteSheet;
	
	public Texture(){
	
		ImageLoader loader = new ImageLoader();
		spriteSheet = loader.loadImage("SpriteSheet.png");
		background = loader.loadImage("trees3.png");
		complete = loader.loadImage("level_complete.png");
		startmsg = loader.loadImage("escape_the_missle.png");
		health = loader.loadImage("health.png");
		instruction = loader.loadImage("Instructions.png");
		greenEnemy = getSprite(1, 1, 64, 64);
		yellowEnemy = getSprite(1, 2, 64, 64);
		greenBlock = getSprite(3, 1, 32, 32);
		yellowBlock = getSprite(3, 2, 32, 32);
		grayBlock = getSprite(4, 1, 32, 32);
		greenProj = getSprite(3, 3, 32, 32);
		yellowProj = getSprite(3, 4, 32, 32);
	
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
