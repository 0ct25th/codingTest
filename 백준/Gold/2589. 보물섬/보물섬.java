import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static List<Coord> landLists;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로

		map = new int[N][M];
		landLists = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				char ch = str.charAt(c);

				if (ch == 'L') {
					map[r][c] = 1; // 육지
					landLists.add(new Coord(r, c));
				} else
					map[r][c] = 0; // 바다
			}
		}

		int result = 0;
		for (int i = 0; i < landLists.size(); i++) {
			Coord cur = landLists.get(i);

			result = Math.max(result, bfs(cur.r, cur.c));
		}

		System.out.println(result);
	}

	static int bfs(int sr, int sc) {
		int max = 0;
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][M];

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc, 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;

			max = Math.max(max, cnt);
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] == 0)
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc, cnt + 1));
			}
		}

		return max;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r, c;
		int cnt;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}

		Coord(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}
