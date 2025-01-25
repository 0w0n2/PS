
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int step = 0;
        while (step<testCase){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int candyNum = Integer.parseInt(st.nextToken());
            int boxNum = Integer.parseInt(st.nextToken());
            int [] boxSize = new int[boxNum];
            for (int i=0;i<boxNum;i++){
                st = new StringTokenizer(br.readLine());
                boxSize[i] = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()); // R*C
            }
            Arrays.sort(boxSize); // 오름차순이라 나중에 배열 끝에서부터 체크

            // 가장 사이즈가 큰 상자부터 채워넣는다
            // (j - boxSize) if <= 0 : break, if > 0 : 다음 상자로
            int usedBox = 0;
            for (int i=(boxNum-1); i>=0; i--){
                if (candyNum - boxSize[i] <= 0) {
                    usedBox++;
                    break;
                }
                usedBox++;
                candyNum -= boxSize[i];
            }
            System.out.println(usedBox);
            step++;
        }
    }
}
