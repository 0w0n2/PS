

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 사람 수
        int [] weight = new int[n]; // 무게 저장
        int [] height = new int[n]; // 키 저장
        for (int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            height[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0;i<n;i++){
            int rank = 1;
            for (int j=0;j<n;j++){
                if (j==i) continue;
                if (weight[i]<weight[j] && height[i]<height[j]) rank++;
            }
            bw.write(String.valueOf(rank)+" ");
        }

        bw.flush();
        bw.close();
    }
}
