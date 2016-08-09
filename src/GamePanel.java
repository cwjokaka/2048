import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/*
 * 游戏面板类
 * */
public class GamePanel extends JPanel {

	private final int BG_OFF_X = 50;
	private final int BG_OFF_Y = 120;
	private final int BLOCK_SIZE = 125;
	public static int curScore = 0;
	private int highScore = 0;
	private Score score = null;
	public static int[][] map = {{0,0,0,0},
							     {0,0,0,2},
								 {0,0,0,0},
								 {0,2,0,0}};
	
	public GamePanel(){
		this.init();
		repaint();
	}
	
	private void init(){
		score = new Score();
		highScore = score.readFromFile("./data/record.txt");
		
		//this.addKeyListener(new KeyBoard());
		//this.setFocusable(true);
	}
	
	//重写paint
	public void paint(Graphics g){
		Color color = g.getColor();
		g.setColor(new Color(247, 247, 211));	//游戏背景色
		
		g.clearRect(0, 0, GameFrame.SCREEN_WEIGHT, GameFrame.SCREEN_HEIGHT);
		g.fillRect(0, 0, GameFrame.SCREEN_WEIGHT, GameFrame.SCREEN_HEIGHT);
		
		this.drawRect(g);
		
		//显示分数
		g.setColor(Color.BLACK);
		g.setFont(new Font("宋体", 0, 30));
		curScore = GameFrame.curScore;
		Integer i = new Integer(curScore);
		g.drawString(i.toString(), 50, 100);
		g.drawString("最高分:"+highScore, 400, 100);
		g.setColor(color);
	}
	
	//绘制方块
	private void drawRect(Graphics g){
		for (int col=0; col<map.length; col++){			//列
			for (int row=0; row<map[0].length; row++){	//行
				if (map[row][col] == 0)
					g.drawImage(Res.img_bg, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 2)
					g.drawImage(Res.img_2, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 4)
					g.drawImage(Res.img_4, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 8)
					g.drawImage(Res.img_8, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 16)
					g.drawImage(Res.img_16, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 32)
					g.drawImage(Res.img_32, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 64)
					g.drawImage(Res.img_64, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 128)
					g.drawImage(Res.img_128, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 256)
					g.drawImage(Res.img_256, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 512)
					g.drawImage(Res.img_512, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 1024)
					g.drawImage(Res.img_1024, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
				else if (map[row][col] == 2048)
					g.drawImage(Res.img_2048, BG_OFF_X + BLOCK_SIZE*col, BG_OFF_Y + BG_OFF_Y*row, this);
			}			
		}	
	}
	
}
