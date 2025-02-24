import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다.
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		// 다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다.
		// 0은 빈 방을 의미하고, 1은 벽을 의미한다.
		map = new int[N + 1][M + 1];
		for (int r = 1; r <= N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++)
				map[r][c + 1] = str.charAt(c) - '0';
		}
		/////////// end of Input

		System.out.println(dijkstra(1, 1));
	}

	static int dijkstra(int sr, int sc) {
		Queue<Coord> pq = new PriorityQueue<>();
		isVisited = new boolean[N + 1][M + 1];

		// 시작점
		isVisited[sr][sc] = true;
		pq.offer(new Coord(sr, sc, 0));

		while (!pq.isEmpty()) {
			Coord cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			// 도착한 경우
			if (r == N && c == M)
				return cnt;

			// 4방향 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 유효범위 밖 || 이미 방문
				if (!isValidCoord(nr, nc) || isVisited[nr][nc])
					continue;

				// 빈 방인 경우
				if (map[nr][nc] == 0) {
					isVisited[nr][nc] = true;
					pq.offer(new Coord(nr, nc, cnt));
				}

				// 벽인 경우
				else if (map[nr][nc] == 1) {
					isVisited[nr][nc] = true;
					pq.offer(new Coord(nr, nc, cnt + 1));
				}
			}
		}

		return 0;
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= M;
	}

	static class Coord implements Comparable<Coord> {
		int r, c;
		int cnt; // 벽 부순 횟수

		Coord(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Coord o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}
}
