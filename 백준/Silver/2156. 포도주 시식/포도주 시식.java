import java.io.*;

public class Main {

	static int n, arr[], dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(br.readLine());

		dp = new int[n + 1];
		dp[1] = arr[1];

		if (n >= 2)
			dp[2] = arr[1] + arr[2];

		for (int i = 3; i <= n; i++) {
			// dp[i-1]: i번째 포도주를 마시지 않는 경우
			// dp[i-2] + arr[i]: i번째 포도주를 마시는 경우 (이전 포도주는 마시지 않음)
			// dp[i-3] + arr[i-1] + arr[i]: i번째 포도주와 i-1번째 포도주를 마시는 경우 (이전 포도주는 마시지 않음)
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
		}
		
		// 최대로 마실 수 있는 포도주의 양을 출력
		System.out.println(dp[n]);
	}
}
