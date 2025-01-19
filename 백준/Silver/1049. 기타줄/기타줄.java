import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int p = Integer.MAX_VALUE;
        int o = Integer.MAX_VALUE;

        for (int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            p = Math.min(p, Integer.parseInt(st.nextToken()));
            o = Math.min(o, Integer.parseInt(st.nextToken()));
            if (o*6<p) p = o*6;
        }

        if(n%6==0) System.out.print(n/6 * p);
        else System.out.print(Math.min(((n/6)+1)*p,(n/6)*p+(n%6)*o));
    }
}
