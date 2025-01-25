
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int melon = sc.nextInt();
        ArrayList<Integer> direction = new ArrayList<>();
        ArrayList<Integer> length = new ArrayList<>();
        for (int i=0;i<6;i++){
            direction.add(sc.nextInt());
            length.add(sc.nextInt());
        }

        while (true){
            String s1 = String.valueOf(direction.get(2)) + String.valueOf(direction.get(3));
            String s2 = String.valueOf(direction.get(4)) + String.valueOf(direction.get(5));
            if (s1.equals(s2)) break;
            else {
                int back = direction.remove(0);
                direction.add(back);
                back = length.remove(0);
                length.add(back);
            }
        }

        int area = length.get(0)*length.get(1)  - length.get(3)*length.get(4);
        int money = area * melon;
        System.out.print(money);
    }
}
