import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String name = br.readLine();

		int[] alph = new int[26];
		for (int i = 0; i < name.length(); i++) {
			int idx = name.charAt(i) - 'A';
			alph[idx]++;
		}

		int oddCnt = 0;
		for (int i = 0; i < 26; i++)
			if (alph[i] % 2 != 0)
				oddCnt++;

		if (oddCnt > 1)
			System.out.println("I'm Sorry Hansoo");
		else {
			String result = "";
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < alph[i] / 2; j++)
					result += (char) (i + 65);
			}
			
			for(int i= 0; i < 26; i++) {
				if(alph[i] % 2 != 0) {
					result += (char)(i + 65);
					break;
				}
			}

			for (int i = 25; i >= 0; i--) {
				for (int j = 0; j < alph[i] / 2; j++)
					result += (char) (i + 65);
			}

			System.out.println(result);
		}
	}
}
