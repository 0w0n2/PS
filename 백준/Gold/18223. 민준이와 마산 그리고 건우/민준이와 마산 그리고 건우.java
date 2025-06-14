/**
 * 다익스트라 한 번만 돌리는 방식 (minCostWithLayover <= minCost) 비교
 */

import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int v, cost;
        boolean isMet;
        Node(int v, int cost){
            this.v = v;
            this.cost = cost;
            this.isMet = false;
        }

        Node(int v, int cost, boolean isMet){
            this(v, cost);
            this.isMet = isMet;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int V, P;
    static ArrayList<ArrayList<Node>> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        V = readInt();
        int E = readInt();
        P = readInt();

        for (int i=0;i<=V;i++) arr.add(new ArrayList<>());

        for (int i=0;i<E;i++){ // 양방향
            int v = readInt();
            int e = readInt();
            int cost = readInt();
            arr.get(v).add(new Node(e, cost));
            arr.get(e).add(new Node(v, cost));
        }

        if (P==1) System.out.print("SAVE HIM");
        else System.out.print(dijkstra() ? "SAVE HIM" : "GOOD BYE");

    }

    private static boolean dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, false)); // 시작점(1번 노드)

        int minCost = Integer.MAX_VALUE;
        int minCostWithLayover = Integer.MAX_VALUE;

        int[][] dist = new int[V+1][2];
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
        dist[1][0] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if (dist[cur.v][cur.isMet ? 1:0] < cur.cost) continue;

            if (cur.v == V){
                if (cur.isMet) {
                    minCostWithLayover = Math.min(minCostWithLayover, cur.cost);
                    break;
                }
                minCost = Math.min(minCost, cur.cost);
                continue;
            }

            for (Node nextNode : arr.get(cur.v)){
                int nextCost = cur.cost + nextNode.cost;
                boolean nextMet = cur.isMet || nextNode.v == P;
                int met = nextMet ? 1:0;
                if (dist[nextNode.v][met] > nextCost){
                    dist[nextNode.v][met] = nextCost;
                    pq.offer(new Node(nextNode.v, nextCost, nextMet));
                }
            }

        }

        return minCostWithLayover <= minCost;
    }

    private static final StreamTokenizer st= new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
