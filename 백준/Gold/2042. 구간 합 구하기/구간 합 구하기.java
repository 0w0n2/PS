import java.util.*;
import java.io.*;

public class Main {

    static class SegTree{
        int size, start;
        long[] arr;

        SegTree(int n){
            size = n;
            start = 1;
            // start : 리프 노드(사이즈 1인 노드)가 시작하는 인덱스로, start 는 2의 거듭제곱 중 size 이상인 최소값이 되어야 한다. (2^start >= size)
            // size : 전체 트리 크기 = 2*start (완전이진트리 구조 / [1, start*2 -1])
            while (start < size) start *= 2;        // 리프노드 인덱스 구하기(start)
            arr = new long[start * 2];         // 전체 트리 배열(size = start*2)
        }

        // 리프 노드들의 값을 미리 준 후(st.arr[st.start + i] = readLong();), 해당 값들로 세그먼트를 구축(construct())
        // 아래에서부터 위로 계산
        // [1, start-1] 구간에 리프 노드가 존재할 때, 그 바로 위 노드들부터 인덱스가 감소하는 순으로 배열의 값을 구하는 것
        void construct(){
            for (int i=start-1; i>0; i--) arr[i] = arr[i*2] + arr[i*2+1];
            // [i*2], [i*2+1]이 자식 노드이므로, 그 합을 부모 노드인 [i]에 저장
        }

        // i: 실제 배열 인덱스(세그먼트 트리에서는 start+i)
        // 해당 리프 노드를 val 로 갱신하고, 그 변화량 (delta) 만틈 부모로 타고 올라가서 전체 노드 값을 반영
        void update(int i, long val){
            i += start;    // 세그먼트 트리에서의 인덱스
            val -= arr[i];
            // 그냥 arr[idx] = val 해버리면 부모 노드들은 얼마만큼 변화됐는지 모르니까
            // 차이값 delta = val - arr[idx] 를 구해서 상위 노드들에 더해주는 방식
            while (i > 0) { // 루트 노드까지
                arr[i] += val;    // 변화량을 더해줌
                i /= 2;
            }
        }

        public long sum(int a, int b) {
            return sum(a, b, 1, 0, start);
        }

        // a, b : 구하려는 구간 범위
        // node : 현재 보고 있는 노드 번호
        // na, nb : 현재 보고 있는 구간의 시작 인덱스, 끝 인덱스
        private long sum(int a, int b, int node, int na, int nb){
            if (b<=na || nb<=a) return 0;           // 범위 밖에 있는 경우
            if (a<=na && nb<=b) return arr[node];   // 범위 안에 완전히 포함 되는 경우

            // 그렇지 않다면 현재 노드의 구간을 두 부분으로 나누어 합을 구하기
            // 구간을 2등분하면 왼쪽 것은 이 노드의 왼쪽 자식, 오른쪽 것은 오른쪽 자식이 표현하고 있음
            //    ㅇ ㅇ
            //  ㅇ ㄴ ㄴ ㅇ
            int mid = (na+nb)/2;
            return sum(a, b, node*2, na, mid) + sum(a, b, node*2 + 1, mid, nb);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken()); // N개의 수
        int M = Integer.parseInt(stk.nextToken()); // 수의 변경이 일어나는 횟수
        int K = Integer.parseInt(stk.nextToken()); // 구간의 합을 구하는 횟수
        SegTree st = new SegTree(N);

        for (int i=0;i<N;i++) st.arr[st.start + i] = Long.parseLong(br.readLine()); // 리프 노드들의 값을 미리 줌
        st.construct(); // 미리 준 리프 노드 값으로 윗 줄부터 루트까지 전체 세그먼트 트리 구축

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<M+K;i++){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            if (a==1){
                int b = Integer.parseInt(stk.nextToken());
                long c = Long.parseLong(stk.nextToken());
                st.update(b-1, c);
            } else {
                int b = Integer.parseInt(stk.nextToken());
                int c = Integer.parseInt(stk.nextToken());
                sb.append(st.sum(b-1, c)).append("\n");
            }
        }
        System.out.print(sb);
    }
}
