import java.util.*;
import java.io.*;

public class Main {
    
    private static class Node implements Comparable<Node>{
        int n, idx;
        Node(int n, int idx){
            this.n = n;
            this.idx = idx;
        }
        @Override
        public int compareTo(Node o) {
            return this.n!=o.n ? Integer.compare(this.n, o.n) : Integer.compare(this.idx, o.idx);
        }
    }
    
    private static class SegTree{
        int size, start;
        long[] arr; // 1<=N<=500_000
        SegTree(int N){
            size = N;
            start = 1;
            while(start < size) start *= 2;
            arr = new long[start * 2];
        }
        // construct 필요 X
        void update(int i){
            i += start;
            long val = 1;
            while (i>0){
                arr[i] += val;
                i/=2;
            }
        }
        long sum(int a, int b){
            return sum(a, b, 1, 0, start);
        }
        long sum(int a, int b, int node, int na, int nb){
            if (b<=na || nb<=a) return 0;
            if (a<=na && nb<=b) return arr[node];

            int mid = (na+nb)/2;
            return sum(a, b, node*2, na, mid) + sum(a, b, node*2+1, mid, nb);
        }
    }
    
    public static void main(String[] args) throws IOException {
        int N = readInt();
        SegTree st = new SegTree(N);

        Node[] nums = new Node[N];
        for (int i=0;i<N;i++) nums[i] = new Node(readInt(), i);
        Arrays.sort(nums);
        
        long ct = 0;
        // 작은 수부터 Swap 횟수 구함 (Swap = nums.idx - [0, nums.idx]까지의 기존 구간합
        // 구간합 구한 후 segTree 업데이트
        for (int i=0;i<N;i++){
            Node cur = nums[i];
            ct += st.sum(cur.idx+1, N);
            st.update(cur.idx);
        }
        System.out.print(ct);
    }
    
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
