import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] lecture;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 강의의 수
        m = Integer.parseInt(st.nextToken()); // 블루레이의 개수
        lecture = new int[n];
        int lecSum = 0;
        int lecMax = -1;
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            lecture[i] = Integer.parseInt(st.nextToken());
            lecMax = Math.max(lecMax, lecture[i]);
            lecSum += lecture[i];
        }
        System.out.print(solve(lecMax, lecSum));

    }

    static int solve(int left, int right){
        while(left<=right){
            int mid = (right+left)/2;
            int count = 1;
            int nowSum = 0;
            for (int i=0;i<n;i++){
                if (nowSum+lecture[i]>mid){
                    count++;
                    nowSum = lecture[i];
                    if (count>m) break;
                } else {
                    nowSum += lecture[i];
                }
            }
            if (count<=m) right = mid-1;
            else left = mid+1;
        }
        return right+1;
    }
}
