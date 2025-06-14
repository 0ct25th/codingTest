import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] T, P, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		T = new int[21];
		P = new int[21];
		dp = new int[21];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= 16; i++) {
			int day = i + T[i];
			if (day > 20)
				continue;

			dp[i] = Math.max(dp[i], dp[i - 1]);
			dp[day] = Math.max(dp[day], dp[i] + P[i]);
		}

		System.out.println(dp[N + 1]);
	}
}
