import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 단어의 개수
        String [] words = new String [n];
        for (int i=0;i<n;i++) words[i] = br.readLine();
        HashSet<String> words_set = new HashSet<>(Arrays.asList(words));
        ArrayList<String> words_array = new ArrayList<>(words_set);
        words_array.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length()!=o2.length()) return Integer.compare(o1.length(), o2.length());
                return o1.compareTo(o2);
            }
        });

        for (int i=0;i<words_array.size();i++) System.out.println(words_array.get(i));
    }
}
