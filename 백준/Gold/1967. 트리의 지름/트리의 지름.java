import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();

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
        int maxWeight = 0;
        for (int i=1;i<=n;i++){
            boolean [] isVisited = new boolean[n+1];
            maxWeight = Math.max(maxWeight, dfs(i, isVisited));
        }
        System.out.println(maxWeight);
    }
    public static int dfs(int v, boolean[] isVisited){
        int maxWeight = 0;
        isVisited[v] = true;
        for (Node i : map.get(v)){
            if(!isVisited[i.nv]) {
                maxWeight = Math.max(maxWeight, dfs(i.nv, isVisited)+i.weight);
            }
        }
        return maxWeight;
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
