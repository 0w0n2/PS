import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        char[] S = readString().toCharArray();
        HashSet<String> A = new HashSet<>();
        int N = readInt();
        for (int i=0;i<N;i++) A.add(readString());
        System.out.print(sol(S, A));
    }

    private static int sol(char[] S, HashSet<String> A) throws IOException{
        int sLen = S.length;
        int[] dp = new int[sLen+1];
        dp[0] = 1;

        for (int i=0;i<sLen;i++){ // 검사 시작지점
            if (dp[i]==0) continue; // 이미 판별된 구간은 뛰어넘기
            for (int j=i+1;j<=sLen;j++){ // S[j:k] 슬라이싱
                StringBuilder sb = new StringBuilder();
                for (int k=i;k<j;k++) sb.append(S[k]);
                if (A.contains(sb.toString())){
                    dp[j] = 1;
                    if (j==sLen) return 1;
                }
            }
        }

        return dp[sLen];
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static String readString() throws IOException{
        st.nextToken();
        return st.sval;
    }
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
