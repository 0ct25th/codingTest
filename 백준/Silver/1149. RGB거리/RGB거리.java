import java.io.*;
import java.util.*;

public class Main {

	final static int RED = 0;
	final static int GREEN = 1;
	final static int BLUE = 2;

	static int N;
	static int[][] home, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		home = new int[N + 1][3];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) {
				int w = Integer.parseInt(st.nextToken());
				home[r][c] = w;
			}
		}

		dp = new int[N + 1][3];
		dp[1][RED] = home[1][RED];
		dp[1][GREEN] = home[1][GREEN];
		dp[1][BLUE] = home[1][BLUE];
		for (int i = 2; i <= N; i++) {
			dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + home[i][RED];
			dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + home[i][GREEN];
			dp[i][BLUE] = Math.min(dp[i - 1][GREEN], dp[i - 1][RED]) + home[i][BLUE];
		}

		System.out.println(Math.min(dp[N][RED], Math.min(dp[N][GREEN], dp[N][BLUE])));
	}
}
