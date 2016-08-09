import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * 游戏窗体类
 * */
public class GameFrame extends JFrame {
	
	public static final int SCREEN_WEIGHT = 600;
	public static final int SCREEN_HEIGHT = 700;
	private JPanel jp = new GamePanel();
	public boolean canCreateBlock = false;				//移动后生成方块的开关
	public static Score score = null;
	public static int curScore = 0;
	public static int highScore = 0;
	private static int gameState = 0;					//游戏状态	：0为可走，1为胜利，-1为输

	//每回合方块的生成记录
	private int[][] reco = {{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0},
							{0,0,0,0}};
	
	public GameFrame(){
		this.init();
		this.add(jp);
		
	}
	
	public void init(){
		this.setTitle("2048Game");
		this.setFocusable(true);
		this.setSize(new Dimension(SCREEN_WEIGHT, SCREEN_HEIGHT));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (curScore > highScore){
					score.writeToFile("./data/record.txt", curScore);
				}
				System.exit(0);
			}
		});			
		this.addKeyListener(new KeyBoard());
		//初始化分数
		score = new Score();
		score.setHighScore(score.readFromFile("./data/record.txt"));
	}		
	
	//控制：向上移动
	private void moveUp(){
		for (int col=0; col<4; col++){
			for (int row=1; row<4; row++){
				while (row-1>=0 && GamePanel.map[row][col] != 0 && reco[row-1][col] != 1){				//不越界、当前方块不为0
					if (GamePanel.map[row][col] == GamePanel.map[row-1][col]){	//当前方块的值等于前方方块
						GamePanel.map[row-1][col] += GamePanel.map[row][col];	
						addScore(2*GamePanel.map[row][col]);
						GamePanel.map[row][col] = 0;
						reco[row-1][col] = 1;
						canCreateBlock = true;
					}else if (GamePanel.map[row-1][col] == 0){					//前方方块为0
						GamePanel.map[row-1][col] = GamePanel.map[row][col];
						GamePanel.map[row][col] = 0;
						row--;
						canCreateBlock = true;
					}else if (GamePanel.map[row-1][col] != GamePanel.map[row][col]){
						break;
					}
				}
			}
		}		
	}
	
	//控制:向下移动
	private void moveDown(){
		for (int col=0; col<4; col++){
			for (int row=2; row>=0; row--){
				while (row+1<4 && GamePanel.map[row][col] != 0 && reco[row+1][col] != 1){				//不越界、当前方块不为0
					if (GamePanel.map[row][col] == GamePanel.map[row+1][col]){	//当前方块的值等于前方方块
						GamePanel.map[row+1][col] += GamePanel.map[row][col];	
						addScore(2*GamePanel.map[row][col]);
						GamePanel.map[row][col] = 0;
						reco[row+1][col] = 1;
						canCreateBlock = true;
					}else if (GamePanel.map[row+1][col] == 0){					//前方方块为0
						GamePanel.map[row+1][col] = GamePanel.map[row][col];
						GamePanel.map[row][col] = 0;
						row++;
						canCreateBlock = true;
					}else if (GamePanel.map[row+1][col] != GamePanel.map[row][col]){
						break;
					}
				}
			}
		}		
	}
	
	//控制:向左移动
	private void moveLeft(){
		for (int row=0; row<4; row++){
			for (int col=1; col<4; col++){
				while (col-1>=0 && GamePanel.map[row][col] != 0 && reco[row][col-1] != 1){				//不越界、当前方块不为0
					if (GamePanel.map[row][col] == GamePanel.map[row][col-1]){	//当前方块的值等于前方方块
						GamePanel.map[row][col-1] += GamePanel.map[row][col];	
						addScore(2*GamePanel.map[row][col]);
						GamePanel.map[row][col] = 0;
						reco[row][col-1] = 1;
						canCreateBlock = true;
					}else if (GamePanel.map[row][col-1] == 0){					//前方方块为0
						GamePanel.map[row][col-1] = GamePanel.map[row][col];
						GamePanel.map[row][col] = 0;
						col--;
						canCreateBlock = true;
					}else if (GamePanel.map[row][col-1] != GamePanel.map[row][col]){
						break;
					}
				}
			}
		}		
	}
	
	//向右移动方法:
	private void moveRight(){
		for (int row=0; row<4; row++){
			for (int col=2; col>=0; col--){
				while (col+1<4 && GamePanel.map[row][col] != 0 && reco[row][col+1] != 1){	//不越界、当前方块不为0,且前面的之前的方块不是刚生成的
					if (GamePanel.map[row][col] == GamePanel.map[row][col+1]){	//当前方块的值等于前方方块
						GamePanel.map[row][col+1] += GamePanel.map[row][col];	
						addScore(2*GamePanel.map[row][col]);
						GamePanel.map[row][col] = 0;
						reco[row][col+1] = 1;
						canCreateBlock = true;
					}else if (GamePanel.map[row][col+1] == 0){					//前方方块为0
						GamePanel.map[row][col+1] = GamePanel.map[row][col];
						GamePanel.map[row][col] = 0;
						col++;
						canCreateBlock = true;
					}else if (GamePanel.map[row][col+1] != GamePanel.map[row][col]){
						break;
					}
				}
			}
		}		
	}
	
	private void createBlock(){			//随机生成方块
		//System.out.println(curScore);
b:		while (canCreateBlock){
			for (int col=0; col<4; col++)
				for (int row=0; row<4; row++){
					if (GamePanel.map[row][col] == 0 && Math.random() < 0.0625){
						GamePanel.map[row][col] = 2;
						canCreateBlock = false;
						break b;
					}
				}
		}
		
	}
	
	//清除方块生成记录
	private void cleanReco(){
		for (int col=0; col<reco.length; col++)
			for (int row=0; row<reco[0].length; row++)
				reco[row][col] = 0;
	}
	
	//加分
	private void addScore(int add){
		curScore += add;
	}
	
	//检测胜负	0为有路可走，1为赢，-1为输  //2016/6/7完成
	private int winOrLose(){
		//赢
		for (int row=0; row<GamePanel.map.length; row++){
			for (int col=0; col<GamePanel.map[0].length; col++){
				if (GamePanel.map[row][col] >= 2048){
					return 1;
				}	
				if (GamePanel.map[row][col] == 0){
					return 0;
				}
			}
			
		}
		//输的条件
		for (int row=0; row<GamePanel.map.length; row++){
			for (int col=0; col<GamePanel.map[0].length-1; col++){
				//有可走路
				if (GamePanel.map[row][col] == GamePanel.map[row][col+1]){
					return 0;
				}				
			}
		}
		for (int col=0; col<GamePanel.map.length; col++){
			for (int row=0; row<GamePanel.map[0].length-1; row++){
				//有可走路
				if (GamePanel.map[row][col] == GamePanel.map[row+1][col] && GamePanel.map[row][col] != 0){
					return 0;
				}				
			}
		}
		//默认为输
		return -1;
	}
	
	//显示结果
	private void showResult(){
		JOptionPane j = new JOptionPane();
		Object[] options = {"reStart", "exit"};
		int choice = 0;
		if (gameState == 1){
			choice = j.showOptionDialog(this, "you win", "win", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		}else if (gameState == -1){
			choice = j.showOptionDialog(this, "you lose", "lose", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		}
		//选择重新开始
		if (choice == 0){
			restart();
		}
	}
	
	//游戏主逻辑
	private void logic(){
		gameState = winOrLose();
		if (gameState != 0){
			showResult();
		}
	}
	
	//重新开始
	private void restart(){
		GamePanel.map = new int[4][4];
		canCreateBlock = true;
		createBlock();
		canCreateBlock = true;
		createBlock();
		curScore = 0;
		GamePanel.curScore = 0;
		jp.repaint();
	}
	
	//键盘监听
	private class KeyBoard extends KeyAdapter{

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key){
			case KeyEvent.VK_UP:
				cleanReco();
				moveUp();
				createBlock();
				jp.repaint();
				logic();
				break;
			case KeyEvent.VK_DOWN:
				cleanReco();
				moveDown();
				createBlock();
				jp.repaint();
				logic();
				break;
			case KeyEvent.VK_LEFT:
				cleanReco();
				moveLeft();
				createBlock();
				jp.repaint();
				logic();
				break;
			case KeyEvent.VK_RIGHT:
				cleanReco();
				moveRight();
				createBlock();
				jp.repaint();
				logic();
				break;
			case KeyEvent.VK_A:
				restart();
				break;
			}		
		}
	}
	
}
