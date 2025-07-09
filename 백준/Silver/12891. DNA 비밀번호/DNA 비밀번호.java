import java.io.*;
import java.util.*;

public class Main {

	static int S, P;
	static String dna;
	static int[] str, use;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		P = Integer.parseInt(st.nextToken()); // 비밀번호로 사용할 부분 문자열의 길이

		dna = br.readLine();

		str = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++)
			str[i] = Integer.parseInt(st.nextToken());

		System.out.println(slidingWindow());
	}

	static int slidingWindow() {
		use = new int[4];
		int result = 0;

		for (int i = 0; i < P; i++) {
			char ch = dna.charAt(i);

			if (ch == 'A')
				use[0]++;
			else if (ch == 'C')
				use[1]++;
			else if (ch == 'G')
				use[2]++;
			else
				use[3]++;
		}

		if (password())
			result++;

		for (int i = 1; i <= S - P; i++) {
			// 왼쪽 제거
			char before = dna.charAt(i - 1);
			if (before == 'A')
				use[0]--;
			else if (before == 'C')
				use[1]--;
			else if (before == 'G')
				use[2]--;
			else
				use[3]--;

			// 오른쪽 추가
			char after = dna.charAt(i + P - 1);
			if (after == 'A')
				use[0]++;
			else if (after == 'C')
				use[1]++;
			else if (after == 'G')
				use[2]++;
			else
				use[3]++;

			if (password())
				result++;
		}

		return result;
	}

	static boolean password() {
		for (int i = 0; i < 4; i++) {
			if (use[i] < str[i])
				return false;
		}

		return true;
	}
}
