import java.io.*;
import java.util.*;

public class Main {
	static int R = 302;
	static int M = 1_000_000_007;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		String T = br.readLine();
		String P = br.readLine(); 
		// 문자열 T에서 P가 몇 번 출현하는지, 그리고 P가 출현하는 T의 시작 인덱스를 차례대로 공백으로 구분해 출력
		
		long[] primeTable = new long[P.length()];
		long num = 1;
		for (int i=0;i<primeTable.length;i++) {
			primeTable[P.length()-1-i] = num;
			num *= R;
			num %= M;
		}
		
		if (P.length()>T.length()) System.out.print(0);
		else {
			// 1. 문자열 P의 해시 Hp를 구함
			// 2. 문자열 T에 대해서 첫번째 부분문자열의 해시 Ht0을 구함
			long Hp = 0, Ht = 0, ct = 0;
			StringBuilder sb = new StringBuilder();
			
			// Todo - 알파벳이랑, 공백 생각해서 아스키코드값 빼줘야 함!~!!
			for (int i=0;i<P.length();i++) {
				Hp += P.charAt(i) * primeTable[i];
				Ht += T.charAt(i) * primeTable[i];
				Hp %= M;
				Ht %= M;
			}
			if ((Ht-Hp) % M == 0) {
				ct++;
				sb.append(1).append(" ");
			}
			
			/*
			3. 라빈카프 알고리즘을 통해서 이전 번째 해시값에서(Ht0) 현재 번째 해시 Ht1를 구하면서
				Hp와 해시값이 일치하는지 확인
			*/
			
			for (int i=0; i<T.length()-P.length() ; i++) {
				Ht -= T.charAt(i) * primeTable[0];
				Ht %= M;
				Ht *= R;
				Ht += T.charAt(i+P.length());
				Ht %= M;
				
				if ((Ht-Hp) % M == 0) {
					ct++;
					sb.append(i+2).append(" ");
				}
			}
			System.out.println(ct);
			System.out.println(sb);
		}
		
	}
}
