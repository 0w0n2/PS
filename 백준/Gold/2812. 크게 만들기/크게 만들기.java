import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String numStr = br.readLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i=0;i<N;i++){
            char nChar = numStr.charAt(i);
            while(!stack.isEmpty() && K>0 && stack.peekLast()<nChar){
                stack.pollLast();
                K--;
            }
            stack.offerLast(nChar);
        }

        while(K-- > 0) stack.pollLast();
        StringBuilder sb = new StringBuilder();
        for (char s : stack) sb.append(s);
        System.out.print(sb);
    }
}
