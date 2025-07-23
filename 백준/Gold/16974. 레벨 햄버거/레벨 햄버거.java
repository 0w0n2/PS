import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main {

    private static long dp[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 레벨
        long X = Long.parseLong(st.nextToken()); // 먹은 개수

        dp = new long[N+1];
        dp[0] = 1; // P(패티)의 개수 -> dp[i] = dp[i-1]*2+1; ... 매 레벨의 중간 지점 : dp[i-1]+1;
        for (int i=1;i<=N;i++) dp[i] = dp[i-1]*2 + 1;

        // 상도가 먹은 패티의 수를 계산 -> 분할 정복(매 레벨의 중간 지점을 기준으로 비교)
        System.out.print(divide(N, X));
    }

    private static long divide(int L, long idx){
        // 기저조건
        if (idx==0) return 0;
        if (L==0) return dp[L];

        if (idx>dp[L]) return dp[L-1]+1+divide(L-1, idx-dp[L]); // 현재 레벨 중간 지점보다 더 먹음
        else if (idx==dp[L]) return dp[L-1]+1; // 딱 현재 레벨 중간 지점까지 먹었다
        else return divide(L-1, idx-1); // 현재 레벨 중간 지점보다 덜 먹음
    }
}
