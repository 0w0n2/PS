

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] field = new int [101][101];
        for (int step=0;step<N;step++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    field[i][j] = 1;
                }
            }
        }
        int result = 0;
        for (int[] ints : field) {
            for (int anInt : ints) {
                if (anInt!=0) result ++;
            }
        }
        System.out.println(result);
    }
}
