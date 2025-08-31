import java.io.*;
import java.util.*;

public class Main {

	static int N, K, S, X, Y;
	static int[][] map;
	static List<Coord> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		list = new LinkedList<>();
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] != 0)
					list.add(new Coord(map[r][c], r, c));
			}
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		bfs();
		
		System.out.println(map[X][Y]);
	}

	static void bfs() {
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };

		while (S > 0 && !list.isEmpty()) {
			S--;
			Collections.sort(list, (o1, o2) -> Integer.compare(o1.n, o2.n));
			int size = list.size();

			for (int i = 0; i < size; i++) {
				Coord cur = list.remove(0);
				int n = cur.n;
				int r = cur.r;
				int c = cur.c;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!isValidCoord(nr, nc) || map[nr][nc] != 0)
						continue;

					map[nr][nc] = n;
					list.add(new Coord(n, nr, nc));
				}
			}
		}
	}
	
	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= N;
	}

	static class Coord {
		int n; // 번호
		int r, c; // 좌표

		Coord(int n, int r, int c) {
			this.n = n;
			this.r = r;
			this.c = c;
		}
	}
}
