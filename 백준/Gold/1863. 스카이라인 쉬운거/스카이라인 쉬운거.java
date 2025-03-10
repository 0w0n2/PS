import java.util.*;
import java.io.*;

public class Main {
	
	static class Node{
		int x, height;
		Node(int x, int height){
			this.x = x;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		// N개의 줄에 고도가 바뀌는 지점의 x, y 좌표가 주어짐
		
		Deque<Node> stack = new ArrayDeque<>();
		
		StringTokenizer st;
	
		// 초기 지점
		int building = 0;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			Node top; 
			if (!stack.isEmpty() && y < (top=stack.getLast()).height) {
				// 더 작은 높이가 들어왔다면, 현재 들어온 값보다 같거나 작은 높이의 stack의 원소가 나오기 전까지 계산
				top = stack.pollLast();
				building++;
				while(!stack.isEmpty() && stack.getLast().height > y) {
					Node topleft = stack.pollLast();
					if (top.height != topleft.height) building++;
					top = topleft;
				}
			}
			if(y!=0) stack.offerLast(new Node(x, y));
			
		}
		
		if (!stack.isEmpty()) {
			Node top = stack.pollLast();
			building++;
			while(!stack.isEmpty()) {
				Node topleft = stack.pollLast();
				if (top.height!=topleft.height) building++;
				top = topleft;
			}
		}
		System.out.print(building);
	}
}