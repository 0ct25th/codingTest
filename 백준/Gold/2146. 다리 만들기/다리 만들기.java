import java.io.*;
import java.util.*;

public class Main {

	static int N, map[][];
	static List<Coord> list;
	static Map<Integer, Integer> hash;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		list = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 1)
					list.add(new Coord(r, c));
			}
		}

		// 섬 번호 붙이기
		hash = new HashMap<>();
		isVisited = new boolean[N][N];
		int idx = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (isVisited[r][c] || map[r][c] == 0)
					continue;

				bfs(r, c, idx);
				idx++;
			}
		}

		int result = Integer.MAX_VALUE;
		for (Coord coord : list)
			result = Math.min(result, bfs(coord.r, coord.c));

		System.out.println(result);
	}

	// 다른 섬을 찾는 bfs
	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		isVisited = new boolean[N][N];

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc, hash.get(convert(sr, sc)), 0));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int n = cur.n;
			int dist = cur.dist;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc])
					continue;

				// 섬인 경우
				if (map[nr][nc] == 1) {
					// 같은 섬인 경우
					if (n == hash.get(convert(nr, nc)))
						continue;
					// 다른 섬인 경우
					else
						return dist;
				}
				// 바다인 경우
				else {
					isVisited[nr][nc] = true;
					dq.offer(new Coord(nr, nc, n, dist + 1));
				}
			}

		}

		return Integer.MAX_VALUE;
	}

	// 같은 섬을 찾는 bfs
	static void bfs(int sr, int sc, int idx) {
		Queue<Coord> dq = new ArrayDeque<>();

		hash.put(convert(sr, sc), idx);
		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] == 0)
					continue;

				hash.put(convert(nr, nc), idx);
				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < N;
	}

	static int convert(int r, int c) {
		return r * 1000 + c;
	}

	static class Coord {
		int r, c;
		int n; // 섬 번호
		int dist; // 이동 거리

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}

		Coord(int r, int c, int n, int dist) {
			this.r = r;
			this.c = c;
			this.n = n;
			this.dist = dist;
		}
	}
}
