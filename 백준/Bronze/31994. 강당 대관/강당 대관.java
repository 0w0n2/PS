import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxCt = 0; String maxName = null;
        for (int i=0;i<7;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int num;
            if (maxCt < (num = Integer.parseInt(st.nextToken()))){
                maxCt = num;
                maxName = name;
            }
        }
        System.out.print(maxName);
    }
}
