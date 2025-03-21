import java.io.*;

public class Main {

	static int N;
	static long[] dp = new long[91];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 2;

		for (int i = 4; i <= N; i++)
			dp[i] = dp[i - 1] + dp[i - 2];
		
		System.out.println(dp[N]);
	}
}
