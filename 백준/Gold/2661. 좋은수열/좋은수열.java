import java.io.*;

public class Main {

	static int N;
	static boolean isFind;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dfs(0, "");
	}

	static void dfs(int depth, String num) {
		// 가지치기: 이미 가장 작은 수열을 찾은 경우
		if (isFind)
			return;

		// 기저조건: 7자리 수열이 만들어진 경우
		if (depth == N) {
			isFind = true;
			System.out.println(num);

			return;
		}

		// 해당 자리 1로 채우기
		if (isGood(num + 1))
			dfs(depth + 1, num + 1);

		// 해당 자리 2로 채우기
		if (isGood(num + 2))
			dfs(depth + 1, num + 2);

		// 해당 자리 3로 채우기
		if (isGood(num + 3))
			dfs(depth + 1, num + 3);
	}

	static boolean isGood(String num) {
		int n = num.length();
		for (int i = 1; i < n / 2 + 1; i++) {
			if (num.substring(n - i, n).equals(num.substring(n - 2 * i, n - i)))
				return false; // 나쁜 수열
		}

		return true; // 좋은 수열
	}
}
