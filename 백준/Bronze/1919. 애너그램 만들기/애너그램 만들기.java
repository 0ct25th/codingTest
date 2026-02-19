import java.io.*;

public class Main {
	
	static int[] alph = new int[26];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] arr = br.readLine().toCharArray();
		for(int i = 0; i < arr.length; i++) {
			alph[arr[i] - 'a']++;
		}
		
		arr = br.readLine().toCharArray();
		for(int i = 0; i < arr.length; i++) {
			alph[arr[i] - 'a']--;
		}
		
		int result = 0;
		for(int i = 0; i < 26; i++) {
			result += Math.abs(alph[i]);
		}
		
		System.out.println(result);
		
		br.close();
	}
}
