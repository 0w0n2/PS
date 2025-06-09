import java.util.*;
import java.io.*;

public class Main {

    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        for (int t=0;t<T;t++){
            int N = readInt();
            int M = readInt();
            Queue<Node> q = new ArrayDeque<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i=0;i<N;i++){
                int impact = readInt();
                q.offer(new Node(i, impact));
                pq.offer(impact);
            }

            int order = 0;
            while(!q.isEmpty()){
                Node c = q.poll();
                if (c.impact==pq.peek()){
                    pq.poll();
                    order++;
                    if (c.idx==M){
                        sb.append(order).append("\n");
                        break;
                    }
                } else {
                    q.offer(c); // 다시 넣기
                }
            }
        }
        System.out.print(sb);
    }

    private static class Node{
        int idx, impact;
        public Node(int idx, int impact) {
            this.idx = idx;
            this.impact = impact;
        }
    }
}
