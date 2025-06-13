import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int v, cows;
        Node(int v, int cows){
            this.v = v;
            this.cows = cows;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cows, o.cows);
        }
    }

    private static ArrayList<ArrayList<Node>> m = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        int N = readInt();
        int M = readInt();
        for (int i=0;i<=N;i++) m.add(new ArrayList<>());
        for (int i=0;i<M;i++){ // 양방향 간선
            int a = readInt();
            int b = readInt();
            int c = readInt();
            m.get(a).add(new Node(b, c));
            m.get(b).add(new Node(a, c));
        }
        System.out.print(dijkstra(N));
    }

    private static int dijkstra(int N){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        while(!pq.isEmpty()){
            Node c= pq.poll();
            if (dist[c.v] < c.cows) continue;

            for (Node np : m.get(c.v)){
                int nc = np.cows + c.cows;
                if (dist[np.v] > nc){
                    dist[np.v] = nc;
                    pq.offer(new Node(np.v, nc));
                }
            }
        }

        return dist[N];
    }
    
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
    
}
