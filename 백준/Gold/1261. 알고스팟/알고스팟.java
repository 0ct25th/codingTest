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
		M = Integer.parseInt(st.nextToken()); // 가로 크기
		N = Integer.parseInt(st.nextToken()); // 세로 크기

		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++)
				map[r][c] = str.charAt(c) - '0';
		}

		System.out.println(dijkstra(0, 0));
	}

	static int dijkstra(int sr, int sc) {
		Queue<Coord> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
		int[][] minDist = new int[N][M];
		for (int r = 0; r < N; r++)
			Arrays.fill(minDist[r], Integer.MAX_VALUE);
		boolean[][] isVisited = new boolean[N][M];

		minDist[sr][sc] = 0;
		pq.offer(new Coord(sr, sc, minDist[sr][sc]));

		while (!pq.isEmpty()) {
			Coord cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int w = cur.w;

			if (w > minDist[r][c])
				continue;

			if (isVisited[r][c])
				continue;
			isVisited[r][c] = true;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc))
					continue;

				// 벽인 경우
				if (map[nr][nc] == 1) {
					if (minDist[nr][nc] <= minDist[r][c] + 1)
						continue;

					minDist[nr][nc] = minDist[r][c] + 1;
					pq.offer(new Coord(nr, nc, minDist[nr][nc]));
				}

				// 빈 방인 경우
				else {
					minDist[nr][nc] = Math.min(minDist[nr][nc], minDist[r][c]);
					pq.offer(new Coord(nr, nc, minDist[r][c]));
				}
			}
		}

		return minDist[N - 1][M - 1];
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r, c; // 좌표
		int w; // 부순 벽 개수

		Coord(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}
}
