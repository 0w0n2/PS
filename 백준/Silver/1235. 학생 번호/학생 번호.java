import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String [] input = new String[n];
        for (int i=0;i<n;i++) input[i] = br.readLine();

        Loop1:
        for (int i=0; i<input[0].length(); i++){
            ArrayList<String> save = new ArrayList<>();
            for (int j=0;j<n;j++){
                String cut = new StringBuilder(input[j]).substring(input[0].length()-1-i);
                if (save.contains(cut)) continue Loop1;
                else save.add(cut);
                if (save.size()==n) {
                    System.out.print(i+1);
                    return;
                }
            }
        }
    }
}
