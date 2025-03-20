import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		n = (int)(Math.log(x+1)/Math.log(2)); // 자릿수
		
		
		int diff = x - ((int)Math.pow(2, n) - 1);
		String b = Integer.toBinaryString(diff);
		StringBuilder sb = new StringBuilder();
		
		for (int i=0;i<n-b.length();i++) sb.append(4);
		for (int i=0;i<b.length();i++) {
			if (b.charAt(i)=='0') sb.append(4);
			else sb.append(7);
		}
		System.out.println(sb);
		
	}
	
	static int[] num = {4,7}; 
	static int n;
	static int diff;
	static boolean alreadyPrinted = false;
	static boolean[] isUsed = new boolean[2];
	
	static void permutation(int ct, int x) {
		if (alreadyPrinted) return;
		
		if (ct==n) {
			if (diff--==0) {
				System.out.print(x);
				alreadyPrinted = true;
			}
			return;
		}
		
		permutation(ct+1, x+4*(int)Math.pow(10, n-ct-1));
		permutation(ct+1, x+7*(int)Math.pow(10, n-ct-1));
	}
	
}
