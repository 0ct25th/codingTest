import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean result;
	static boolean[][] map, isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new boolean[M][N];
		for (int r = 0; r < M; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				int num = str.charAt(c) - '0';

				// 검은색
				if (num == 1)
					map[r][c] = false;
				// 흰색
				else
					map[r][c] = true;
			}
		}
		/////////////// end of Input

		result = false;
		isVisited = new boolean[M][N];
		for (int c = 0; c < N; c++) {
			if (map[0][c] && !isVisited[0][c])
				bfs(0, c);

			if (result)
				break;
		}

		System.out.println(result ? "YES" : "NO");
	}

	static void bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			if (r == M - 1) {
				result = true;
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || !map[nr][nc])
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < M && -1 < c && c < N;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
