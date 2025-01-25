

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer, Integer> count = new HashMap<>();

        q.offer(a);
        count.put(a, 0);

        while(!q.isEmpty()){
            int num = q.poll();
            if (num==k){
                System.out.print(count.get(num));
                return;
            }
            int add1 = num+1;
            int mul2 = num*2;
            if(add1<=k && !count.containsKey(add1)) {
                q.offer(add1);
                count.put(add1, count.get(num)+1);
            }
            if(mul2<=k && !count.containsKey(mul2)) {
                q.offer(mul2);
                count.put(mul2, count.get(num)+1);
            }
        }
    }
}
