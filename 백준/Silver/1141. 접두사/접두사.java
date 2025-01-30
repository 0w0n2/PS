import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 단어의 개수
        String [] array = new String[n];
        for (int i=0;i<n;i++) array[i] = br.readLine();
        Arrays.sort(array, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        // 정렬 조건 : 단어 길이
        // 접두어는 해당 문자보다 짧거나 같은 길이기 때문에, 단어 길이로 정렬하여 오른쪽으로 선형 탐색해서 자신이 접두어가 될 수 있는 단어가 있는지 체크

        int res = n;
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                if (array[j].startsWith(array[i])){
                    // startsWith() : String 변수에서 어떤 문자열로 시작하는지 확인(Boolean) <-> endsWith()
                    res--;
                    break;
                }
            }
        }
        System.out.print(res);
    }
}
