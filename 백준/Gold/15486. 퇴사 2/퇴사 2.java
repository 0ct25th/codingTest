import java.io.*;
import java.util.*;

public class Main {

	static int N, T[], P[], dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		T = new int[N + 1];
		P = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		////////////// end of Input

		dp = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			// 상담을 하지 않은 경우
			dp[i] = Math.max(dp[i], dp[i - 1]);

			// 상담을 진행한 경우
			int nextDay = i + T[i] - 1;
			if (nextDay <= N)
				dp[nextDay] = Math.max(dp[nextDay], dp[i - 1] + P[i]);
		}

		System.out.println(dp[N]);
	}
}
