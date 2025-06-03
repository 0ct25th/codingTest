import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, result;
	static List<Integer> list;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int sc = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());

			for (int r = sr; r < er; r++)
				for (int c = sc; c < ec; c++)
					map[r][c] = 1;
		}

		isVisited = new boolean[M][N];
		list = new ArrayList<>();
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (isVisited[r][c] || map[r][c] == 1)
					continue;

				bfs(r, c);
				result++;
			}
		}

		System.out.println(result);

		Collections.sort(list);
		for (int i : list)
			System.out.printf("%d ", i);
	}

	static void bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		int cnt = 1;

		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] == 1)
					continue;

				cnt++;
				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}

		list.add(cnt);
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < M && -1 < c && c < N;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
