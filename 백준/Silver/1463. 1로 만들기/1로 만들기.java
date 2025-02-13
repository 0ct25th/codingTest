import java.io.*;
import java.util.Arrays;

public class Main {

	static final int INF = 987654321;
	static int N, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		/////////////////// end of Input

		dp = new int[N + 1];
		Arrays.fill(dp, INF);
		dp[1] = 0;

		for (int X = 2; X <= N; X++) {
			// 1을 뺀다.
			dp[X] = dp[X - 1] + 1;

			// X가 3으로 나누어 떨어지면, 3으로 나눈다.
			if (X % 3 == 0)
				dp[X] = Math.min(dp[X], dp[X / 3] + 1);

			// X가 2로 나누어 떨어지면, 2로 나눈다.
			if (X % 2 == 0)
				dp[X] = Math.min(dp[X], dp[X / 2] + 1);
		}
		
		System.out.println(dp[N]);
	}
}
