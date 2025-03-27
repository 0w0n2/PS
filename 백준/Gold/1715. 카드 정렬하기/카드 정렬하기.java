import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i=0;i<N;i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int result = 0;
		while(pq.size()>1) {
			int sum = pq.poll() + pq.poll();
			result += sum;
			pq.offer(sum);
		}
		System.out.print(result);
	}
}
