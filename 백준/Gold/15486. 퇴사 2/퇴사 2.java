import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] T, P;
	static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		T = new int[1_500_051];
		P = new int[1_500_051];
		dp = new long[1_500_051];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= 1_500_001; i++) {
			int day = i + T[i];
			if (day > 1_500_051)
				continue;

			dp[i] = Math.max(dp[i], dp[i - 1]);
			dp[day] = Math.max(dp[day], dp[i] + P[i]);
		}

		System.out.println(dp[N + 1]);
	}
}
