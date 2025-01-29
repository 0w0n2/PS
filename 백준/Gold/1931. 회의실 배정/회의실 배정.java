

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numMeeting = Integer.parseInt(br.readLine());
        int [][] meetingTime = new int [numMeeting][2];
        for (int i = 0; i < meetingTime.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetingTime[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
            meetingTime[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
        } // 입력 끝

        Arrays.sort(meetingTime, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]!=o2[1] ? o1[1]-o2[1] : o1[0]-o2[0];
            }
        }); // 종료 시간 빠른 기준으로 배열 정렬

        int count = 1;
        int currentLastTime = meetingTime[0][1];
        for (int i = 1; i < numMeeting; i++) {
            if (meetingTime[i][0]>=currentLastTime){    // 회의 시간이 안 겹칠 때(현재 등록된 회의의 끝나는 시간 <= 다음 예정 회의의 시작 시간), 가장 빨리 끝나는 회의를 예정 회의로 등록
                currentLastTime = meetingTime[i][1];
                count++;
            }
        }
        System.out.println(count);



    }
}
