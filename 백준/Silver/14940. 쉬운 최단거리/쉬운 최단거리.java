import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int n, m;
	static int[][] map, minDist;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[] start = new int[2];
		map = new int[n][m];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 2) {
					start[0] = r;
					start[1] = c;
				}
			}
		}

		bfs(start[0], start[1]);

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (map[r][c] == 0 || map[r][c] == 2)
					sb.append("0 ");
				else {
					if (minDist[r][c] == INF)
						sb.append("-1 ");
					else
						sb.append(minDist[r][c]).append(" ");
				}
			}

			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		minDist = new int[n][m];
		for (int r = 0; r < n; r++)
			Arrays.fill(minDist[r], INF);

		minDist[sr][sc] = 0;
		dq.offer(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || minDist[nr][nc] != INF || map[nr][nc] == 0)
					continue;

				minDist[nr][nc] = minDist[r][c] + 1;
				dq.offer(new Coord(nr, nc));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < n && -1 < c && c < m;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
