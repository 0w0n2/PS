import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Deque<Integer> dq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		Loop:
		for (int t=0;t<T;t++) {
			String p = br.readLine();
			dq.clear();
			int reverseFlag = 0;
			
			br.readLine(); // n 입력 받음
			// 1. 배열 입력 받기
			String x = br.readLine(); 
			int temp = 0;
			for (int i=1;i<x.length()-1;i++) {
				if (x.charAt(i)==',') {
					dq.offerLast(temp);
					temp = 0;
				}
				else temp = (temp*10) + (x.charAt(i)-'0'); // xi의 범위가 1~100 이므로 한 자릿수 초과 숫자도 입력 처리해줌
			}
			if (temp!=0) dq.offerLast(temp);
			
			// 2. p 에 따라서 연산 처리
			for (int i=0;i<p.length();i++) {
				if (p.charAt(i)=='R') reverseFlag = (reverseFlag+1)%2; // R이면 flag를 반전
				else if (p.charAt(i)=='D'&&!dq.isEmpty()){  
					if (reverseFlag==0) dq.pollFirst(); // 현재 not reverse 상태라면 앞에서부터 뺌
					else dq.pollLast(); // 현재 reverse 상태라면 뒤에서부터 뺌
				} else { // 만약 p='D'인데 dq.isEmpty() 라면 error 
					sb.append("error\n");
					continue Loop; // 다음 테케로..
				}
			}
			
			// 에러 없이 연산 수행 완료
			sb.append("[");
			int size = dq.size();
			if (!dq.isEmpty()) {
				if (reverseFlag==0) { // 만약 최종적인 상태가 not reverse 상태라면 앞에서부터 빼서 추가
					for (int i=0;i<size;i++) {
						sb.append(dq.pollFirst());
						if (i<size-1) sb.append(",");
					}
				} else { // reverse 상태라면 뒤에서부터 빼서 추가
					for (int i=0;i<size;i++) {
						sb.append(dq.pollLast());
						if (i<size-1) sb.append(",");
					}
				}
				
			}
			sb.append("]\n");
		}
		System.out.print(sb);
	}
}
