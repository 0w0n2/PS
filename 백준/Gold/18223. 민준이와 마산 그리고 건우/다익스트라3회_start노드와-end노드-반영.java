/**
 * 다익스트라 3번 돌리는 경우
 * 1. 1->V
 * 2. 1->P
 * 3. P->V
 * 결과적으로 1 == 2+3 인지 비교
 * 
 * P 위치를 방문했는지 여부를 체크할 필요가 없어서 dist 배열이 1차원으로 줄어듦
 * 성능면에서 1회 돌리는 것보다 실행 시간이 20ms 정도 느려졌다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_18223_민준이와마산그리고건우 {
    static class Node implements Comparable<Node>{
        int v, cost;
        Node(int v, int cost){
            this.v = v;
            this.cost = cost;
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

        // 항상 1번 정점에서 P, V번 정점으로 갈 수 있는 경로가 존재함 (Integer.MaxValue * 2 처리 필요 X)
        if (P==1) System.out.print("SAVE HIM");
        else {
            boolean res = dijkstra(1, V) == dijkstra(1, P) + dijkstra(P, V);
            System.out.print(res ? "SAVE HIM" : "GOOD BYE");
        }
    }

    private static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0)); // 시작점(1번 노드)

        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if (dist[cur.v] < cur.cost) continue;
            if (cur.v == end) break;

            for (Node nextNode : arr.get(cur.v)){
                int nextCost = cur.cost + nextNode.cost;
                if (dist[nextNode.v] > nextCost){
                    dist[nextNode.v] = nextCost;
                    pq.offer(new Node(nextNode.v, nextCost));
                }
            }
        }

        return dist[end];
    }

    private static final StreamTokenizer st= new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
