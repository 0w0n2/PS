import java.util.*;
import java.io.*;

public class Main {
    static int n, t;
    static int[] money;
    static int[] prefix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        money = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) money[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(money);
        prefix = new int[n+1];
        for (int i=1;i<=n;i++) prefix[i] = prefix[i-1] + money[i-1]; // 누적합

        t = Integer.parseInt(br.readLine()); // 목표값

        System.out.print(solve(0, money[n-1]));

    }

    public static int solve(int left, int right){
        int result = 0;

        while(left<=right){
            int mid = (left+right)/2;
            int idx = binarySearchIdx(mid);
            int sum = prefix[idx] + mid*(n-idx);

            if (sum>t) right = mid-1;
            else {
                left = mid+1;
                result = mid;
            }
        }

        return result;
    }
    public static int binarySearchIdx(int target){
        int idx = n;

        int left = 0;
        int right = n-1;

        while(left<=right){
            int mid = (left + right) / 2;
            if (money[mid]>target){
                idx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return idx;
    }
}
