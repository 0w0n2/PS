import java.util.*;
import java.io.*;

public class Main {
	
	static int result = 0, t;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int[] A = new int[a];
		int[] B = new int[b];
		
		for (int i=0;i<a;i++) A[i] = Integer.parseInt(br.readLine());
		for (int i=0;i<b;i++) B[i] = Integer.parseInt(br.readLine());
		
		HashMap<Integer, Integer> aMap = findCase(A);
		HashMap<Integer, Integer> bMap = findCase(B);
		
		if (aMap.size()<bMap.size()) getResult(aMap, bMap);
		else getResult(bMap, aMap);
		System.out.println(result);
	}
	
	static HashMap<Integer, Integer> findCase(int[] x) {
		
		HashMap<Integer, Integer> xMap = new HashMap<>();
		
		int n = x.length;
		int[] prefix = new int [2*n+1];
		for (int i=1;i<=2*n;i++) {
			prefix[i] = prefix[i-1] + x[(i-1)%n];
		}
		
		if (prefix[n]==t) result++;
		else if (prefix[n]<t) xMap.put(prefix[n], xMap.getOrDefault(prefix[n], 0) + 1);
		
		for (int len = 1; len<n; len++) {
			for (int i=0; i<n; i++) {
				int sum = prefix[i+len] - prefix[i];
				
				if (sum==t) result++;
				else if (sum<t) xMap.put(sum, xMap.getOrDefault(sum, 0) + 1);
			}
		}
		
		return xMap;
	}
	
	static void getResult(HashMap<Integer, Integer> S, HashMap<Integer, Integer> B) {
		
		for (int s : S.keySet()) {
			int key = t - s;
			if (B.containsKey(key)) {
				result += S.get(s) * B.get(key);
			}
		}
	}
	
}
