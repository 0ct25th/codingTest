import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static int[] A, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		//////////////////////////// end of Input

		LIS();

		System.out.println(result);
	}

	static void LIS() {
		dp = new int[N];

		for (int i = 0; i < N; i++) {
			dp[i] = 1;

			// 주어진 수열에 첫번째 부터 i번째 전까지 탐색
			for (int j = 0; j < i; j++)
				// 자신보다 큰 값 && 자신을 끝에 놓았을 때 최대값 갱신 여부 확인
				if (A[i] < A[j] && dp[i] < dp[j] + 1)
					dp[i] = dp[j] + 1;

			result = Math.max(result, dp[i]);
		}
	}
}
