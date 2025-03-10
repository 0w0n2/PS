import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		// N개의 줄에 고도가 바뀌는 지점의 x, y 좌표가 주어짐
		
		Deque<Integer> stack = new ArrayDeque<>();
		
		StringTokenizer st;
	
		// 초기 지점
		int building = 0;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int top; 
			if (!stack.isEmpty() && y < (top=stack.getLast())) {
				// 더 작은 높이가 들어왔다면, 현재 들어온 값보다 같거나 작은 높이의 stack의 원소가 나오기 전까지 계산
				top = stack.pollLast();
				building++;
				while(!stack.isEmpty() && stack.getLast() > y) {
					int topleft = stack.pollLast();
					if (top != topleft) building++;
					top = topleft;
				}
			}
			if(y!=0) stack.offerLast(y);
			
		}
		
		if (!stack.isEmpty()) {
			int top = stack.pollLast();
			building++;
			while(!stack.isEmpty()) {
				int topleft = stack.pollLast();
				if (top!=topleft) building++;
				top = topleft;
			}
		}
		System.out.print(building);
	}
}