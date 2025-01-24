

import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] T, P;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        T = new int[n];
        P = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        } // 입력

        cal(0, 0);
        System.out.println(max);
    }

    public static void cal(int day, int sum) {
        // 현재 날짜가 N일을 넘어가는 경우 수익의 최대값 책정
        if (day >= n) {
            max = Math.max(max, sum);
            return;
        }

        // 그날 상담 선택 (But, 상담 후 날짜가 퇴사일(N+1)을 넘기면 X
        if (day + T[day] <= n) { 
            cal(day + T[day], sum + P[day]);
        }

        // 그날 상담 선택 ㄴㄴ
        cal(day + 1, sum);
    }
}
