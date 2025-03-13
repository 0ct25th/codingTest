import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[][] map, isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		map = new int[n + 1][n + 1];
		for (int r = 1; r <= n; r++) {
			String s = br.readLine();
			for (int c = 1; c <= n; c++)
				map[r][c] = s.charAt(c - 1) - '0';
		}
		///////// end of Input

		System.out.println(dijkstra());
	}

	static int dijkstra() {
		Queue<Coord> pq = new PriorityQueue<>();
		isVisited = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			Arrays.fill(isVisited[i], Integer.MAX_VALUE);

		// 시작점 (1, 1)
		isVisited[1][1] = 0;
		pq.offer(new Coord(1, 1, 0));

		while (!pq.isEmpty()) {
			Coord cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			if (r == n && c == n)
				return cnt;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] <= isVisited[r][c] + 1)
					continue;

				// 흰 방인 경우
				if (map[nr][nc] == 1) {
					// 방문 체크
					isVisited[nr][nc] = isVisited[r][c] + 1;
					// 우선순위 큐에 삽입
					pq.offer(new Coord(nr, nc, cnt));
				}

				// 검은 방인 경우
				else {
					// 방문 체크
					isVisited[nr][nc] = isVisited[r][c] + 1;
					// 우선순위 큐에 삽입
					pq.offer(new Coord(nr, nc, cnt + 1));
				}
			}
		}

		return 0;
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= n && 0 < c && c <= n;
	}

	static class Coord implements Comparable<Coord> {
		int r, c;
		int cnt;

		Coord(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Coord o) {
			return (this.cnt - o.cnt);
		}
	}
}
