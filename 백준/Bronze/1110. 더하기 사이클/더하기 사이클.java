import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int n = N;
        int ct = 0;
        do{
            int left = n / 10;
            int right = n % 10;
            int sum = left + right;
            n = (right * 10) + (sum % 10);
            ct++;
        }while(N!=n);
        System.out.print(ct);
    }
}
