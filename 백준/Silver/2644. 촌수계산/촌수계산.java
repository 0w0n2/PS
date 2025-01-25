import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<ArrayList<Integer>> ip = new ArrayList<>();
    static boolean check = false;
    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 전체 사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine()); // 쌍 수
        for (int i=0;i<=n;i++) ip.add(new ArrayList<>()); // n+1개로 초기화

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            ip.get(v).add(u);
            ip.get(u).add(v);
        } // 리스트 입력
        bfs(start, end);
        if (!check) System.out.print(-1);

    }

    public static void bfs(int start, int end){
        int [] isVisited = new int[n+1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        isVisited[start] = 0;

        while(!q.isEmpty()){
            int v = q.poll();
            if (v==end){
                System.out.print(isVisited[v]);
                check = true;
                return;
            }
            for (int i=0;i<ip.get(v).size();i++){
                int nv = ip.get(v).get(i);
                if(isVisited[nv]==0){
                    q.offer(nv);
                    isVisited[nv] = isVisited[v] + 1;
                }
            }
        }
    }
}
