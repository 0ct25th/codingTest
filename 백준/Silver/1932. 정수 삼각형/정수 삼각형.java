import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[][] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				if (r < c)
					break;

				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[n][n];
		for (int c = 0; c < n; c++)
			dp[n - 1][c] = map[n - 1][c];

		if(n > 1) {
			for (int r = n - 2; r > 0; r--) {
				for (int c = 0; c <= r; c++) 
					dp[r][c] = Math.max(dp[r + 1][c], dp[r + 1][c + 1]) + map[r][c];
			}

			dp[0][0] = Math.max(dp[1][0], dp[1][1]) + map[0][0];
		}
		System.out.println(dp[0][0]);
	}
}
