import java.io.*;
import java.util.*;

public class Main {

	static int T, W, result;
	static int[] plum, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		plum = new int[T + 1];
		for (int i = 1; i <= T; i++)
			plum[i] = Integer.parseInt(br.readLine());

		dp = new int[T + 1][W + 1]; // [현재시간][자두이동횟수]
		for (int t = 1; t <= T; t++) {
			int tree = plum[t]; // 현재 시간에 자두가 떨어지는 나무 번호

			// 이동 횟수가 0인 경우 (계속 1번 나무에 있음)
			if (tree == 1) // 1번 나무에서 자두가 떨어지면
				dp[t][0] = dp[t - 1][0] + 1; // 자두를 받을 수 있음
			else // 2번 나무에서 자두가 떨어지면
				dp[t][0] = dp[t - 1][0]; // 자두를 받을 수 없음

			// 이동 횟수가 1 이상인 경우들
			for (int w = 1; w <= W; w++) {
				if (tree == 1) { // 1번 나무에서 자두가 떨어지는 경우
					if (w % 2 == 0) { // 이동 횟수가 짝수 = 현재 1번 나무에 위치
						// 이전에 1번 나무에 있었거나(w번 이동), 2번 나무에서 이동해왔거나(w-1번 이동)
						dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1]);
					} else { // 이동 횟수가 홀수 = 현재 2번 나무에 위치
						// 2번 나무에서 1번 나무로 이동해서 자두를 받거나, 2번 나무에 그대로 있거나
						dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
					}
				} else { // 2번 나무에서 자두가 떨어지는 경우
					if (w % 2 == 0) { // 이동 횟수가 짝수 = 현재 1번 나무에 위치
						// 1번 나무에서 2번 나무로 이동해서 자두를 받거나, 1번 나무에 그대로 있거나
						dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
					} else { // 이동 횟수가 홀수 = 현재 2번 나무에 위치
						// 이전에 2번 나무에 있었거나(w번 이동), 1번 나무에서 이동해왔거나(w-1번 이동)
						dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1]);
					}
				}
			}
		}
		
		for(int w = 0; w <= W; w++)
			result = Math.max(result, dp[T][w]);
		
		System.out.println(result);
	}
}
