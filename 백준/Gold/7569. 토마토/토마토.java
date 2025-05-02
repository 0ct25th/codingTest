import java.io.*;
import java.util.*;

public class Main {

	static int M, N, H, cnt, result;
	static int[][][] map;
	static boolean[][][] isVisited;
	static Queue<Coord> dq = new ArrayDeque<>();
	static int[] dh = { -1, 1, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		H = Integer.parseInt(st.nextToken()); // 높이

		map = new int[H][N][M];
		isVisited = new boolean[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					int num = Integer.parseInt(st.nextToken());
					map[h][r][c] = num;

					if (num == 1) { // 익은 토마토
						isVisited[h][r][c] = true;
						dq.offer(new Coord(h, r, c));
					} else if (num == 0) // 안익은 토마토
						cnt++;
				}
			}
		}

		while (cnt > 0 && !dq.isEmpty()) {
			result++;
			bfs();
		}

		if (cnt > 0)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	static void bfs() {
		int size = dq.size();

		for (int i = 0; i < size; i++) {
			Coord cur = dq.poll();
			int h = cur.h;
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 6; d++) {
				int nh = h + dh[d];
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nh, nr, nc) || isVisited[nh][nr][nc] || map[nh][nr][nc] != 0)
					continue;

				cnt--;
				isVisited[nh][nr][nc] = true;
				dq.offer(new Coord(nh, nr, nc));
			}
		}
	}

	static boolean isValidCoord(int h, int r, int c) {
		return -1 < h && h < H && -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int h, r, c;

		Coord(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
}
