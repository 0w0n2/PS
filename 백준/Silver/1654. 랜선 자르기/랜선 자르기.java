

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long thisLength;
    static long [] lengthRan;
    static long N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Long.parseLong(st.nextToken());
        lengthRan = new long [K];
        for (int i=0;i<K;i++) lengthRan[i] = Long.parseLong(br.readLine());

        thisLength = 1;
        long end = (long)Math.pow(2,31);

        selectLength(thisLength, end);

        System.out.println(thisLength);
    }

    public static void selectLength(long start, long end){
        if (start>end){
            thisLength = start -1;
            return;
        }
        long middlePoint = start + (end-start)/2;
        long count = 0;
        for (int i = 0; i<K; i++){
            count += (lengthRan[i]/middlePoint);
        }

        if (count<N){
            // 선택된 후보가 정답보다 크다 -> 랜선이 더 짧아야 한다
            selectLength(start, middlePoint-1);
        } else {
            // 선택된 후보가 정답보다 작다
            selectLength(middlePoint+1, end);
        }

    }
}
