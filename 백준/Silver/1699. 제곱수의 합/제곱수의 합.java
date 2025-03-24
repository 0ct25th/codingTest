import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dp = new int[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			int min = Integer.MAX_VALUE;

			for (int j = 1; j <= i / 2; j++) {

				if (j * j == i) {
					min = 1;
					break;
				} else
					min = Math.min(min, dp[i - j] + dp[j]);

			}

			dp[i] = min;
		}

		System.out.println(dp[N]);
	}
}
