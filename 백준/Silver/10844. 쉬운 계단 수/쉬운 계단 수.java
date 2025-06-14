import java.io.*;

public class Main {

	static int N;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new long[101][10];
		for (int i = 1; i < 10; i++)
			dp[1][i] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i - 1][1] % 1_000_000_000;
			for (int j = 1; j <= 8; j++)
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1_000_000_000;
			dp[i][9] = dp[i - 1][8] % 1_000_000_000;
		}

		long maxValue = 0;
		for (int i = 0; i <= 9; i++)
			maxValue = (maxValue + dp[N][i]) % 1_000_000_000;
		
		System.out.println(maxValue);
	}
}
