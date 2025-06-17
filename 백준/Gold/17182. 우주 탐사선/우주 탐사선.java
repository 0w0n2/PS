import java.io.*;
import java.util.*;

public class Main {
    // 모든 행성을 탐사하는데 걸리는 최소시간
    // 탐색할 행성의 개수, 발사되는 행성의 위치, 행성간 이동하는데 걸리는 시간
    // 행성의 위치는 0부터 시작 0은 0번째 인덱스에 해당하는 행성
    // i번쨰 행성에서 j번째 행성에 도달하는데 걸리는 시간
    // i j가 같으면 항상 -이 주어짐
    // 모든행성을 탐사하는데 걸리는 최단시간

    // 행성의 개수 N 행성의 발사위치 K
    // i행성에서 j까지 걸리는 시간
    static int N;
    static int K;
    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node n){
            return this.weight-n.weight;
        }
    }
    static int[][] time;
    static List<List<Node>> list=new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        N=readInt();
        K=readInt();
        visited=new boolean[N];

        for(int i=0;i<N;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int num=readInt();
                if(i==j) continue;
                list.get(i).add(new Node(j,num));
            }
        }

        time=new int[N][N];
        for (int[] t:time) Arrays.fill(t,Integer.MAX_VALUE);
        for (int i=0;i<N;i++) dijkstra(i);

        isVisited = new boolean[N];
        isVisited[K] = true;
        recursive(K, 0, time[K][K]);
        System.out.print(result);
    }

    static int result = Integer.MAX_VALUE;
    static boolean[] isVisited;

    private static void recursive(int v, int ct, int sum){
        if (sum>=result) return;   // 가지치기
        if (ct==N-1) {
            result = sum;
            return;
        }

        for (int i=0;i<N;i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                recursive(i, ct+1, sum + time[v][i]);
                isVisited[i] = false;
            }
        }
    }

    private static void dijkstra(int i) {
        PriorityQueue<Node> priorityQueue=new PriorityQueue<>();
        priorityQueue.add(new Node(i,0));
        time[i][i] = 0;

        while (!priorityQueue.isEmpty()){
            Node n=priorityQueue.poll();

            if (time[i][n.to]<n.weight) continue;

            for(Node node:list.get(n.to)){

                int nt = time[i][n.to]+node.weight;
                if(time[i][node.to] > nt){
                    time[i][node.to] = nt;
                    priorityQueue.add(new Node(node.to,time[i][node.to]));
                }
            }
        }
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}