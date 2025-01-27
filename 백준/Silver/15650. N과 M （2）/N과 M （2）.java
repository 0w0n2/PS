import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<String> save = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 수열 크기

        for (int i = 1; i <= n; i++) {
            // 수열의 시작 넘버 설정(i)
            String s = String.valueOf(i);
            pp(i, 1, s);
        }
        save.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String result : save) {
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void pp(int x, int count, String s) {
        if (count == m) { // 수열 크기에 도달하면 결과를 저장
            save.add(s);
            return;
        }

        if (x > n) return; // 범위를 벗어나면 종료

        // 현재 숫자를 선택 X
        pp(x + 1, count, s);

        // 현재 숫자를 선택
        if (x + 1 <= n) {
            String nextSequence = s + " " + (x + 1);
            pp(x + 1, count + 1, nextSequence);
        }
    }
}
