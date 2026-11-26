import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }

    static final int[] dx = {1, -1};

    static class Node {
        int idx;
        int ct;

        public Node(int idx, int ct) {
            this.idx = idx;
            this.ct = ct;
        }
    }

    public static void main(String[] args) throws IOException{
        int N = readInt();
        int[] num = new int[N];
        for (int i=0; i<N; i++) num[i] = readInt();

        int start = readInt()-1;
        int end = readInt()-1;

        Queue<Node> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[N];
        q.offer(new Node(start, 0));
        isVisited[start] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.idx == end) {
                System.out.println(cur.ct);
                return;
            }
            
            for (int d : dx) {
                int step = d * num[cur.idx];
                for (int next = cur.idx + step; 0<=next && next<N; next+=step) {
                    if (isVisited[next]) continue;
                    isVisited[next] = true;
                    q.offer(new Node(next, cur.ct+1));
                }
            }
        }

        System.out.print(-1);
    }
}
