import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine()); // 명령어 수
        ArrayDeque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int n=0;n<N;n++){
          StringTokenizer st = new StringTokenizer(br.readLine());
          if (st.countTokens()>1){ // push
              st.nextToken();
              q.offerLast(Integer.parseInt(st.nextToken()));
          } else {
              switch (st.nextToken()){
                  case "front":
                      if (q.isEmpty()) sb.append("-1\n");
                      else sb.append(q.peekFirst()).append("\n");
                      break;
                  case "back":
                      if (q.isEmpty()) sb.append("-1\n");
                      else sb.append(q.peekLast()).append("\n");
                      break;
                  case "size":
                      sb.append(q.size()).append("\n");
                      break;
                  case "empty":
                      sb.append(q.isEmpty() ? "1\n" : "0\n");
                      break;
                  case "pop":
                      if (q.isEmpty()) sb.append("-1\n");
                      else sb.append(q.pollFirst()).append("\n");
                      break;
              }
          }
        }
        System.out.print(sb);
    }
}
