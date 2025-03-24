import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][K + 1];
		for (int r = 0; r <= N; r++)
			dp[r][0] = 1;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= K; c++)
				dp[r][c] = (dp[r - 1][c] + dp[r - 1][c - 1]) % 10_007;
		}

		System.out.println(dp[N][K]);
	}
}
