import java.io.*;
import java.util.*;

public class Main {
	static int N, M, sr, sc, sd, result;
	static int[][] map;
	// 북, 동, 남, 서 순서로 방향 벡터 정의
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		sd = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}

		simulation(sr, sc, sd);
		System.out.println(result);
	}

	static void simulation(int r, int c, int d) {
		while (true) {
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			if (map[r][c] == 0) {
				map[r][c] = 2;
				result++;
			}

			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if (isAroundNotEmpty(r, c)) {
				// 후진할 좌표 계산 (현재 방향의 반대)
				int backDir = (d + 2) % 4;
				int nr = r + dr[backDir];
				int nc = c + dc[backDir];

				// 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
				if (isValidCoord(nr, nc) && map[nr][nc] != 1) {
					r = nr;
					c = nc;
				}
				// 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
				else {
					return;
				}
			}
			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			else {
				// 3-1. 반시계 방향으로 90도 회전한다.
				d = (d + 3) % 4; // 반시계 회전

				// 회전 후 앞쪽 좌표 계산
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
				if (isValidCoord(nr, nc) && map[nr][nc] == 0) {
					r = nr;
					c = nc;
					// 3-3. 1번으로 돌아간다.
				}
			}
		}
	}

	static boolean isAroundNotEmpty(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!isValidCoord(nr, nc))
				continue;

			if (map[nr][nc] == 0)
				return false;
		}
		return true;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}
}
