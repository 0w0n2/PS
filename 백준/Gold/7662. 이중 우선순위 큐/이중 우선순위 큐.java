import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		TreeMap<Long, Integer> map = new TreeMap<>();
		StringBuilder sb = new StringBuilder();
		
		for (int t=0;t<T;t++) {
			int k = Integer.parseInt(br.readLine()); // 연산의 개수
			map.clear();
			
			for (int i=0;i<k;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String p = st.nextToken();
				long x = Long.parseLong(st.nextToken());
				if (p.equals("I")) {
					if (map.containsKey(x)) map.replace(x, map.get(x)+1);
					else map.put(x, 1);
				}
				else {
					if (map.isEmpty()) continue;
					if (x==1) {
						long lastKey = map.lastKey();
						if (map.get(lastKey)>1) map.replace(lastKey, map.get(lastKey)-1);
						else map.remove(lastKey);
					} else {
						long firstKey = map.firstKey();
						if (map.get(firstKey)>1) map.replace(firstKey, map.get(firstKey)-1);
						else map.remove(firstKey);
					}
					
				}
			}
			
			if (map.isEmpty()) sb.append("EMPTY\n");
			else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}
		System.out.print(sb);
	}
}
