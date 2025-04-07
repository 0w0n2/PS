

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int [][] dp = new int [101][100001]; // 몇번째 물건부터 선택했을 때, 가치의 최대는?
    static int N;
    static int K;
    static int [] weight = new int [101];
    static int [] value = new int [101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물품의 수 (최대 : 101)
        K = Integer.parseInt(st.nextToken()); // 최대 허용 무게 (최대 : 100,001)

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        } // 입력 완

        for (int i=1; i<=N; i++){
            for (int j=1; j<=K; j++){
                if (weight[i]<=j){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]]+value[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }

}
