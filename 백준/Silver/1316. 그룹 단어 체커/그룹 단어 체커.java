import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numWord = Integer.parseInt(br.readLine());
        int step = 0;
        int deny = 0;
        
        while (step < numWord) {
            String inputWord = br.readLine();
            
            boolean [] alpha = new boolean[26];
            
            for (int i = 0; i < inputWord.length(); i++) {
                int wordLocation = inputWord.charAt(i) - 'a';
                if (i == 0) {
                    alpha[wordLocation] = true;
                    continue;
                } else {
                    if (inputWord.charAt(i) != (inputWord.charAt(i - 1))) {
                        if (alpha[wordLocation] == true) {
                            deny++;
                            break;
                        }
                        else alpha[wordLocation] = true;
                    }
                }
            }
            step++;
        }
        bw.write(String.valueOf(numWord-deny));
        bw.flush();
        bw.close();
    }
}
