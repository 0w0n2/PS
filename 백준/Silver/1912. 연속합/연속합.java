import java.io.*;
import java.util.*;

/**
 * - 한 개 이상의 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구한다.
 *
 */
public class Main {
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int cur = readInt();
        int ans = cur;  // 0부터 시작 X (최댓값이 음수인 경우 예외)

        for (int i=1; i<n; i++) {
            int a = readInt();
            cur = Math.max(cur+a, a);   // 이번 숫자를 더하거나 or 이전 저장 숫자를 모두 버림
            ans = Math.max(ans, cur);
        }

        System.out.print(ans);
    }
}
