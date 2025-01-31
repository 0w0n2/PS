
import java.util.*;
import java.io.*;
public class Main {
    static boolean [][] field;
    static boolean [][] isVisited;
    static int [] dx = {0, 1, 0, -1}; // 하, 우, 상, 좌
    static int [] dy = {1, 0, -1, 0};
    static int width;
    static int height;
    static int count;
    static ArrayList<Integer> Area = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());   // 직사각형 개수

        field = new boolean[height][width];
        isVisited = new boolean[height][width];

        // 직사각형 입력 처리
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()); // 왼쪽 아래 x좌표
            int y1 = Integer.parseInt(st.nextToken()); // 왼쪽 아래 y좌표
            int x2 = Integer.parseInt(st.nextToken()); // 오른쪽 위 x좌표
            int y2 = Integer.parseInt(st.nextToken()); // 오른쪽 위 y좌표

            // 직사각형의 해당 영역을 true로 표시
            for (int row = height - y2; row < height - y1; row++) { // y좌표를 행으로 반전
                for (int col = x1; col < x2; col++) { // x좌표를 열로
                    field[row][col] = true;
                }
            }
        }

//        // 필드 출력 (디버깅 용)
//        for (int row = 0; row < height; row++) {
//            for (int col = 0; col < width; col++) {
//                System.out.print((field[row][col] ? 1 : 0) + " ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!field[i][j] && !isVisited[i][j]) { // (i,j)가 방문 안 한 구역, 그리고 채워지지 않은(false) 구역이라면 탐색 시작
                    count = 0; // 넓이 카운팅 초기화
                    DFS(i, j);
                    Area.add(count); // 구해진 넓이를 리스트에 추가
                }
            }
        }
        Collections.sort(Area); // 넓이 리스트 오름차순 정렬
        System.out.println(Area.size());
        for (Integer i : Area) System.out.print(i+" ");

    }

    public static void DFS(int x, int y){
        isVisited[x][y] = true; // 방문함(x,y)
        count++; // 넓이 +1

        for (int i=0;i<4;i++){  // 상하좌우 움직임
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx<0 || nx>=height || ny<0 || ny>=width) continue;  // field를 벗어났을 때는 무시
            if (!isVisited[nx][ny] && !field[nx][ny]) DFS(nx, ny);  // 갈 수 있는 곳(한 번도 방문 안 한 곳 & 직사각형으로 채워지지 않은 곳)이라면 ㄱㄱ
        }
    }
}
