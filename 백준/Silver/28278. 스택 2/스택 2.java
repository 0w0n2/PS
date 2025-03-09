// Main_28278_스택2
import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 5가지의 명령
		// 1. 1 x : 정수 x를 스택에 넣는다 (1<=X<=100_000) [삽입]
		// 2. 2 : 스택에 정수가 있다면 맨 위의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다. [삭제]
		// 3. 3 : 스택에 들어있는 정수의 개수를 출력한다. [사이즈 체크]
		// 4. 4 : 스택이 비어있으면 1, 아니면 0을 출력 [비어있는지 유무]
		// 5. 5 : 스택에 정수가 있다면 맨 위의 정수를 출력, 없다면 -1을 대신 출력 [조회]
		
		Deque<Integer> stack = new ArrayDeque<>(); // 스택
		
		int N = Integer.parseInt(br.readLine()); // 명령의 수 N (<=1_000_000)
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if (st.countTokens()>0) {
				stack.offerLast(Integer.parseInt(st.nextToken()));
			}
			else {
				if (cmd==2) sb.append(stack.isEmpty() ? -1 : stack.pollLast());
				else if (cmd==3) sb.append(stack.size());
				else if (cmd==4) sb.append(stack.isEmpty() ? 1 : 0);
				else sb.append(stack.isEmpty() ? -1 : stack.getLast());
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}
