import java.io.*;
import java.util.*;

public class Main {

	static char[] str1, str2;
	static boolean isPossible;
	static boolean[] isChk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());
			str1 = st.nextToken().toCharArray();
			str2 = st.nextToken().toCharArray();

			Arrays.sort(str1);
			Arrays.sort(str2);

			isPossible = true;
			if (str1.length != str2.length)
				isPossible = false;
			else {
				for (int i = 0; i < str1.length; i++) {
					if (str1[i] != str2[i]) {
						isPossible = false;
						break;
					}
				}
			}

			if (isPossible)
				sb.append("Possible\n");
			else
				sb.append("Impossible\n");
		}

		System.out.println(sb);
	}
}
