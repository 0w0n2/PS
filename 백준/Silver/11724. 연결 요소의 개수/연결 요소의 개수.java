

import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] line = new ArrayList[1001];
    static boolean [] isVisited = new boolean[1001];

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점의 개수(N)
        int m = Integer.parseInt(st.nextToken()); // 간선의 개수(M)
        line = new ArrayList[1001];
        for(int i=0; i<1001; i++) line[i] = new ArrayList<Integer>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            line[u].add(v);
            line[v].add(u);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!isVisited[i]) {
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    public static void DFS(int v){
        isVisited[v] = true;
        for (int nv : line[v]){
            if (!isVisited[nv]) DFS(nv);
        }
    }
}
