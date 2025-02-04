import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		////////////// end of Input

		System.out.println(bfs(0, 0));
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		int[][][] isVisited = new int[2][N][M];

		isVisited[0][sr][sc] = 1;
		dq.offer(new Coord(sr, sc, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			// 도착한 경우
			if (r == N - 1 && c == M - 1)
				return isVisited[cnt][r][c];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 유효범위 벗어남 || 이미 방문
				if (!isValidCoord(nr, nc) || isVisited[cnt][nr][nc] != 0)
					continue; // 넘기기

				// 이동할 수 있는 곳
				if (map[nr][nc] == 0) {
					isVisited[cnt][nr][nc] = isVisited[cnt][r][c] + 1;
					dq.offer(new Coord(nr, nc, cnt));
				}

				// 이동할 수 없는 벽
				else if (map[nr][nc] == 1 && cnt < 1) {
					isVisited[cnt + 1][nr][nc] = isVisited[cnt][r][c] + 1;
					dq.offer(new Coord(nr, nc, cnt + 1));
				}
			}
		}

		return -1;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r, c;
		int cnt; // 벽 부순 횟수

		Coord(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

}
