

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String equation = br.readLine();

        int [] smallSum = new int[50];
        int smallSumIndex = 0;

        String stack = "";
        for (int i=0;i<equation.length();i++){
            if (equation.charAt(i)=='+'||equation.charAt(i)=='-'){
                smallSum[smallSumIndex] += Integer.parseInt(stack);
                stack = "";
                if (equation.charAt(i)=='-') smallSumIndex++;
            } else {
                stack += equation.charAt(i);
                if (i==(equation.length()-1)) smallSum[smallSumIndex] += Integer.parseInt(stack);
            }
        }

        int sum = smallSum[0];
        for (int i=1; i<smallSum.length; i++) sum-=smallSum[i];
        System.out.println(sum);
    }
}
