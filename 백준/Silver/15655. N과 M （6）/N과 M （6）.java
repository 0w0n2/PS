import java.util.*;
import java.io.*;

public class Main {
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        nums = new int[N];
        // N개의 자연수 중에서 M개를 고른 수열
        // 고른 수열은 오름차순이어야 함
        // 중복되는 수열 X
        for (int i=0;i<N;i++) nums[i] = readInt();
        Arrays.sort(nums);

        combination(0, 0, new StringBuilder());
        System.out.println(sb);
    }

    static int nums[], N, M;
    static StringBuilder sb = new StringBuilder();

    private static void combination(int st, int ct, StringBuilder selection){
        if (ct==M){
            sb.append(selection).append("\n");
            return;
        }

        for (int i=st;i<N;i++){
            int prevLen = selection.length();
            combination(i+1, ct+1, selection.append(nums[i]).append(" "));
            selection.setLength(prevLen);
        }
    }
}
