import java.io.*;

public class Main {

	static int N;
	static long dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		dp = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			for (int i = 4; i <= N; i++)
				dp[i] = dp[i - 3] + dp[i - 2];

			System.out.println(dp[N]);
		} // end of TestCase
	}
}
