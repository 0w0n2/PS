import java.util.*;
import java.io.*;

public class Main {
	
	private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static class Cor{
		int x, y;
		Cor(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static ArrayList<Cor> oneLocation = new ArrayList<>(); 
	
	public static void main(String[] args) throws IOException{

		for (int i=0;i<10;i++) {
			for (int j=0;j<10;j++) {
				map[i][j] = temp[i][j] = readInt();
				if (map[i][j]==1) oneLocation.add(new Cor(i, j));
			}
		}
		
		recursive(0, 0, new int[] {5, 5, 5, 5, 5});
		System.out.println(min==Integer.MAX_VALUE ? -1:min);
	}
	
	static int[][] map = new int[10][10];
	static int[][] temp = new int[10][10];
	static int min = Integer.MAX_VALUE;
	
	private static void recursive(int idx, int ct, int[] num) {
		if (ct>min) return;
		
		if (idx==oneLocation.size()) {
			min = Math.min(min, ct);
			return;
		}
		
		Cor cur = oneLocation.get(idx);
		if (temp[cur.x][cur.y]==0) {
			recursive(idx+1, ct, num);
			return;
		}
		
		for (int i=0;i<5;i++) {
			if (num[i]>0 && canAttach(i+1, cur)) {
				attach(i+1, cur);
				num[i]--;
				recursive(idx+1, ct+1, num);
				dettach(i+1, cur);
				num[i]++;
			}
		}
	}
	
	private static boolean canAttach(int size, Cor cur) {
		if (cur.x+size>10||cur.y+size>10) return false;
		
		for (int i=0;i<size;i++) {
			for (int j=0;j<size && cur.y+j<10;j++) {
				if (temp[cur.x+i][cur.y+j]==0) return false;
			}
		}
		return true;
	}
	
	private static void attach(int size, Cor cur) {
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				temp[cur.x+i][cur.y+j]=0;
			}
		}
	}
	
	private static void dettach(int size, Cor cur) {
		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				temp[cur.x+i][cur.y+j]=map[cur.x+i][cur.y+j];
			}
		}
	}
}
