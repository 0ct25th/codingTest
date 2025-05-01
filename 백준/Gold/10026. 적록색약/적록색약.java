import java.io.*;
import java.util.*;

public class Main {

	static int N, cCnt, bCnt;
	static char[][] common, blind;
	static boolean[][] isC, isB;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		common = new char[N][N];
		blind = new char[N][N];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				char ch = str.charAt(c);

				// 적록색약이 아닌 사람
				common[r][c] = ch;
				// 적록색약인 사람
				if (ch == 'G')
					blind[r][c] = 'R';
				else
					blind[r][c] = ch;
			}
		}

		isC = new boolean[N][N];
		isB = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 적록색약이 아닌 사람
				if (!isC[r][c]) {
					bfs(r, c, common[r][c], 'c');
					cCnt++;
				}

				// 적록색약인 사람
				if (!isB[r][c]) {
					bfs(r, c, blind[r][c], 'b');
					bCnt++;
				}
			}
		}

		System.out.printf("%d %d", cCnt, bCnt);
	}

	static void bfs(int sr, int sc, char color, char ch) {
		Queue<Coord> dq = new ArrayDeque<>();

		dq.offer(new Coord(sr, sc));
		if (ch == 'c')
			isC[sr][sc] = true;
		else
			isB[sr][sc] = true;

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc))
					continue;

				if (ch == 'c') {
					if (isC[nr][nc] || common[nr][nc] != color)
						continue;

					isC[nr][nc] = true;
				} else {
					if (isB[nr][nc] || blind[nr][nc] != color)
						continue;

					isB[nr][nc] = true;
				}
				dq.offer(new Coord(nr, nc));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < N;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
