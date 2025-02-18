import java.io.*;
import java.util.*;

public class Main {

	static int n, cost[][], dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());

			cost = new int[2][n + 1];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++)
					cost[i][j] = Integer.parseInt(st.nextToken());
			}
			/////////////////////// end of Input

			dp = new int[2][n + 1];
			dp[0][1] = cost[0][1];
			dp[1][1] = cost[1][1];

			for (int i = 2; i <= n; i++) {
				dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + cost[0][i];
				dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + cost[1][i];
			}

			System.out.println(Math.max(dp[0][n], dp[1][n]));

		} // end of TestCase
	}
}
