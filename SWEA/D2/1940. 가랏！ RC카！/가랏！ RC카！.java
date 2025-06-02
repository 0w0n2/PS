import java.io.*;

public class Solution {

    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {

        /*
        0: 현재 속도 유지
        1: 가속
        2: 감속
        
        입력으로 주어진 N개의 명령을 모두 수행했을 때, N초 동안 이동한 거리는?
        - 2<=N<=30 의 정수
        - 가속도 : 1 또는 2 m/s^2
        - 현재 속도보다 감속할 속도가 더 클 경우, 속도는 0 m/s (음수 X)
         */
        
        int T = readInt(); // 테스트 케이스
        StringBuilder sb = new StringBuilder();

        for (int t=1;t<=T;t++){
            int N = readInt(); // 명렁 개수
            int speed = 0; // 시작 속도
            int distance = 0; // 이동 거리

            for (int i=0;i<N;i++){
                int cmd = readInt(); // 현재 명령어

                if (cmd==1) speed += readInt();
                else if (cmd==2) speed = Math.max((speed-readInt()), 0);

                distance += speed;
            }
            sb.append("#").append(t).append(" ").append(distance).append("\n");
        }
        System.out.print(sb);
    }
}

