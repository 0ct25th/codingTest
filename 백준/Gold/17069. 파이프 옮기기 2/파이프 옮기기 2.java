import java.io.*;
import java.util.*;

public class Main {

	static int N, map[][];
	static long dp[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dynamicProgramming();

		System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
	}

	static void dynamicProgramming() {
		dp = new long[3][N + 1][N + 1];
		dp[0][1][2] = 1; // 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지

		for (int r = 1; r <= N; r++) {
			for (int c = 3; c <= N; c++) {
				// 현재 좌표가 벽인 경우
				if (map[r][c] == 1)
					continue;

				dp[0][r][c] = dp[0][r][c - 1] + dp[2][r][c - 1]; // 가로
				dp[1][r][c] = dp[1][r - 1][c] + dp[2][r - 1][c]; // 세로
				if (map[r - 1][c] == 0 && map[r][c - 1] == 0) // 대각선
					dp[2][r][c] = dp[0][r - 1][c - 1] + dp[1][r - 1][c - 1] + dp[2][r - 1][c - 1];
			}
		}
	}
}
