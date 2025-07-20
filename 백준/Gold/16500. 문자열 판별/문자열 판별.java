import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        S = readString();
        int N = readInt();
        for (int i=0;i<N;i++) A.add(readString());
        System.out.print(S.length()==0 ? 0 : sol());
    }

    private static String S;
    private static HashSet<String> A = new HashSet<>();

    private static int sol() throws IOException{
        int sLen = S.length();
        int[] dp = new int[sLen+1];
        dp[0] = 1;

        for (int i=0;i<sLen;i++){ // 검사 시작지점
            if (dp[i]==0) continue; // 이미 판별된 구간은 뛰어넘기
            for (String a : A){
                int end = i + a.length();
                if (end>sLen) continue;
                if (S.startsWith(a, i)){
                    dp[end] = 1;
                    if (end==sLen) return 1;
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
