import java.io.*;
import java.util.*;

public class Main {

	static int I;
	static int[][] map;
	static int[] dr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			I = Integer.parseInt(br.readLine());
			map = new int[I][I];

			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());

			System.out.println(bfs(sr, sc, er, ec));
		}
	}

	static int bfs(int sr, int sc, int er, int ec) {
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[I][I];

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int m = cur.m;

			if (r == er && c == ec)
				return m;

			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc])
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc, m + 1));
			}
		}

		return -1;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < I && -1 < c && c < I;
	}

	static class Coord {
		int r, c;
		int m; // 움직인 횟수

		Coord(int r, int c, int m) {
			this.r = r;
			this.c = c;
			this.m = m;
		}
	}
}
