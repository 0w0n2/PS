import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int [] num;
    static boolean [] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 수열 크기
        num = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=n;i++) num[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(num);

        isVisited = new boolean[n+1];
        StringBuilder sb = new StringBuilder();
        pp(0, "", sb);
        System.out.println(sb);
    }

    public static void pp(int count, String s, StringBuilder sb) {
        if (count == m) { // 수열 크기에 도달하면 결과를 저장
            sb.append(s).append("\n");
            return;
        }
        for (int i=1;i<=n;i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                String ns = "";
                if (count==0) ns = new StringBuilder(s).append(num[i]).toString();
                else ns = new StringBuilder(s).append(" ").append(num[i]).toString();
                pp(count+1, ns, sb);
                isVisited[i] = false;
            }
        }
    }
}
