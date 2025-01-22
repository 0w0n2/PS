import java.util.*;
import java.io.*;

class Main
{	
	static long min = Long.MAX_VALUE;
	static long max = Long.MIN_VALUE;
	static long [] num;
	static int n;
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 숫자 개수
		num = new long [n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<n;i++)num[i] = Long.parseLong(st.nextToken()); // 숫자 입력
		st = new StringTokenizer(br.readLine());
		int [] sign = new int [4]; // + - * /
		for (int i=0;i<4;i++) sign[i] = Integer.parseInt(st.nextToken()); // 부호 입력
		cal(1, num[0], sign);
		System.out.println(max+"\n"+min);
	}
	public static void cal(int i, long c, int[] nowSign) {
		if (i==n) {
			max = Math.max(max, c);
			min = Math.min(min, c);
			return;
		}
		for (int j=0;j<4;j++) {
			if (nowSign[j]>0) {
				nowSign[j]--;
				if (j==0) cal(i+1, c+num[i], nowSign);
				else if (j==1) cal(i+1, c-num[i], nowSign);
				else if (j==2) cal(i+1, c*num[i], nowSign);
				else cal(i+1, c/num[i], nowSign);
				nowSign[j]++;
			}
		}
	}
}

