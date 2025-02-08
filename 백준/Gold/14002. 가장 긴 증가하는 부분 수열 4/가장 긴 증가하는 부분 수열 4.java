import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static int[] A, dp;
	static Stack<Integer> stk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		/////////////////////// end of Input

		// 가장 긴 증가하는 부분 수열의 길이 구하기
		LIS();

		// 수열 찾기
		sequence();

		// 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
		System.out.println(result);

		// 둘째 줄에는 가장 긴 증가하는 부분 수열을 출력한다. 그러한 수열이 여러가지인 경우 아무거나 출력한다.
		while(!stk.isEmpty())
			System.out.print(stk.pop() + " ");

		br.close();
	}

	static void sequence() {
		int value = result; // 가장 긴 증가하는 부분 수열 길이 값
		stk = new Stack<>(); // 경로를 역추적할 스택

		for (int i = N - 1; i > -1; i--) {
			// 현재 찾는 길이와 같은 겨우
			if (value == dp[i]) {
				stk.push(A[i]);
				value--; // 다음 찾을 길이
			}
		}
	}

	static void LIS() {
		dp = new int[N];
		result = 1;

		for (int i = 0; i < N; i++) {
			dp[i] = 1;

			for (int j = 0; j < i; j++) {
				if (A[j] < A[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}

			result = Math.max(result, dp[i]);
		}
	}
}