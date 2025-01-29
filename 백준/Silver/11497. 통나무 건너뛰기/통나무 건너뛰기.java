import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); // 출력용
        int tc = Integer.parseInt(br.readLine());
        for (int t=0;t<tc;t++){

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> num = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 큐 정렬조건 내림차순

            for (int i=0;i<n;i++) {
                num.offer(Integer.parseInt(st.nextToken())); // 입력 받은 수들이 내림차순으로 큐에 담김
            }

            ArrayList<Integer> sorted = new ArrayList<>(); // 문제 조건(인접한 수들의 차이가 최소)에 맞춰 수 정렬할 리스트
            sorted.add(num.poll()); // 가장 큰 수(우선순위 큐의 0번째(선입선출)를 리스트에 입력

            for (int i=1;i<n;i++){ 
                // sorted.get(0) = 현재 리스트의 가장 왼쪽
                // sorted.get(sorted.size()-1) = 현재 리스트의 가장 오른쪽
                // 왼쪽, 오른쪽 중 더 큰 수가 있는 위치에 우선 순위 큐에서 뽑은 수를 넣어줌(그리디) 
                if(sorted.get(0)>sorted.get(sorted.size()-1)) sorted.add(0, num.poll());
                else sorted.add(num.poll());
            }

            int maxDiff = Math.abs(sorted.get(0)-sorted.get(n-1)); // 첫번째 통나무와 마지막 통나무는 인접하다고 판정됨, 그 둘의 차이
            for (int i=1;i<n;i++){ // 첫번째 -> 두번째 -> .. -> 마지막 통나무까지 인접한 통나무들의 차이값의 최대값을 탐색
                maxDiff = Math.max(maxDiff, Math.abs(sorted.get(i) - sorted.get(i-1)));
            }

            sb.append(maxDiff).append("\n"); // 출력

        }

        System.out.println(sb);
    }
}
