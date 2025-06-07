import java.io.*;

public class Main {

	static int N, stairs[], dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1]; // 각 계단에서 최대 점수
		stairs = new int[N + 1];
		for (int i = 1; i <= N; i++)
			stairs[i] = Integer.parseInt(br.readLine());

		if (N > 2) {
			dp[1] = stairs[1];
			dp[2] = dp[1] + stairs[2];

			for (int i = 3; i <= N; i++) {
				dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
			}
		} else if (N == 1)
			dp[1] = stairs[1];
		else if (N == 2)
			dp[2] = stairs[1] + stairs[2];

		System.out.println(dp[N]);
	}
}
