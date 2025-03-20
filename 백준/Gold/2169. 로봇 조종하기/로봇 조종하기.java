import java.io.*;
import java.util.*;

public class Main {

	static int N, M, map[][], dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		////////////////////// end of Input

		// DP 배열 초기화
		dp = new int[N + 1][M + 1];

		// 첫 번째 행 초기화: 각 열의 값은 이전 열의 값에 현재 열의 값을 더한 것
		dp[1][1] = map[1][1];
		for (int c = 2; c <= M; c++)
			dp[1][c] = dp[1][c - 1] + map[1][c];

		// 각 행에 대해 왼쪽에서 오는 경우와 오른쪽에서 오는 경우를 계산
		for (int r = 2; r <= N; r++) {
			int[][] tmp = new int[2][M + 1];

			// 왼쪽에서 오는 경우
			tmp[0][1] = dp[r - 1][1] + map[r][1];
			for (int c = 2; c <= M; c++)
				tmp[0][c] = Math.max(tmp[0][c - 1], dp[r - 1][c]) + map[r][c];

			// 오른쪽에서 오는 경우
			tmp[1][M] = dp[r - 1][M] + map[r][M];
			for (int c = M - 1; c >= 1; c--)
				tmp[1][c] = Math.max(tmp[1][c + 1], dp[r - 1][c]) + map[r][c];

			for (int c = 1; c <= M; c++)
				dp[r][c] = Math.max(tmp[0][c], tmp[1][c]);

		}

		// 최종적으로 N행 M열에 도달하는 최대값을 출력
		System.out.println(dp[N][M]);
	}
}
