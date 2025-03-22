import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] result = new int[Integer.parseInt(br.readLine())];
		for (int i=0;i<result.length;i++) {
			result[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(result);
		StringBuilder sb = new StringBuilder();
		for (int i:result) sb.append(i).append("\n");
		System.out.println(sb);
	}
}
