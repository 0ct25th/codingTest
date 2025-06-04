import java.io.*;
import java.util.*;

public class Main {

	static int N, maxHeight;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[r][c]);
			}
		}

		int result = 0;
		for (int h = 0; h <= maxHeight; h++) {
			isVisited = new boolean[N][N];
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (isVisited[r][c] || map[r][c] <= h)
						continue;

					cnt++;
					bfs(r, c, h);
				}
			}

			result = Math.max(result, cnt);
		}
		
		System.out.println(result);
	}

	static void bfs(int sr, int sc, int h) {
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

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] <= h)
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < N;
	}

	static class Coord {
		int r, c; // 좌표

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
