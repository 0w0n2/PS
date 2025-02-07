import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 상자의 개수 n
        int k = Integer.parseInt(st.nextToken()); // 규칙의 개수 k
        int d = Integer.parseInt(st.nextToken()); // 도토리 개수 d

        ArrayList<int[]> rules = new ArrayList<>(); // 규칙 넣기

        for (int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 첫 수
            int b = Integer.parseInt(st.nextToken()); // 끝 수
            int c = Integer.parseInt(st.nextToken()); // 간격
            rules.add(new int[] {a, b, c});
        }
        int start=0;
        int end =n;
        while(start<end) { // 박스 숫자를 고르자~~

            int box = (start+end)/2; // 박스 숫자

            long nowDotori = 0;

            for (int i=0;i<k;i++){
                int [] current = rules.get(i); // 현재 검사할 순번의 규칙의 a, b, c를 꺼내온다
                int a = current[0];
                int b = current[1];
                int c = current[2];
                if (a<=box){ // 선택한 박스가 a보다 클 때, a와 박스(또는 b) 사이에 있는 모든 박스에 도토리가 하나씩 들어있음
                    nowDotori += (Math.min(box, b)-a)/c + 1;
                }
            }

            if (nowDotori>=d){ // 도토리 개수 충분 -> 범위 왼쪽 탐색(start=end 가 될 때까지)
                end = box;
            } else { // 도토리 개수 부족 -> 더 큰 상자 번호를 찾아야 함
                start = box+1;
            }
        }
        System.out.println(end);
    }
}

