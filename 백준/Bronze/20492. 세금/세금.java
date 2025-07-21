import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.printf("%d %d", (int)(N*0.78), (int)(N-(N*0.20)*0.22));
    }
}
