
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int height = sc.nextInt();
        int width = sc.nextInt();
        if (height==1) System.out.println(1);
        else if (height==2) System.out.println(Math.min(4, (width+1)/2));
        else if (width<7) System.out.println(Math.min(4, width));
        else System.out.println((width-4) + 2);
    }
}
