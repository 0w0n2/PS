import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static int radius = 0;
    static boolean [] isVisited;
    static int deep = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 트리의 정점의 개수
        for (int i=0;i<=n;i++) map.add(new ArrayList<>());

        for (int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while(true){
                int x = Integer.parseInt(st.nextToken());
                if (x==-1) break;
                int w = Integer.parseInt(st.nextToken());
                map.get(node).add(new Node(x, w));
            }
        }

        isVisited = new boolean[n+1];
        dfs(1, 0);

        isVisited = new boolean[n+1];
        radius = 0;
        dfs(deep, 0);

        System.out.println(radius);
    }

    public static void dfs(int v, int r){
        isVisited[v] = true;

        if (r>radius){
            radius = r;
            deep = v;
        }

        for (Node next : map.get(v)){
            if (!isVisited[next.nv]){
                dfs(next.nv, r+next.weight);
                isVisited[next.nv] = false;
            }
        }
    }

    static class Node{
        int nv;
        int weight;
        public Node(int nv, int weight){
            this.nv = nv;
            this.weight = weight;
        }
    }
}
