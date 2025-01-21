
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> x = new ArrayList<>(); // x(0) y(1)
        ArrayList<Integer> y = new ArrayList<>(); // x(0) y(1)
        int [] min = new int [n];
        Arrays.fill(min, -1);

        for (int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            x.add(Integer.parseInt(st.nextToken())); // x 좌표 입력
            y.add(Integer.parseInt(st.nextToken())); // y 좌표 입력
        }

        for (int i=0;i<x.size();i++){ // x[i]
            for (int j=0;j<y.size();j++){ // y[j]
                ArrayList<Integer> distance = new ArrayList<>();
                for (int k=0;k<x.size();k++) {
                    distance.add(Math.abs(x.get(i) - x.get(k)) + Math.abs(y.get(j) - y.get(k)));
                }
                Collections.sort(distance);
                int sum = 0;
                for (int k=0;k<n;k++){
                    sum += distance.get(k);
                    if (min[k]==-1) min[k] = sum; // 처음 등록
                    else min[k] = Math.min(sum, min[k]);
                }
            }
        }
        for (int i=0;i<n;i++) System.out.print((min[i])+" ");
    }
}
