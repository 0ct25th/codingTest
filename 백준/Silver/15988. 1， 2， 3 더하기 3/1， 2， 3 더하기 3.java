import java.io.*;

public class Main {

	static int n;
	static long dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			dp = new long[1000001];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;

			for (int i = 4; i <= n; i++)
				dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;

			System.out.println(dp[n] % 1000000009);
		}
	}
}
