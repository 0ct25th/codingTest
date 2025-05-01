import java.io.*;
import java.util.*;

public class Main {

	static int n, m, cnt, extent;

	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로
		m = Integer.parseInt(st.nextToken()); // 가로

		map = new int[n][m];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}
		////////////////// end of Input

		isVisited = new boolean[n][m];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (isVisited[r][c] || map[r][c] == 0)
					continue;

				extent = Math.max(extent, bfs(r, c)); // 넓이 갱신
				cnt++; // 개수 카운트
			}
		}

		System.out.println(cnt);
		System.out.println(extent);
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		int extent = 1;

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

				extent++; // 그림 넓이 증가
				isVisited[nr][nc] = true; // 방문 체크
				dq.offer(new Coord(nr, nc));
			}
		}

		return extent;
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
