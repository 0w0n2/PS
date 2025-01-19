
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        boolean [] isPrime = new boolean[(123456*2)+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i=2; i<=Math.sqrt(123456*2); i++){
            if (isPrime[i]){
                for (int j=i*i; j<=123456*2; j+=i) isPrime[j] = false;
            }
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = -1;
        while((num = Integer.parseInt(br.readLine()))!=0){
            int count = 0;
            for (int i=num+1; i<=num*2; i++){
                if (isPrime[i]) count++;
            }
            bw.write(String.valueOf(count)+"\n");
        }
        bw.flush();
        bw.close();
    }
}
