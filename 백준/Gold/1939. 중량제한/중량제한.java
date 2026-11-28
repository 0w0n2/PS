import java.util.*;
import java.io.*;

public class Main {
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int[] par;
    private static final int INIT = -1;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        par = new int[N];
        Path[] paths = new Path[M];

        for (int i=0; i<M; i++) {
            paths[i] = new Path(readInt()-1, readInt()-1, readInt());
        }

        int start = readInt()-1;
        int end = readInt()-1;

        Arrays.fill(par, INIT);
        Arrays.sort(paths, (o1, o2) -> Integer.compare(o2.weight, o1.weight)); // 가중치 큰 순서로 정렬

        for (Path p : paths) {
            merge(p.a, p.b);

            if (find(start) == find(end)) {
                System.out.print(p.weight);
                return;
            }
        }
    }

    private static int find(int x) {
        if (par[x] == INIT) {
            return x;
        }

        return par[x] = find(par[x]);
    }

    private static void merge(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        par[b] = a;
    }

    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval; // 1<=C<=10^9
    }

    private static class Path {
        int a, b, weight;

        public Path(int node, int to, int weight) {
            this.a = node;
            this.b = to;
            this.weight = weight;
        }
    }
}
