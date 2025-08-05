import java.io.*;
import java.util.*;

public class Main {

	static int N, M, sr, sc, answer;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c);

				if (map[r][c] == 'I') {
					sr = r;
					sc = c;
				}
			}
		}

		answer = bfs(sr, sc);
		System.out.println(answer == 0 ? "TT" : answer);
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][M];
		int result = 0;

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] == 'X')
					continue;

				if (map[nr][nc] == 'P')
					result++;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}

		return result;
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
