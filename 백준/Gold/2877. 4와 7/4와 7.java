import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		int n = (int)(Math.log(x+1)/Math.log(2)); // 자릿수 : log2(x+1) 
		int diff = x - ((int)Math.pow(2, n) - 1); // x번째 숫자가 해당 자릿수(수열) 내에서 어떤 위치에 있는지
		String b = Integer.toBinaryString(diff);  // 이진수로 변환
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0;i<n-b.length();i++) sb.append(4); // 구한 이진수에서 부족한 숫자는 0(4)로 채움
		for (int i=0;i<b.length();i++) {			   // 0이면 4, 1이면 7 추가  
			if (b.charAt(i)=='0') sb.append(4);
			else sb.append(7);
		}
		System.out.println(sb);
	}
}
