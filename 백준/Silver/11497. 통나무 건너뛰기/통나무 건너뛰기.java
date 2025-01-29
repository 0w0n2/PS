import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int t=0;t<tc;t++){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> num = new PriorityQueue<>(Collections.reverseOrder());
            for (int i=0;i<n;i++) num.offer(Integer.parseInt(st.nextToken()));
            ArrayList<Integer> sorted = new ArrayList<>();
            sorted.add(num.poll());
            for (int i=1;i<n;i++){
                int k = num.poll();
                int start = sorted.get(0);
                int end = sorted.get(sorted.size()-1);
                if(start>end) sorted.add(0, k);
                else sorted.add(k);
            }
            sorted.add(0, sorted.get(sorted.size()-1));
            int maxDiff = 0;
            for (int i=1;i<sorted.size();i++){
                int diff = Math.abs(sorted.get(i) - sorted.get(i-1));
                maxDiff = Math.max(maxDiff, diff);
            }
            sb.append(maxDiff+"\n");
        }
        System.out.println(sb);
    }
}
