import java.io.*;
import java.util.Arrays;

public class Main {

	static final int INF = 987654321;
	static int n, dp[], order[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		order = new int[n + 1];
		dp = new int[n + 1];
		Arrays.fill(dp, INF);
		dp[1] = 0;

		for (int X = 2; X <= n; X++) {
			// 1을 뺀다.
			dp[X] = dp[X - 1] + 1;
			order[X] = X - 1;

			// X가 3으로 나누어 떨어지면, 3으로 나눈다.
			if (X % 3 == 0 && dp[X] > dp[X / 3] + 1) {
				dp[X] = dp[X / 3] + 1;
				order[X] = X / 3;
			}

			// X가 2로 나누어 떨어지면, 2로 나눈다.
			if (X % 2 == 0 && dp[X] > dp[X / 2] + 1) {
				dp[X] = dp[X / 2] + 1;
				order[X] = X / 2;
			}
		}

		// 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
		sb.append(dp[n]).append("\n");

		// 둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다. 
		// 정답이 여러 가지인 경우에는 아무거나 출력한다.
		while (n > 0) {
			sb.append(n).append(" ");
			n = order[n];
		}

		System.out.println(sb);
	}
}
