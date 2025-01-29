package d21_40.d36_250129;

import java.io.*;
import java.util.*;

public class P1_18870_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] original = new int[n];
        int[] sorted = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) original[i] = sorted[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(sorted); // 내림차순 정렬

        HashMap<Integer, Integer> rankMap = new HashMap<>(); // 정렬된 서로 다른 숫자가 각각 몇 번째 등수인지 기록 (중복 숫자는 같은 등수라 생략하기 위해 해시맵 씀)
        int rank = 0;
        for (int num : sorted){
            if (!rankMap.containsKey(num)) rankMap.put(num, rank++); // 후위증가
        }
        for (int num : original) bw.write(rankMap.get(num)+" "); // 맵에서 해당 숫자가 몇 번째 등수인지 찾고, 출력

        bw.flush();
        bw.close();
    }
}
