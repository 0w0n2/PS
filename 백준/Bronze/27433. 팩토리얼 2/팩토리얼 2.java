import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        System.out.print(N==0 ? 1 : recursive(N));
    }

    private static long recursive(long N){
        if (N==1) return 1;
        return N*recursive(N-1);
    }
}
