
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int step = 1;
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken()); // L일 동안만 사용 가능
            int P = Integer.parseInt(st.nextToken()); // 연속하는 P일 중
            int V = Integer.parseInt(st.nextToken()); // 휴가기간
            if (L==0 && P==0 && V==0) break;        // 입력 탈출 조건
            
            bw.write("Case " + String.valueOf(step) + ": " + String.valueOf((V/P)*L + Math.min(L, V%P)) + "\n");
            step++;
        }
        bw.flush();
        bw.close();
    }
}
