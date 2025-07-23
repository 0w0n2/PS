import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int a, b, c, d, q, r;
        StringBuilder sb = new StringBuilder();
        sb.append(a=(100-readInt())).append(" ")
                .append(b=(100-readInt())).append(" ")
                .append(c=100-(a+b)).append(" ")
                .append(d=(a*b)).append(" ")
                .append(q=d/100).append(" ")
                .append(r=d%100).append("\n")
                .append(c+q).append(" ")
                .append(r);
        System.out.print(sb);
    }
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
