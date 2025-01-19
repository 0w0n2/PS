import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int p = Integer.MAX_VALUE;  // 패키지(6개묶음) 최저가
        int o = Integer.MAX_VALUE;  // 낱개 구매 최저가

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            p = Math.min(p, Integer.parseInt(st.nextToken())); // 역대 패키지 최저가 저장
            o = Math.min(o, Integer.parseInt(st.nextToken())); // 역대 낱개 구매 최저가 저장
            if (o*6<p) p = o*6; // 만약 낱개*6이 패키지보다 더 싸면 그 가격으로 업데이트
        }

        if(n%6==0) System.out.print(n/6 * p); 
        else System.out.print(Math.min(((n/6)+1)*p,(n/6)*p+(n%6)*o));
        // n이 6배수가 아닐 때, (1) 패키지 n/6 + 1개 사느냐? or (2) 패키지 n/6개 + 낱개 나머지 사느냐 -> 더 싼 거로
        // 근데!! 위 for 문에서 if (o*6<p) p = o*6; 해놨기 때문에 낱개*6 경우도 내포되어 있어서...
        // ㄴ 실질적으론 (1) 패키지 n/6 + 1개 사느냐? or (2) 패키지 n/6개 + 낱개 나머지 사느냐 or (3) 낱개 * n개 사느냐 -> 더 싼 거로 꼴입니다...
    }
}