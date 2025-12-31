import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	private static int readInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	public static void main(String[] args) throws IOException{
		N = readInt();
		health = new int[N];
		happy = new int[N];
		
		for (int i=0;i<N;i++) health[i] = readInt();
		for (int i=0;i<N;i++) happy[i] = readInt();
	
		System.out.print(recur(0, 0, 100));
	}
	
	private static int N, health[], happy[];
	
	private static int recur(int idx, int happySum, int curHealth) {
		int result = 0;
		
		if (idx == N) return happySum;	// 기저 조건
		
		if ((curHealth - health[idx]) > 0) result = Math.max(result, recur(idx+1, happySum + happy[idx], curHealth - health[idx]));
		
		return Math.max(result, recur(idx+1, happySum, curHealth));
	}
}
