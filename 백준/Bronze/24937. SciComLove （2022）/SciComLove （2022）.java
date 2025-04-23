import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		st.nextToken();
		int N = (int) st.nval;
		
		StringBuilder sb = new StringBuilder("SciComLove");
		N%=sb.length();
		
		for (int i=0;i<N;i++) {
			char start = sb.charAt(0);
			sb.deleteCharAt(0);
			sb.append(start);
		}
		System.out.print(sb);
	}
}
