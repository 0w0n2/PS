import java.util.*;

public class Main{
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        
        int n = 1;

        while((1 + n*(n-1)/2) <= x) {
        	n++;
        }
        
        n--;

        int s = x - (n*(n-1)/2);
        
        if (n%2==1) System.out.print((n-s+1) + "/" + s);
        else System.out.print(s + "/" + (n-s+1));
	}
}