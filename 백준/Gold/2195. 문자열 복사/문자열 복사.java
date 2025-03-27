import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		
		int L = 0;
		
		int idx = 0;
		int ct = 0;
		
		while(L<P.length()) {
			StringBuilder temp = new StringBuilder();
			while (idx<P.length()) {
				temp.append(P.charAt(idx));
				if (!S.contains(temp)) {
					temp.delete(temp.length()-1, temp.length());
					break;
				}
				idx++;
				
			}
			L += temp.length();
			ct++;
		}
		System.out.print(ct);
	}
}
