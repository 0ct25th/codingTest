import java.io.*;
import java.util.*;

public class Main {

	static int N, K, S, X, Y;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Queue<Coord> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		pq = new PriorityQueue<>();
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 0)
					continue;

				pq.offer(new Coord(r, c, map[r][c], 0));
			}
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		bfs();

		System.out.println(map[X][Y]);

	}

	static void bfs() {
		while (!pq.isEmpty()) {
			Coord cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			if (r == X && c == Y)
				return;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || map[nr][nc] != 0 || cnt + 1 > S)
					continue;

				map[nr][nc] = map[r][c];
				pq.offer(new Coord(nr, nc, map[nr][nc], cnt + 1));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= N;
	}

	static class Coord implements Comparable<Coord> {
		int r, c;
		int num;
		int cnt;

		Coord(int r, int c, int num, int cnt) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Coord o) {
			if (this.cnt == o.cnt)
				return (this.num - o.num);

			return (this.cnt - o.cnt);
		}

	}
}
