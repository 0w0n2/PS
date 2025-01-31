import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] arr = new ArrayList[101];
    static boolean [] isVisited = new boolean[101];
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int node = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int line = Integer.parseInt(br.readLine()); // 연결된 쌍의 수
        // 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수는?

        for (int i=0;i<101;i++) arr[i] = new ArrayList<>();
        for (int i=0;i<line;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[u].add(v);
            arr[v].add(u);
        }
        DFS(1);
        System.out.print(count);
    }
    public static void DFS(int v){
        isVisited[v] = true;
        for(int nv : arr[v]){ // 현재 v(노드)와 연결된 노드들(nv)을 모두 확인한다(arr[v] : nv1, nv2, ...)
            if(!isVisited[nv]) {
                count++; // 방문 안 한 노드를 찾으면 그곳으로 이동하고, 카운트 +1
                DFS(nv);
            }
        }
    }
}
