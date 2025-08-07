import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map, isVisited;
	static Queue<Coord> dq = new ArrayDeque<>();
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		isVisited = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					dq.offer(new Coord(r, c));
					isVisited[r][c] = 0;
				} else {
					isVisited[r][c] = -1;
				}
			}
		}
		
		bfs();

		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				result = Math.max(result, isVisited[r][c]);
			}
		}

		System.out.println(result);
	}

	static void bfs() {
		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] != -1)
					continue;

				isVisited[nr][nc] = isVisited[r][c] + 1;
				dq.offer(new Coord(nr, nc));

			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
