import java.io.*;
import java.util.*;

public class Main {

	final static int RED = 0;
	final static int GREEN = 1;
	final static int BLUE = 2;

	static int N;
	static int[][] cost, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		// 입력받은 비용을 저장할 배열
		cost = new int[N][3];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			cost[r][RED] = Integer.parseInt(st.nextToken());
			cost[r][GREEN] = Integer.parseInt(st.nextToken());
			cost[r][BLUE] = Integer.parseInt(st.nextToken());
		}

		// dp 배열 초기화
		dp = new int[N][3];

		// 첫 번째 집의 색칠 비용을 dp에 저장
		dp[0][RED] = cost[0][RED];
		dp[0][GREEN] = cost[0][GREEN];
		dp[0][BLUE] = cost[0][BLUE];

		// 1부터 N-1까지 각 i별 i-1의 서로 다른 색상 중 최솟값을 누적하여 더함
		for (int i = 1; i < N; i++) {
			dp[i][RED] = cost[i][RED] + Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]);
			dp[i][GREEN] = cost[i][GREEN] + Math.min(dp[i - 1][RED], dp[i - 1][BLUE]);
			dp[i][BLUE] = cost[i][BLUE] + Math.min(dp[i - 1][RED], dp[i - 1][GREEN]);
		}

		// 최종 결과 출력
		System.out.println(Math.min(dp[N - 1][RED], Math.min(dp[N - 1][GREEN], dp[N - 1][BLUE])));
	}
}
