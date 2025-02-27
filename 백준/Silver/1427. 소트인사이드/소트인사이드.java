import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = br.readLine().chars().map(o->o-'0').toArray();
		Arrays.sort(nums);
		StringBuilder sb = new StringBuilder();
		for(int x:nums) sb.append(x);
		System.out.print(sb.reverse());
	}
}