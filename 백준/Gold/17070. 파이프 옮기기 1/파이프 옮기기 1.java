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

		///////////// end of Input

		dynamicProgramming();

		System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
	}

	static void dynamicProgramming() {
		// 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수 저장
		dp = new long[3][N + 1][N + 1]; // [0:가로/1:세로/2:대각선][행][열]
		dp[0][1][2] = 1;

		for (int r = 1; r <= N; r++) {
			for (int c = 3; c <= N; c++) {
				// 벽인 경우
				if (map[r][c] == 1)
					continue;

				// 파이프가 가로로 놓임
				dp[0][r][c] = dp[0][r][c - 1] + dp[2][r][c - 1];

				// 파이프가 세로로 놓임
				dp[1][r][c] = dp[1][r - 1][c] + dp[2][r - 1][c];

				// 파이프가 대각선으로 놓임
				if (map[r - 1][c] == 0 && map[r][c - 1] == 0) {
					dp[2][r][c] = dp[0][r - 1][c - 1] + dp[1][r - 1][c - 1] + dp[2][r - 1][c - 1];
				}
			}
		}
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
