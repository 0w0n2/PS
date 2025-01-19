import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        boolean [] isPrime = new boolean[1299709+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i=2; i*i<=1299709; i++){
            if (isPrime[i]){
                for (int j=i*i; j<=1299709; j+=i) isPrime[j] = false;
            }
        }
        ArrayList<Integer> prime = new ArrayList<>();
        for (int i=2;i<=1299709;i++) if (isPrime[i]) prime.add(i);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        for (int t=0;t<testCase;t++){
            int num = Integer.parseInt(br.readLine());
            if (prime.contains(num))bw.write(String.valueOf(0)+"\n"); // 입력 소수일 경우
            else { // 입력이 합성수일 경우
                for (int j=num-1; j>=2; j--)
                    if (prime.contains(j)) { // 입력에서 제일 가깝게 작은 소수를 찾고
                        bw.write(String.valueOf(prime.get(prime.indexOf(j) + 1) - j) + "\n"); // 해당 소수의 인덱스+1 = 입력에서 제일 가깝게 큰 소수 이므로, 두 소수의 차이가 정답이 됨
                        break;
                    }
            }
        }
        bw.flush();
        bw.close();
    }
}
