import java.io.*;

public class Main {

	static int n, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		if (n == 1)
			System.out.println(1);
		else if (n == 2)
			System.out.println(3);
		else {
			dp = new int[n + 1];
			dp[1] = 1;
			dp[2] = 3;
			dp[3] = 5;

			for (int i = 4; i <= n; i++)
				dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10_007;

			System.out.println(dp[n] % 10_007);
		}

	}
}
