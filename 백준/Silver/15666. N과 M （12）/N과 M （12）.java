import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static ArrayList<Integer> nums;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		HashSet<Integer> inputNums = new HashSet<>();
		for (int i=0;i<N;i++) inputNums.add(Integer.parseInt(st.nextToken()));
		nums = new ArrayList<>(inputNums);
		Collections.sort(nums); // 오름차순 정렬
		
		select(0, 0, new ArrayList<>());
		System.out.println(sb);
	}
	
	static StringBuilder sb = new StringBuilder();
	public static void select(int depth, int start, ArrayList<Integer> selection) {
		
		if(depth==M) {
			StringBuilder temp = new StringBuilder();
			for (int s : selection) {
				temp.append(s).append(" ");
			}
			sb.append(temp.toString().trim()).append("\n");
			return;
		}

		for (int i=start;i<nums.size();i++) {
			selection.add(nums.get(i));
			select(depth+1, i, selection);
			selection.remove(selection.size()-1);
		}
	}
}