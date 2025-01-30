import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 회의 개수
        PriorityQueue<int []> meet = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1]!=o2[1]? o1[1]-o2[1]:o1[0]-o2[0];
            }
        }); // 큐 정렬 1순위 : 회의 종료 빠른 순([1]), 2순위 : 회의 시작 빠른 순([0])
        for (int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            meet.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        int res = 1; // 결과
        int end = meet.poll()[1]; // 첫 회의의 종료 시간 저장
        int size = meet.size(); // 남은 미팅 후보 개수
        for (int i=0;i<size;i++){
            int[] nowMeeting = meet.poll(); // 미팅 선택
            if (end<=nowMeeting[0]){ 
                res++;
                end = nowMeeting[1]; // 해당 미팅의 종료 시간으로 업데이트
            }
        }
        System.out.println(res);
    }
}
