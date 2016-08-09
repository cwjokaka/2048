import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 分数数据的存储
 * */
public class Score {
	
	private int curScore = 0;				//当前分数
	private int highScore = 4;				//最高分
	
	public static void main(String[] args){
		Score s = new Score();
		s.writeToFile("./data/record.txt", s.getHighScore());
		System.out.println(s.readFromFile("./data/record.txt"));
	}
	
	public void writeToFile(String path, int highScore){
		try {
			FileOutputStream fos = new FileOutputStream(path);
			DataOutputStream dos = new DataOutputStream(fos);
			//效率慢
			dos.writeUTF(new Integer(highScore).toString());
			
			dos.flush();
			dos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} 
	}
	
	public int readFromFile(String path){
		int i = 0;
		try {
			File f = new File(path);
			if(f.exists()){
				FileInputStream fis = new FileInputStream(f);
				DataInputStream dis = new DataInputStream(fis);
				String s = dis.readUTF();
				i = Integer.valueOf(s);
				dis.close();
			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return i;
	}
	
	public int getCurScore() {
		return curScore;
	}
	public void setCurScore(int curScore) {
		this.curScore = curScore;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
}
