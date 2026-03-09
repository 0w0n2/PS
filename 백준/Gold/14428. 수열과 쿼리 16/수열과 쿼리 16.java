import java.util.*;
import java.io.*;

public class Main {

    private static class SegTree{
        int size, start;
        int[] leaf; // 리프노드에 저장된 수열의 값
        int[] arr;  // 크기가 작은 값의 인덱스

        SegTree(int N){
            size = N;
            start = 1;
            while(start < size) start <<= 1;
            arr = new int[start << 1];
            leaf = new int[N];
        }

        void construct(){
            for (int i=start-1;i>0;i--){
                arr[i] = (leaf[arr[i*2]] <= leaf[arr[i*2+1]]) ? arr[i*2] : arr[i*2+1];
            }
        }

        int min(int a, int b){
            return min(a, b, 1, 0, start);
        }

        int min(int a, int b, int node, int na, int nb){
            if (b<=na || nb<=a) return -1;
            if (a<=na && nb<=b) return arr[node];

            int mid = (na+nb)>>1;
            int left = min(a, b, node*2, na, mid);
            int right = min(a,b, node*2+1, mid, nb);

            if (left==-1) return right;
            if (right==-1) return left;

            if (leaf[left] == leaf[right]) return Math.min(left, right);
            return (leaf[left] < leaf[right]) ? left : right;
        }

        void update(int i, int val){
            i += start;
            leaf[arr[i]] = val;

            while (i>1){
                i >>= 1;
                arr[i] = (leaf[arr[i*2]] <= leaf[arr[i*2+1]]) ? arr[i*2] : arr[i*2+1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        SegTree st = new SegTree(N);
        for (int i=0;i<N;i++) {
            st.leaf[i] = readInt();
            st.arr[i+st.start] = i;
        }
        st.construct();

        StringBuilder sb = new StringBuilder();
        int M = readInt();
        for (int i=0;i<M;i++){
            int cmd = readInt();
            int a = readInt();
            int b = readInt();

            if (cmd == 1) st.update(a-1, b);
            else sb.append(st.min(a-1, b)+1).append("\n");
        }

        System.out.print(sb);

    }

    private final static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
