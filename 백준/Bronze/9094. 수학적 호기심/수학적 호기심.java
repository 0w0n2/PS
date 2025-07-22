import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<T;i++){
            sb.append(sol(readInt(), readInt())).append("\n");
        }
        System.out.print(sb);
    }

    private static int sol(int n, int m){
        int ct = 0;
        for (int a=1;a<n;a++){
            for (int b=a+1;b<n;b++){
                if (((a*a)+(b*b)+m)%(a*b)==0) ct++;
            }
        }
        return ct;
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
