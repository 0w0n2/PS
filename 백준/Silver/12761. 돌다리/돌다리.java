import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    private static final int MAX_LENGTH = 100_000;  // 돌다리의 최대 길이 (0~10^5)

    public static void main(String[] args) throws IOException {
        final int A = readInt();
        final int B = readInt();
        final int N = readInt(); // 동규 현재 위치
        final int M = readInt(); // 주미 현재 위치

        System.out.print(solution(A, B, N, M));
    }

    // start==target가 되기 위한 최소한의 이동횟수 리턴
    private static int solution(int a, int b, int start, int target) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[MAX_LENGTH + 1];

        Arrays.fill(dist, -1);
        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur==target) {
                return dist[cur];
            }

            int[] next = {cur+1, cur-1, cur-a, cur-b, cur+a, cur+b, cur*a, cur*b}; // 8가지 방법(음수 곱셈은 X)
            for (int n : next) {
                if (n<0 || n>MAX_LENGTH || dist[n]!=-1) {
                    continue;
                }

                dist[n] = dist[cur] + 1;
                q.add(n);
            }
        }

        return 0;
    }
}
