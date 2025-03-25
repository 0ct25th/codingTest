import java.io.*;
import java.util.*;

public class Main {

	static int n, k;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dp = new int[k + 1];
		dp[0] = 1;

		for (int i = 1; i <= n; i++) {
			int coin = Integer.parseInt(br.readLine());

			for (int j = coin; j <= k; j++)
				dp[j] += dp[j - coin];
		}

		System.out.println(dp[k]);
	}
}
