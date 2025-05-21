import java.util.*;
import java.io.*;

public class Main {
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = readInt();
        TreeSet<Integer> map = new TreeSet<>();
        for (int i=0;i<N;i++) map.add(readInt());

        StringBuilder sb = new StringBuilder();
        int M = readInt();
        for (int i=0;i<M;i++){
            if (map.contains(readInt())) sb.append("1 ");
            else sb.append("0 ");
        }
        System.out.print(sb);
    }
}
