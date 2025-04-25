import java.io.*;
import java.util.*;

public class Main {

	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int dp[][] = new int[K + 1][N + 1];
		for (int r = 0; r <= K; r++)
			dp[r][0] = 1;

		for (int c = 0; c <= N; c++)
			dp[1][c] = 1;

		for (int r = 2; r <= K; r++) {
			for (int c = 1; c <= N; c++) {
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
				dp[r][c] %= 1_000_000_000;
			}
		}

		System.out.println(dp[K][N]);
	}
}
