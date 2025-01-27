import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static int deep;
    static int radius;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i=0;i<=n;i++) map.add(new ArrayList<>());

        for (int i=0;i<n-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map.get(v).add(new Node(u, w));
            map.get(u).add(new Node(v, w));
        }

        boolean[] isVisited = new boolean[n + 1];
        radius = 0;
        dfs(1, isVisited, 0);

        isVisited = new boolean[n+1];
        radius = 0;
        dfs(deep, isVisited, 0);
        System.out.println(radius);
    }
    public static void dfs(int v, boolean[] isVisited, int w){
        isVisited[v] = true;

        if (w>radius){
            radius = w;
            deep = v;
        }

        for (Node next : map.get(v)){
            if(!isVisited[next.nv]) {
                dfs(next.nv, isVisited, w+next.weight);
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
