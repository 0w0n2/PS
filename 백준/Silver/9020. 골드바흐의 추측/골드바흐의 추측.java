import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 소수 리스트 만들기
        boolean[] prime = new boolean[10_001];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i=2; i*i <= 10_000; i++){
            if (prime[i]){
                for (int j=i*i; j<=10_000; j+=i) prime[j] = false;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        Loop1:
        for (int i=0;i<T;i++){
            int n = Integer.parseInt(br.readLine());
            if (prime[n/2]) bw.write(n/2+" "+n/2+"\n");
            else {
                for (int k=n/2; k>=2; k--){
                    if (prime[k]){
                        int diff = n - k;
                        if (prime[diff]) {
                            bw.write(k+" "+diff+"\n");
                            continue Loop1;
                        }
                    }
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
