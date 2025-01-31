
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] num = new int[n];
        int count = 0;
        for (int i=0;i<n;i++) num[i] = Integer.parseInt(br.readLine());
        for (int i=n-1;i>0;i--){
            if (num[i]<=num[i-1]) {
                count += num[i-1] - (num[i]-1);
                num[i-1] =  num[i]-1;
            }
        }
        System.out.println(count);
    }
}
