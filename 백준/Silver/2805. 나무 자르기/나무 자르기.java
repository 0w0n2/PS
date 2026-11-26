import java.util.*;
import java.io.*;

public class Main {
    // 1 <= N <= 1*10^6
    // 1 <= M <= 2*10^9 ; int 범위 내
    // 나무의 높이의 합은 항상 M보다 크거나 같다(못 가져가는 예외 X)
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws  IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = readInt(); // 나무의 수
        int M = readInt(); // 필요한 나무의 길이
        int[] heights = new int[N];

        for (int i=0; i<N; i++) heights[i] = readInt();
        Arrays.sort(heights);

        int right = heights[N-1] - 1; // 최대 높이: 가장 높은 나무 길이- 1
        int left = 0;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right-left)/2; // == (right+left)/2 ; int 오버플로우 방지

            long curSum = 0;
            for (int h : heights) {
                if (h>mid) curSum += h-mid;
                if (curSum>M) break;
            }

            if (curSum >= M) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.print(ans);

    }
}
