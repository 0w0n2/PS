import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine()); // 테스트케이스
        // Queue<Integer> queue = new LinkedList<>(); // 첫 시도 때 큐 썼는데 큐는 수평으로 슬라이드 채우듯이 자료 넣는 거면 스택은 차곡차곡 쌓는 거더라고요? 그래서 큐는 제일 먼저 들어왔던 게 제일 먼저 삭제된다면 스택은 제일 위에 쌓여 있는 게 제일 먼저 삭제 된다고...
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<testCase; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 토큰의 개수가 2개면 push, 아니면 그외 명령
            if (st.countTokens()==2){
                st.nextToken(); // push는 날려버림
                stack.add(Integer.parseInt(st.nextToken())); // 정수 X를 스택에 집어넣음
                continue;
            }
            String cmd = st.nextToken();
            if (cmd.equals("pop")){
                // pop : 가장 위에 있는 정수를 빼고, 그 수를 출력
                if (!stack.isEmpty()) System.out.println(stack.pop());
                else System.out.println(-1);
            } else if (cmd.equals("size")){
                // size : 스택에 들어 있는 정수의 개수
                System.out.println(stack.size());
            } else if (cmd.equals("empty")){
                if (stack.isEmpty()) System.out.println(1);
                else System.out.println(0);
            } else if (cmd.equals("top")){
                if (!stack.isEmpty()) System.out.println(stack.peek()); // 가장 위에 있는 정수 조회
                else System.out.println(-1);
            }
        }
    }
}

