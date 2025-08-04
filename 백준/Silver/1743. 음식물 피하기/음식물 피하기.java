import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, result;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[r][c] = 1;
		}

		isVisited = new boolean[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if (isVisited[r][c] || map[r][c] == 0)
					continue;

				result = Math.max(result, bfs(r, c));
			}
		}
		
		System.out.println(result);
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		int size = 1;

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

				size++;
				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}

		return size;
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= M;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
