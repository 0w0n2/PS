
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String input = br.readLine();

        char [] location = new char[N];
        for (int i=0;i<N;i++) location[i] = input.charAt(i);

        int count = 0;
        for (int i=0;i<N;i++){
            if (location[i]=='P'){
                for (int j = Math.max(0,i-K); j<=Math.min(N-1, i+K); j++){
                    if (location[j]=='H'){
                        count++;
                        location[j] = 'U';
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
