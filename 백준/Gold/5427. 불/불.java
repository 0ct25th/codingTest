import java.io.*;
import java.util.*;

public class Main {

	static int w, h, result;
	static int[] start;
	static char[][] map;
	static int[][] fireTime;
	static Queue<Coord> dq;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new char[h][w];
			start = new int[2];
			fireTime = new int[h][w];
			for (int r = 0; r < h; r++)
				Arrays.fill(fireTime[r], Integer.MAX_VALUE);
			dq = new ArrayDeque<>();

			for (int r = 0; r < h; r++) {
				String str = br.readLine();
				for (int c = 0; c < w; c++) {
					char ch = str.charAt(c);

					switch (ch) {
					case '@': // 상근이의 시작 위치
						start[0] = r;
						start[1] = c;
						map[r][c] = '.'; // 빈공간
						break;

					case '*': // 불
						map[r][c] = ch;
						fireTime[r][c] = 0;
						dq.offer(new Coord(r, c, 0));
						break;

					default:
						map[r][c] = ch;
						break;
					}
				}
			}

			fire();
			
			if (bfs(start[0], start[1]))
				sb.append(result).append("\n");
			else
				sb.append("IMPOSSIBLE\n");
		} // end of TestCase

		System.out.println(sb);
	}

	static boolean bfs(int sr, int sc) {
		boolean[][] isVisited = new boolean[h][w];

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc)) {
					result = t + 1;
					return true;
				}

				if (isVisited[nr][nc] || map[nr][nc] != '.' || fireTime[nr][nc] <= t + 1)
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc, t + 1));
			}
		}

		return false; // 탈출이 불가능한 경우
	}

	static void fire() {
		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || map[nr][nc] != '.' || fireTime[nr][nc] <= t + 1)
					continue;

				fireTime[nr][nc] = t + 1;
				dq.offer(new Coord(nr, nc, t + 1));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < h && -1 < c && c < w;
	}

	static class Coord {
		int r, c; // 위치
		int t; // 초

		Coord(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
