import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        switch (br.readLine()) {
            case "SONGDO":
                System.out.print("HIGHSCHOOL");
                break;
            case "CODE":
                System.out.println("MASTER");
                break;
            case "2023":
                System.out.println("0611");
                break;
            case "ALGORITHM":
                System.out.println("CONTEST");
                break;
        }
    }
}
