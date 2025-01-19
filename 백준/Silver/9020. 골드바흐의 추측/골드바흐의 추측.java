import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int [] num = new int[T];
        int maxNum = Integer.MIN_VALUE;
        for (int i=0;i<T;i++){
            num[i] = Integer.parseInt(br.readLine());
            maxNum = Math.max(maxNum, num[i]);
        }
        // 소수 리스트 만들기
        boolean[] prime = new boolean[maxNum+1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i=2; i*i <= maxNum; i++){
            if (prime[i]){
                for (int j=i*i; j<=maxNum; j+=i) prime[j] = false;
            }
        }
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i=2;i<=maxNum;i++){
            if(prime[i]) primes.add(i);
        }

        for (int i=0;i<T;i++){
            if (primes.contains(num[i]/2)) bw.write(num[i]/2+" "+num[i]/2+"\n");
            else {
                for (int j=num[i]/2; j>=2; j--){
                    if (primes.contains(j)){
                        int diff = num[i] - j;
                        if (primes.contains(diff)) {
                            bw.write(j+" "+diff+"\n");
                            break;
                        }
                    }
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
