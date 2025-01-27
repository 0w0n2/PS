import java.util.*;
import java.io.*;

public class Main {
    static int [] dp;
    static int [] num;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int [n];
        dp = new int [n];
        for (int i=0;i<n;i++) num[i] = Integer.parseInt(st.nextToken());

        int max = 0;
        for (int i=0;i<n;i++){
            max = Math.max(max, LIS(i));
        }
        System.out.println(max);
    }

    public static int LIS(int i){

        if (dp[i]!=0) return dp[i];

        dp[i] = 1;
        for (int j=0;j<i;j++){
            if (num[j]<num[i]) dp[i] = Math.max(dp[i], LIS(j)+1);
        }

        return dp[i];
    }

}
