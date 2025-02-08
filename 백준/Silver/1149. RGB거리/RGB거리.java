import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 집 개수
        int [][] costs = new int[n+1][3]; // 집 칠하는 비용
        int [][] dp = new int[1001][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;

        for (int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
        }
        for (int i=1; i<=n; i++){
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0]; //앞이 1이거나 2
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1]; //앞이 0이거나 2
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + costs[i][2]; // 앞이 1이거나 0
        }
        System.out.print(Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]));
    }

}
