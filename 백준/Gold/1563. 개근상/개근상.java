import java.util.*;
import java.io.*;

public class Main {
    final static int MOD = 1_000_000, MAX_LATE = 1, MAX_ABSENT = 2;
    // 개근상 못 받음 : 지각 두 번 이상 or 결석 세번 연속

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[2][MAX_LATE+1][MAX_ABSENT+1]; // [일수][지각수(0~1)][연속 결석수(0~2)]
        dp[0][0][0] = 1;

        for (int day=1;day<=N;day++){
            int cur = day&1;
            int prev = cur^1;
            for (int l=0;l<=MAX_LATE;l++) Arrays.fill(dp[cur][l], 0);
            for (int l=0;l<=MAX_LATE;l++){
                for (int a=0;a<=MAX_ABSENT;a++){
                    if (dp[prev][l][a]==0) continue;
                    dp[cur][l][0] = (dp[cur][l][0] + dp[prev][l][a]) % MOD; // 출석(지각 유지, 결석 초기화)
                    if (a<MAX_ABSENT) dp[cur][l][a+1] = (dp[cur][l][a+1] + dp[prev][l][a]) % MOD; // 결석 (지각 수 그대로, 연속 결석 수+1)
                    if (l<MAX_LATE) dp[cur][l+1][0] = (dp[cur][l+1][0] + dp[prev][l][a]) % MOD; // 지각 (지각 수+1, 결석 수 0으로)
                }
            }
        }
        int result = 0;
        for (int[] i:dp[N%2]) for (int j:i) result = (result+j) % MOD;
        System.out.print(result);

    }
}
