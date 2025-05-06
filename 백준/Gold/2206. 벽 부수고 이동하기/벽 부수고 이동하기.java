import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static boolean[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			for (int c = 1; c <= M; c++) {
				char ch = str.charAt(c - 1);

				if (ch == '0')
					map[r][c] = true;
			}
		}

		if (bfs(1, 1))
			System.out.println(result);
		else
			System.out.println(-1);
	}

	static boolean bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[2][N + 1][M + 1];

		isVisited[0][sr][sc] = true;
		dq.offer(new Coord(0, sr, sc, 1));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int v = cur.v;
			int t = cur.t;

			if (r == N && c == M) {
				result = t;
				return true; // 가능
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc))
					continue;

				// 이동할 수 있는 곳
				if (map[r][c] && !isVisited[v][nr][nc]) {
					isVisited[v][nr][nc] = true;
					dq.offer(new Coord(v, nr, nc, t + 1));
				} else {
					// 벽을 뚫을 수 있는 경우
					if (v == 0 && !isVisited[v + 1][nr][nc]) {
						isVisited[v + 1][nr][nc] = true;
						dq.offer(new Coord(v + 1, nr, nc, t + 1));
					}

				}
			}
		}

		return false; // 불가능
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= M;
	}

	static class Coord {
		int r, c;
		int v; // 벽 뚫은 횟수
		int t; // 경로

		Coord(int v, int r, int c, int t) {
			this.v = v;
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
