

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // N개의 수
        HashMap<Integer, Integer> map = new HashMap<>(); // 최빈도 측정

        int [] num = new int [n];
        int sum = 0;
        for (int i=0;i<n;i++) {
            num[i] = Integer.parseInt(br.readLine());

            if (map.containsKey(num[i])){ // 빈도 업
                int replaceCount = map.get(num[i]) + 1;
                map.replace(num[i], replaceCount);
            } else {
                map.put(num[i], 1);
            }

            sum += num[i]; // 입력과 동시에 평균을 구하기 위한 총계 계산
        }
        // 산술 평균
        System.out.println(Math.round((double)sum/num.length));

        // 중앙값,범위 위해 오름차순 정렬
        Arrays.sort(num);

        // 중앙값
        System.out.println(num[num.length/2]);

        // 최빈값
        int max = 0;
        for (Integer i : map.keySet()) {
            max = Math.max(max, map.get(i));
        }

        ArrayList<Integer> mapAL = new ArrayList<>();
        for (Integer i : map.keySet()) {
            if (map.get(i)==max) mapAL.add(i);
        }

        if (mapAL.size()==1) System.out.println(mapAL.get(0));
        else {
            Collections.sort(mapAL);
            System.out.println(mapAL.get(1));
        }

        // 범위
        System.out.println(num[num.length-1]-num[0]);

    }
}
