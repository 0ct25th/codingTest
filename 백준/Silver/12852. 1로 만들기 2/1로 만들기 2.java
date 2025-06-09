import java.io.*;
import java.util.*;

public class Main {

	static int N, dp[], order[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		dp = new int[1_000_001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;

		order = new int[1_000_001];
		for (int i = 2; i <= N; i++) {
		    dp[i] = dp[i - 1] + 1;
		    order[i] = i - 1;
		    if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
		        dp[i] = dp[i / 2] + 1;
		        order[i] = i / 2;
		    }
		    if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
		        dp[i] = dp[i / 3] + 1;
		        order[i] = i / 3;
		    }
		}

		sb.append(dp[N]).append("\n");
		int idx = N;
		while(idx > 0) {
			sb.append(idx).append(" ");
			idx = order[idx];
		}

		System.out.println(sb);
	}
}
