import java.io.*;

public class Main {
	
	static final long MOD = 1000000000;

	static int N;
	static long result, dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new long[N + 1][10];

		// 첫 번째 자리수는 오른쪽 맨 끝의 자릿수이므로 경우의 수가 1개밖에 없음
		for (int i = 1; i < 10; i++)
			dp[1][i] = 1;

		// 두 번째 자릿수버투 N까지 탐색
		for (int i = 2; i <= N; i++) {
			// i번째 자릿수의 자리 값들을 탐색
			for (int j = 0; j < 10; j++) {
				if (j == 0) // j가 0이라면 이전 자릿수의 첫번째 자리 수만 가능
					dp[i][0] = dp[i - 1][1] % MOD;

				else if (j == 9) // j가 9라면 이전 자리수는 8만 가능
					dp[i][9] = dp[i - 1][8] % MOD;

				else // 그외, 이전 자리 값 +1, -1의 합이 됨
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
			}
		}

		// 각 자리 값마다 경우의 수를 모두 더해 줌
		for (int i = 0; i < 10; i++)
			result += dp[N][i];

		System.out.println(result % MOD);
	}
}
