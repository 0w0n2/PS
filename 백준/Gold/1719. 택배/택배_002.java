import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
    private static final int INF = 1_000_000;

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int[][] dist, first;

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        dist = new int[n][n];
        first = new int[n][n];

        for (int i=0; i<n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i=0; i<m; i++) {
            int a = readInt()-1;
            int b = readInt()-1;
            int w = readInt();

            if (w < dist[a][b]) {
                dist[a][b] = dist[b][a] = w;
                first[a][b] = b;
                first[b][a] = a;
            }
        }

        floyd(n);

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                sb.append(j == i ? "-" : first[i][j]+1).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void floyd(int n) {
        for (int k=0; k<n; k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        first[i][j] = first[i][k]; // i->k로 가는 first
                    }
                }
            }
        }
    }

    private static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
