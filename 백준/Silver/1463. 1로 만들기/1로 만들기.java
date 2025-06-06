import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[1_000_001];
		Arrays.fill(dp, Integer.MAX_VALUE);

		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;

		for (int i = 4; i <= N; i++) {
			if (i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);

			if (i % 2 == 0)
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);

			dp[i] = Math.min(dp[i], dp[i - 1] + 1);
		}
		
		System.out.println(dp[N]);
	}
}
