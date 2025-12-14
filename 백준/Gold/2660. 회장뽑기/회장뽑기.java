import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
    public static void main(String[] args) throws IOException{
        N = readInt();
        for (int i=0;i<=N;i++) map.add(new ArrayList<>());

        while(true) {
            int a = readInt();
            int b = readInt();
            if (a==-1 && b==-1) break;
            map.get(a).add(b);
            map.get(b).add(a);
        }

        int[] scores = new int[N+1];
        int minScore = Integer.MAX_VALUE;

        for (int i=1;i<=N;i++) {
            scores[i] = bfs(i);
            minScore = Math.min(minScore, scores[i]);
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i=1;i<=N;i++) {
            if (scores[i] == minScore) candidates.add(i);
        }
        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        Collections.sort(candidates);
        for (int c:candidates) sb.append(c).append(" ");
        System.out.print(sb.toString().trim());

    }

    private static int N;
    private static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    private static int bfs(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        dist[start] = 0;

        int maxDist = 0;

        while(!q.isEmpty()) {
            int c = q.poll();

            for (int next: map.get(c)) {
                if (dist[next] == -1) {
                    dist[next] = dist[c]+1;
                    q.offer(next);
                    maxDist = Math.max(maxDist, dist[next]);
                }
            }
        }
        return maxDist;
    }
}