import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] dp = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[2] = 1;
		dp[4] = 2;
		dp[5] = 1;

		for (int i = 6; i <= n; i++)
			dp[i] = Math.min(dp[i - 2], dp[i - 5]) + 1;

		System.out.println(dp[n] == Integer.MAX_VALUE ? -1 : dp[n]);
	}
}
