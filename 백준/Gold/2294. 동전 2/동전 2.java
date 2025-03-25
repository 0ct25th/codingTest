import java.io.*;
import java.util.*;

public class Main {

	static final int INF = 100_001;
	static int n, k, dp[];
	static boolean[] coins;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		coins = new boolean[k + 1];
		dp = new int[k + 1];
		Arrays.fill(dp, INF);

		for (int i = 0; i < n; i++) {
			int coin = Integer.parseInt(br.readLine());

			if (coin > k || coins[coin])
				continue;

			coins[coin] = true;
			dp[coin] = 1;
		}

		/////////////////// end of Input

		for (int i = 2; i <= k; i++) {
			for (int j = 1; j < i; j++) {
				int m = coins[j] ? 1 : INF;
				dp[i] = Math.min(dp[i], dp[i - j] + m);
			}
		}

		System.out.println(dp[k] >= INF ? -1 : dp[k]);
	}
}
