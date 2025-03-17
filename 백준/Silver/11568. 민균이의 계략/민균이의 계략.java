import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] cards, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		cards = new int[N];
		for (int i = 0; i < N; i++)
			cards[i] = Integer.parseInt(st.nextToken());

		/////////// end of Input

		LIS();
	}

	static void LIS() {
		int max = 0;
		dp = new int[N];

		for (int i = 0; i < N; i++) {
			dp[i] = 1;

			for (int j = 0; j < i; j++)
				if (cards[j] < cards[i] && dp[i] < dp[j] + 1)
					dp[i] = dp[j] + 1;

			max = Math.max(max, dp[i]);
		}

		System.out.println(max);
	}
}
