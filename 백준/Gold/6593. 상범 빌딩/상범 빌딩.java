import java.io.*;
import java.util.*;

public class Main {

	static int L, R, C;
	static Coord start, end;
	static char[][][] map;
	static int[] dl = { -1, 1, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (L == 0 && R == 0 && C == 0)
				break;

			map = new char[L][R][C];
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
					String str = br.readLine();
					for (int c = 0; c < C; c++) {
						map[l][r][c] = str.charAt(c);

						if (map[l][r][c] == 'S')
							start = new Coord(l, r, c);
						else if (map[l][r][c] == 'E')
							end = new Coord(l, r, c);
					}
				}
				br.readLine();
			}

			int result = bfs(start.l, start.r, start.c);
			if (result == -1)
				sb.append("Trapped!\n");
			else
				sb.append("Escaped in ").append(result).append(" minute(s).\n");

		} // end of TestCase

		System.out.println(sb);
	}

	static int bfs(int sl, int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[L][R][C];

		isVisited[sl][sr][sc] = true;
		dq.offer(new Coord(sl, sr, sc, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int l = cur.l;
			int r = cur.r;
			int c = cur.c;

			if (l == end.l && r == end.r && c == end.c)
				return cur.t;

			for (int d = 0; d < 6; d++) {
				int nl = l + dl[d];
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nl, nr, nc) || isVisited[nl][nr][nc] || map[nl][nr][nc] == '#')
					continue;

				isVisited[nl][nr][nc] = true;
				dq.offer(new Coord(nl, nr, nc, cur.t + 1));
			}
		}

		return -1;
	}

	static boolean isValidCoord(int l, int r, int c) {
		return -1 < l && l < L && -1 < r && r < R && -1 < c && c < C;
	}

	static class Coord {
		int l, r, c;
		int t;

		Coord(int l, int r, int c) {
			this.l = l;
			this.r = r;
			this.c = c;
		}

		Coord(int l, int r, int c, int t) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
