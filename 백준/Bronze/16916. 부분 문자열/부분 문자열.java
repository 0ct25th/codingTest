import java.io.*;

public class Main {

	static int[] pi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String S = br.readLine();
		String P = br.readLine();

		System.out.println(KMP(S, P) ? 1 : 0);
	}

	static boolean KMP(String s, String p) {
		int idx = 0;

		// 부분 일치 테이블 생성
		makeTable(p);

		// 텍스트 S를 순회하면서 패턴 P와 비교
		for (int i = 0; i < s.length(); i++) {
			while (idx > 0 && s.charAt(i) != p.charAt(idx))
				idx = pi[idx - 1];

			// 불일치가 발생하면, pi 배열을 사용하여 이전에 일치했던 부분으로 돌아
			if (s.charAt(i) == p.charAt(idx))
				idx++;

			// 끝까지 일치하는 경우
			if (idx == p.length())
				return true;
		}

		return false;
	}
	public static void makeTable(String p) {
		int idx = 0;
		// 패턴 P의 각 위치에서의 부분 일치 정보를 저장
		pi = new int[p.length()];

		for (int i = 1; i < p.length(); i++) {
			// pi[i]는 패턴의 0부터 i까지의 접두사와 접미사가 일치하는 최대 길이를 나타냄
			while (idx > 0 && p.charAt(i) != p.charAt(idx))
				idx = pi[idx - 1];

			// 일치하는 경우
			if (p.charAt(i) == p.charAt(idx)) {
				idx++;
				pi[i] = idx;
			}
		}
	}
}
