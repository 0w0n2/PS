import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] num = new int [n];
        for (int i=0;i<n;i++) num[i] = Integer.parseInt(st.nextToken());

        
        int max = 0;
        int [] dp = new int[n];
        for (int i=0;i<n;i++){
            dp[i] = 1;
            for (int j=0;j<i;j++){
                if (num[j]<num[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }

}
