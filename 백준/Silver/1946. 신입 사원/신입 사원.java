
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        for (int i=0;i<testCase;i++){
            int p = Integer.parseInt(br.readLine());
            int [][] rank = new int[p][2];
            for (int j=0;j<p;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                rank[j][0] = Integer.parseInt(st.nextToken());
                rank[j][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(rank, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0]-o2[0];
                }
            });
            int c = 1;
            int small = rank[0][1];
            for (int j=1;j<p;j++){
                if (rank[j][1]<small) {
                    c++;
                    small = rank[j][1];
                }
            }
            bw.write(String.valueOf(c)+"\n");
        }
        bw.flush();
        bw.close();
    }
}
