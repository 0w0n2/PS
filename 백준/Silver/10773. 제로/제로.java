import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 재현이가 잘못된 수를 부를 때 : 0을 외치고, 가장 최근에 쓴 수를 지우게(stack의 top 원소를 제거)
		// 0 : 가장 최근에 쓴 수를 지움
		// x : 해당 수를 stack에 저장
		// k개의 줄에 명령이 주어짐 (k<=100_000)
		int [] stack = new int[100_000];
		int top = 0;
		
		int k = Integer.parseInt(br.readLine());
		
		for (int i=0;i<k;i++) {
			int x = Integer.parseInt(br.readLine());
			
			if (x!=0) stack[top++] = x;
			else top--; 
		}
		
		int result = 0;
		for (int i=0;i<top;i++) result+=stack[i];
		System.out.print(result);
	}
}
