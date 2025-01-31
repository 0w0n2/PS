import java.util.*;

public class Main{
	public static void main (String[] args)  {
		Scanner sc = new Scanner (System.in);
		String inputWord = sc.nextLine();
		String [] Word = new String [inputWord.length()];
		for (int i=0;i<inputWord.length();i++) Word[i] = String.valueOf(inputWord.charAt(i));
		Arrays.sort(Word); // 입력 받은 문자를 오름차순 정렬
		
		String [] palindrome = new String[inputWord.length()];
		boolean [] used = new boolean[inputWord.length()];
		
		int j = 0;
		for (int i=0;i<inputWord.length()-1;i++) {
			if (Word[i].equals(Word[i+1])) {
				palindrome[j] = Word[i];
				palindrome[inputWord.length()-1-j] = Word[i];
				used[i] = true;
				used[i+1] = true;
				j++;
				i++;
			} 
		}
		
		int remnant = 0;
		for (int i=0; i<inputWord.length();i++) {
			if (!used[i]) {
				remnant++;
				if (remnant>1) {
					System.out.println("I'm Sorry Hansoo");
					return;
				}
				palindrome[inputWord.length()/2] = Word[i];
			}
		}
		
		for (int i=0;i<inputWord.length();i++) {
			System.out.print(palindrome[i]);
		}
	}	
}