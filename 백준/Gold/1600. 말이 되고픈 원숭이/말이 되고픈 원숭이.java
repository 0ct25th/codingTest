import java.io.*;
import java.util.*;

public class Main {

	static int K, W, H;
	static int[][] map; // 0: 평지, 1: 장애물
	// 원숭이 움직임
	static int[] mDr = { -1, 1, 0, 0 };
	static int[] mDc = { 0, 0, -1, 1 };
	// 말 움직임
	static int[] hDr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] hDc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// K번만 말의 움직임으로 움직일 수 있음
		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 가로 길이
		H = Integer.parseInt(st.nextToken()); // 세로 길이

		map = new int[H][W];
		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs(0, 0));
	}

	static int bfs(int sr, int sc) {
		Queue<Monkey> dq = new ArrayDeque<>();
		int[][][] isVisited = new int[H][W][K + 1];

		isVisited[sr][sc][0] = 0;
		dq.offer(new Monkey(sr, sc, 0));

		while (!dq.isEmpty()) {
			Monkey cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.count;

			if (r == H - 1 && c == W - 1)
				return isVisited[r][c][cnt];

			// 말로 변할 수 있는 경우
			if (cnt < K) {
				// 말로 8방향 탐색
				for (int d = 0; d < 8; d++) {
					int nr = r + hDr[d];
					int nc = c + hDc[d];

					// 유효범위 외 || 이미 방문 || 장애물
					if (!isValidCoord(nr, nc) || isVisited[nr][nc][cnt + 1] != 0 || map[nr][nc] == 1)
						continue; // 넘기기

					isVisited[nr][nc][cnt + 1] = isVisited[r][c][cnt] + 1;
					dq.offer(new Monkey(nr, nc, cnt + 1));
				}
			}

			// 원숭이로 4방향 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + mDr[d];
				int nc = c + mDc[d];

				// 유효범위 외 || 이미 방문 || 장애물
				if (!isValidCoord(nr, nc) || isVisited[nr][nc][cnt] != 0 || map[nr][nc] == 1)
					continue; // 넘기기

				isVisited[nr][nc][cnt] = isVisited[r][c][cnt] + 1;
				dq.offer(new Monkey(nr, nc, cnt));
			}
		}

		return -1;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < H && -1 < c && c < W;
	}

	static class Monkey {
		int r;
		int c;
		int count; // 말로 변한 횟수

		Monkey(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
}
