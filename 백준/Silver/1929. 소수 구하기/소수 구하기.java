

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 에라토스테네스의 체 : 2~N까지 소수의 배수들을 다 걸러버림으로써 소수만 남기는 방식
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for (int i=4; i<=N; i+=2) isPrime[i] = false;
        for (int i=3; i * i <= N; i+=2){
            if (isPrime[i]) {
                for (int j=i*i; j<=N; j+=i*2) isPrime[j] = false;
            }
        }
        for (int i=M;i<=N;i++) if (isPrime[i]) System.out.println(i);
    }
}
