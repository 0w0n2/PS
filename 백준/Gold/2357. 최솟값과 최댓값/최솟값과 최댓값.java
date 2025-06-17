// Node(min, max) 로 하나의 트리에 최댓값/최솟값 모두 저장하던 구조 -> minTree, maxTree 로 분리

import java.io.*;

// a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 또는 제일 큰 정수

public class Main {

    private static class SegTree{
        int size, start;
        int[] minTree;
        int[] maxTree;

        SegTree(int n){
            size = n;
            start = 1;

            while(start < size) start <<= 1;
            minTree = new int[start*2];
            maxTree = new int[start*2];
        }
        
        // 선입력 받은 리프 노드로 전체 세그먼트 트리 구성
        // 하나의 공간은 그 구간의 최솟값, 최댓값을 가지고 있음
        void construct(){
            for (int i=start-1; i>0; i--) {
                minTree[i] = Math.min(minTree[i*2], minTree[i*2+1]);
                maxTree[i] = Math.max(maxTree[i*2], maxTree[i*2+1]);
            }
        }
        
        // [a, b] 구간의 최댓값, 최솟값 조회
        Node getMinMax(int a, int b){
            a += start;
            b += start;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            while (a <= b){ // a 와 b 가 겹칠 때까지 왼쪽/오른쪽에서 가운데로 좁혀오도록 이동 (최고 높이의 공통 부모 노드까지 이동)
                // 구간을 쪼갰을 때 양쪽 경계에 걸쳐 있는 예외 노드를 따로 챙겨주자
                if ((a&1)==1){ // (1) a가 부모 노드의 오른쪽 노드일 때
                    min = Math.min(min, minTree[a]);
                    max = Math.max(max, maxTree[a]);
                    a++;
                }
                if ((b&1)==0){ // (2) b가 부모 노드의 왼쪽 노드일 때
                    min = Math.min(min, minTree[b]);
                    max = Math.max(max, maxTree[b]);
                    b--;
                }
                a >>= 1; // 다음 부모 노드로 올라감
                b >>= 1;
            }
            return new Node(min, max);
        }
    }

    private static class Node{
        int min, max;
        Node(int min, int max){
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt(); // 쿼리 개수

        SegTree st = new SegTree(N);

        for (int i=0;i<N;i++){
            st.minTree[st.start + i] = st.maxTree[st.start + i] = readInt();    // 리프노드 입력
        }
        st.construct(); // 전체 세그먼트 트리 구성

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<M;i++){
            int a = readInt();
            int b = readInt();
            Node res = st.getMinMax(a-1, b-1);
            sb.append(res.min).append(" ").append(res.max).append("\n");
        }
        System.out.print(sb);
    }
    
    // 입력 로직
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
