import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		
		StringBuilder makeP = new StringBuilder();
		
		int idx = 0;
		int ct = 0;
		
		while(makeP.length()<P.length()) {
			StringBuilder temp = new StringBuilder();
			while (idx<P.length()) {
				temp.append(P.charAt(idx));
				if (!S.contains(temp)) {
					temp.delete(temp.length()-1, temp.length());
					break;
				}
				idx++;
				
			}
			makeP.append(temp); 
			ct++;
		}
		System.out.print(ct);
	}
}
