import java.io.*;
import java.util.*;

public class Main {

	static int R, C, result;
	static char[][] map;
	static int[] jihoon;
	static int[][] fireTime;
	static Queue<Coord> dq = new ArrayDeque<>();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		jihoon = new int[2]; // 지훈 위치
		fireTime = new int[R][C]; // 불타는 시간
		for (int r = 0; r < R; r++)
			Arrays.fill(fireTime[r], Integer.MAX_VALUE);

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				char ch = str.charAt(c);

				switch (ch) {
				case 'J': // 지훈 초기 위치
					jihoon[0] = r;
					jihoon[1] = c;
					map[r][c] = '.'; // 지나갈 수 있는 공간
					break;
				case 'F': // 불이 난 공간
					dq.offer(new Coord(r, c, 0));
					map[r][c] = ch;
					fireTime[r][c] = 0;
					break;
				default: // 벽 또는 지나갈 수 있는 공간
					map[r][c] = ch;
					break;
				}
			}
		}

		///////////// end of Input

		fireBfs(); // 불이 퍼지는 시간 계산

		System.out.println(jihoonBfs(jihoon[0], jihoon[1]) ? result : "IMPOSSIBLE");
	}

	static boolean jihoonBfs(int sr, int sc) {
		boolean[][] isVisited = new boolean[R][C];

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int now = cur.t;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 탈출한 경우
				if (!isValidCoord(nr, nc)) {
					result = now + 1;
					return true;
				}

				if (isVisited[nr][nc] || map[nr][nc] != '.' || fireTime[nr][nc] <= now + 1)
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc, now + 1));
			}
		}

		return false; // 탈출할 수 없는 경우
	}

	static void fireBfs() {
		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int now = cur.t;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || map[nr][nc] == '#' || fireTime[nr][nc] <= now + 1)
					continue;

				fireTime[nr][nc] = now + 1;
				dq.offer(new Coord(nr, nc, now + 1));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < R && -1 < c && c < C;
	}

	static class Coord {
		int r, c;
		int t; // 이동 시간

		Coord(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
