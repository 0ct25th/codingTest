import java.io.*;
import java.util.*;

public class Main {

	static int S, P, pwd[], cnt[];
	static long result;
	static String dna;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫 번째 줄에 민호가 임의로 만든 DNA 문자열 길이 |S|와 비밀번호로 사용할 부분문자열의 길이 |P| 가 주어진다.
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		// 두번 째 줄에는 민호가 임의로 만든 DNA 문자열이 주어진다.
		dna = br.readLine();

		// 세번 째 줄에는 부분문자열에 포함되어야 할 {‘A’, ‘C’, ‘G’, ‘T’} 의 최소 개수가 공백을 구분으로 주어진다.
		pwd = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++)
			pwd[i] = Integer.parseInt(st.nextToken());

		//////////////// end of Input

		slidingWindow();

		System.out.println(result);
	}

	static void slidingWindow() {
		cnt = new int[4];

		// 첫번째 윈도우 생성
		for (int i = 0; i < P; i++) {
			char ch = dna.charAt(i);

			cnt[idx(ch)]++;
		}

		if (isChk())
			result++;

		for (int i = 1; i < S - P + 1; i++) {
			// 왼쪽 몰아내기
			cnt[idx(dna.charAt(i - 1))]--;
			// 오른쪽 들여오기
			cnt[idx(dna.charAt(i + P - 1))]++;

			if (isChk())
				result++;
		}
	}

	static boolean isChk() {
		for (int i = 0; i < 4; i++)
			if (pwd[i] > cnt[i])
				return false;

		return true;
	}

	static int idx(char ch) {
		switch (ch) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		}
		return 4;
	}
}
