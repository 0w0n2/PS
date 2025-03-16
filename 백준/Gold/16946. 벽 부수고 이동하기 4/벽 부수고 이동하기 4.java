import java.util.*;
import java.io.*;

public class Main {
    
    static HashMap<Integer, Integer> area = new HashMap<>();
    static int n = 1, N, M, map[];  // 1차원 배열로 변경
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N * M];  // 1차원 배열로 변경

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i * M + j] = line.charAt(j) - '0';  // 1차원 인덱스로 저장
            }
        }

        // 2. 0인 곳에 대해 BFS를 실행하여 영역 구분
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i * M + j] == 0) {
                    n++;
                    bfs(i, j);
                }
            }
        }

        // 3. 벽(1)인 곳의 값 갱신
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> use = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i * M + j] != 1) sb.append(0);
                else {
                    int sum = 1;
                    use.clear();

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx * M + ny] == 1) continue;
                        if (use.contains(map[nx * M + ny])) continue;

                        sum += area.get(map[nx * M + ny]);
                        use.add(map[nx * M + ny]);
                    }
                    sb.append(sum % 10);
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static class Node {
        int index;
        Node(int index) {
            this.index = index;
        }
    }

    static Deque<Integer> q = new ArrayDeque<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static void bfs(int sx, int sy) {
        q.clear();
        int startIndex = sx * M + sy;
        q.offerLast(startIndex);
        map[startIndex] = n;

        int ct = 1;

        while (!q.isEmpty()) {
            int c = q.pollFirst();
            int x = c / M;
            int y = c % M;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx * M + ny] != 0) continue;
                map[nx * M + ny] = n;
                q.offerLast(nx * M + ny);
                ct++;
            }
        }

        area.put(n, ct);
    }
}
