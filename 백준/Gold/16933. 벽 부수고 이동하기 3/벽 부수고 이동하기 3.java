import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			for (int c = 1; c <= M; c++)
				map[r][c] = str.charAt(c - 1) - '0';
		}

		System.out.println(bfs(1, 1));
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new PriorityQueue<>((o1, o2) -> (o1.dist - o2.dist));
		boolean[][][] isVisited = new boolean[K + 1][N + 1][M + 1];

		isVisited[0][sr][sc] = true;
		dq.offer(new Coord(sr, sc, 1, 0, true));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int dist = cur.dist;
			int cnt = cur.cnt;
			boolean day = cur.day;

			if (r == N && c == M)
				return dist;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[cnt][nr][nc])
					continue;

				// 이동할 수 있는 곳
				if (map[nr][nc] == 0) {
					isVisited[cnt][nr][nc] = true;
					dq.offer(new Coord(nr, nc, dist + 1, cnt, !day));
				}

				// 이동할 수 없는 벽
				else {
					if (cnt + 1 > K || isVisited[cnt + 1][nr][nc])
						continue;

					// 낮인 경우
					if (day) {
						isVisited[cnt + 1][nr][nc] = true;
						dq.offer(new Coord(nr, nc, dist + 1, cnt + 1, !day));
					}
					// 밤인 경우
					else 
						dq.offer(new Coord(r, c, dist + 1, cnt, !day));
				}
			}

		}

		return -1;
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= M;
	}

	static class Coord {
		int r, c; // 좌표
		int cnt; // 벽 부순 횟수
		int dist; // 거리
		boolean day; // t: 낮, f: 밤

		Coord(int r, int c, int dist, int cnt, boolean day) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.cnt = cnt;
			this.day = day;
		}
	}
}
