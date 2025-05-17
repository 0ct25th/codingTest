import java.io.*;
import java.util.*;

public class Main {

	static int N, A[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		System.out.println(LIS());
	}

	static int LIS() {
		int[] dp = new int[N];
		int result = 0;

		for (int i = 0; i < N; i++) {
			dp[i] = 1;

			for (int j = 0; j < i; j++) {
				if (A[j] < A[i])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}

			result = Math.max(result, dp[i]);
		}

		return result;
	}
}
