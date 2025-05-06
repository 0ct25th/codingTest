import java.io.*;
import java.util.*;

public class Main {

	static int K, W, H;
	static int[][] map;
	static int[] drM = { -1, 1, 0, 0 };
	static int[] dcM = { 0, 0, -1, 1 };
	static int[] drH = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dcH = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}

		System.out.println(bfs(0, 0));
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][][] isVisited = new boolean[K + 1][H][W];

		isVisited[0][sr][sc] = true;
		dq.offer(new Coord(sr, sc, 0, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int k = cur.k;
			int m = cur.m;

			if (r == H - 1 && c == W - 1)
				return m;

			// 말
			if (k + 1 <= K) {
				for (int d = 0; d < 8; d++) {
					int nr = r + drH[d];
					int nc = c + dcH[d];

					if (!isValidCoord(nr, nc) || map[nr][nc] == 1 || isVisited[k + 1][nr][nc])
						continue;

					isVisited[k + 1][nr][nc] = true;
					dq.offer(new Coord(nr, nc, k + 1, m + 1));
				}
			}

			// 원숭이
			for (int d = 0; d < 4; d++) {
				int nr = r + drM[d];
				int nc = c + dcM[d];

				if (!isValidCoord(nr, nc) || map[nr][nc] == 1 || isVisited[k][nr][nc])
					continue;

				isVisited[k][nr][nc] = true;
				dq.offer(new Coord(nr, nc, k, m + 1));
			}
		}

		return -1;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < H && -1 < c && c < W;
	}

	static class Coord {
		int r, c;
		int k; // 능력 사용
		int m; // 동작수

		Coord(int r, int c, int k, int m) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.m = m;
		}
	}
}
