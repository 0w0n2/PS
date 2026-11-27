import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int N, M;
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        for (int i=0; i<N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++) {
            int a = readInt()-1;
            int b = readInt()-1;
            // 양방향 간선
            map.get(a).add(b);
            map.get(b).add(a);
        }

        // 1부터 각 헛간 별 거리 계산 및 결과 출력
        System.out.print(bfs());

    }
    private static String bfs() {
        boolean[] isVisited = new boolean[N];
        isVisited[0] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);

        int curDist = -1, minIdx = 0, ct = 0;

        while (!q.isEmpty()) {
            curDist++;
            int size = ct = q.size();   // 이 거리의 헛간 수
            minIdx = Integer.MAX_VALUE; // 이 거리에서 가장 작은 번호

            while (size-- > 0) {
                int curIdx = q.poll();
                minIdx = Math.min(minIdx, curIdx);

                for (int nextIdx : map.get(curIdx)) {
                    if (isVisited[nextIdx]) continue;
                    isVisited[nextIdx] = true;
                    q.offer(nextIdx);
                }
            }
        }

        return String.format("%d %d %d", minIdx+1, curDist, ct);
    }
}
