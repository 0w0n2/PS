
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [][] field = new int[n][m];
        ArrayList<Integer>[] shark = new ArrayList[2];
        shark[0] = new ArrayList<>();
        shark[1] = new ArrayList<>();

        for (int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<m;j++){
                field[i][j] = Integer.parseInt(st.nextToken());
                if (field[i][j]==1) {
                    shark[0].add(i);
                    shark[1].add(j);
                }
            }
        }

        int safeDistance = 0;
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                int safeNow = Integer.MAX_VALUE;
                if (field[i][j]==0){
                    for (int s=0;s<shark[0].size();s++){
                        int x = Math.abs(shark[0].get(s)-i);
                        int y = Math.abs(shark[1].get(s)-j);
                        int distance = Math.max(x, y);
                        safeNow = Math.min(safeNow, distance);
                    }
                    safeDistance = Math.max(safeDistance, safeNow);
                }
            }
        }
        System.out.println(safeDistance);

    }
}
