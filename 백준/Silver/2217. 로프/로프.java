import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int max = 0;
        int [] rope = new int [testCase];
        for (int i=0;i<testCase;i++) rope[i] = Integer.parseInt(br.readLine());
        Arrays.sort(rope);
        for (int i=0;i<testCase;i++) max = Math.max(rope[i]* (testCase-i), max);
        System.out.println(max);
    }
}
