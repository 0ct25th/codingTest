import java.io.*;
import java.util.*;

public class Main {

	static int M, N, K;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());

				map[Y][X] = 1;
			}

			int result = 0;
			isVisited = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (isVisited[r][c] || map[r][c] == 0)
						continue;

					result++;
					bfs(r, c);
				}
			}

			sb.append(result).append("\n");
		}

		System.out.println(sb);
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
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
