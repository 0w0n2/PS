

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numJewel = Integer.parseInt(st.nextToken());
        int numBag = Integer.parseInt(st.nextToken());
        int [][] information = new int [numJewel][2];   // 보석 무게와 가격
        int [] weightBag = new int [numBag];            // 별 가방 무게

        for (int i = 0; i < numJewel; i++) {
            st = new StringTokenizer(br.readLine());
            information[i][0] = Integer.parseInt(st.nextToken()); // 무게 (0)
            information[i][1] = Integer.parseInt(st.nextToken()); // 가격 (1)
        }
        for (int i = 0; i < numBag; i++) {
            weightBag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(information, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
                // 보석 우선순위 정렬 : 무게 제일 가벼운 거
            }
        });

        Arrays.sort(weightBag); // 가방 무게 정렬 : 제일 가벼운 거 순

        boolean [] used = new boolean[numJewel]; // 기본값 : false
        long money = 0L;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // 우선순위 큐에는 가격이 들어감, 가격 높은 순으로 정렬
        int jewelIndex = 0;

        for (int bagWeight : weightBag) {
            // 가방 무게보다 더 작은 모든 보석을 큐에 추가
            while (jewelIndex<numJewel && information[jewelIndex][0]<=bagWeight){
                pq.offer(information[jewelIndex][1]);
                jewelIndex++;
            }

            if (!pq.isEmpty()){
                money+=pq.poll();
            }

        }

        bw.write(String.valueOf(money));
        bw.flush();
        bw.close();

    }
}
