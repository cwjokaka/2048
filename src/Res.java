import java.awt.Image;
import java.awt.Toolkit;

/*
 * 游戏资源
 * */
public class Res {
	public static Image img_bg;	//方块背景
	public static Image img_2;	//方块2
	public static Image img_4;	//方块4
	public static Image img_8;	//方块8
	public static Image img_16;	
	public static Image img_32;	
	public static Image img_64;	
	public static Image img_128;	
	public static Image img_256;	
	public static Image img_512;
	public static Image img_1024;
	public static Image img_2048;
	
	static {
		img_bg = loadImage("img_bg.png");
		img_2 = loadImage("img_2.png");
		img_4 = loadImage("img_4.png");
		img_8 = loadImage("img_8.png");
		img_16 = loadImage("img_16.png");
		img_32 = loadImage("img_32.png");
		img_64 = loadImage("img_64.png");
		img_128 = loadImage("img_128.png");
		img_256 = loadImage("img_256.png");
		img_512 = loadImage("img_512.png");
		img_1024 = loadImage("img_1024.png");
		img_2048 = loadImage("img_2048.png");
	}
	
	private static Image loadImage(String fileName){
		return Toolkit.getDefaultToolkit().getImage("images/"+fileName);
	}
}
