import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;

	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}

		while (true) {
			// 빙산이 한 덩어리가 아닌 경우
			if (check() > 1)
				break;

			// 빙산 녹이기
			melting();

			// 빙산이 다 녹아버린 경우
			if (check() == 0) {
				result = 0;
				break;
			}

			// 시간
			result++;
		}

		System.out.println(result);
	}

	static void melting() {
		List<Coord> lst = new ArrayList<>();

		// 바닷물 개수 카운트
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0)
					continue;

				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!isValidCoord(nr, nc) || map[nr][nc] != 0)
						continue;

					cnt++;
				}

				lst.add(new Coord(r, c, cnt));
			}
		}

		// 빙산 녹이기
		for (Coord coord : lst) {
			int r = coord.r;
			int c = coord.c;
			int cnt = coord.cnt;

			map[r][c] = Math.max(0, map[r][c] - cnt);
		}
	}

	static int check() {
		int cnt = 0; // 빙산 개수 세기
		isVisited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0 || isVisited[r][c])
					continue;

				bfs(r, c);
				cnt++; // 빙산 개수 증가
			}

			if (cnt > 1)
				break;
		}

		return cnt;
	}

	static void bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] == 0)
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r, c; // 위치
		int cnt; // 바다 개수

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}

		Coord(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}
