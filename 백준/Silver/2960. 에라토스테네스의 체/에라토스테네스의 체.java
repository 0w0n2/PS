
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int count = 0;
        int result = 0;
        ArrayList<Integer> numList = new ArrayList<>();
        for (int i=2; i<=n; i++) numList.add(i); // 2부터 n까지

        while(!numList.isEmpty()){ // 요소가 모두 삭제될 때까지 반복문 실행
            int num = numList.get(0); // 가장 앞에 있는 거 빼오기
            count++;
            if (count==k){
                System.out.println(num);
                return;
            }
            numList.remove(0);

            for (int i=0; i<numList.size(); i++){
                if (numList.get(i)%num==0){
                    count ++;
                    if (count==k){
                        System.out.println(numList.get(i));
                        return;
                    }
                    numList.remove(i);

                }
            }
        }
    }
}
