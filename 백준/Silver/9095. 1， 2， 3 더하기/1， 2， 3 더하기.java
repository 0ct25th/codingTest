import java.io.*;

public class Main {

	static int T, n, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		dp = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i < 12; i++)
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			System.out.println(dp[n]);
		} // end of TestCase
	}
}
