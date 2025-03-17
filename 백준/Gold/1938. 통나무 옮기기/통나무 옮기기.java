import java.util.*;
import java.io.*;

public class Main {
	static class Coordinate{
		int x, y;
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
    static class Node {
        int x, y, ct;
        boolean horizon; // 가로(true) / 세로(false) 상태 저장

        Node(int x, int y, int ct, boolean horizon) {
            this.x = x;
            this.y = y;
            this.ct = ct;
            this.horizon = horizon;
        }
    }

    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    
    static int N;
    
    static char[][] map;
    
    static boolean[][][] isVisited; // 방문 체크 [x][y][가로(0)/세로(1)]
    
    static Deque<Node> q = new ArrayDeque<>();
    static Node start, end; // 시작점과 목표점

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        isVisited = new boolean[N][N][2]; // 0: 가로, 1: 세로
        
        Coordinate[] s = new Coordinate[3];
        Coordinate[] e = new Coordinate[3];
        List<int[]> startList = new ArrayList<>();
        List<int[]> endList = new ArrayList<>();
        
        int idxS = 0, idxE = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'B') s[idxS++] = new Coordinate(i, j);
                if (map[i][j] == 'E') e[idxE++] = new Coordinate(i, j);
            }
        }
	
        isVisited[s[1].x][s[1].y][(s[0].x==s[1].x) ? 0:1] = true; // 시작 지점 방문 처리
        q.offer(new Node(s[1].x, s[1].y, 0, (s[0].x == s[1].x)));
 
        end = new Node(e[1].x, e[1].y, 0, (e[0].x==e[1].x));
        bfs();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node c = q.pollFirst();

            if (c.x == end.x && c.y == end.y && c.horizon == end.horizon) {
                System.out.print(c.ct);
                return;
            }

            // 네 방향 이동
            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];

                if (!canMove(nx, ny, c.horizon)) continue;
                if (!isVisited[nx][ny][c.horizon ? 0 : 1]) {
                    isVisited[nx][ny][c.horizon ? 0 : 1] = true;
                    q.offerLast(new Node(nx, ny, c.ct+1, c.horizon));
                }
            }

            // 회전 (가로 <-> 세로 변경)
            if (canRotate(c.x, c.y)) {
                if (!isVisited[c.x][c.y][!c.horizon ? 0 : 1]) {
                    isVisited[c.x][c.y][!c.horizon ? 0 : 1] = true;
                    q.offerLast(new Node(c.x, c.y, c.ct+1, !c.horizon));
                }
            }
        }
        System.out.print(0); // 이동 불가능한 경우
    }

    // 상하좌우 이동 가능 여부
    static boolean canMove(int x, int y, boolean horizon) {
        if (horizon) return isValid(x, y-1) && isValid(x, y) && isValid(x, y+1); // 가로일 경우
        else return isValid(x-1, y) && isValid(x, y) && isValid(x+1, y); // 세로일 경우
    }

    // 회전 가능 여부
    static boolean canRotate(int x, int y) {
        for (int i=x-1; i<=x+1; i++) {
            for (int j=y-1; j<=y+1; j++) {
                if (!isValid(i, j)) return false; // 3x3 범위 내에 장애물이 있으면 회전 불가능
            }
        }
        return true;
    }

    // 좌표가 유효한지 확인
    static boolean isValid(int x, int y) {
        return !(x<0||x>=N||y<0||y>=N||map[x][y]=='1');
    }
}
