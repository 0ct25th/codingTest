import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;

	static int N, M, result;
	static int[][] original;
	static List<Coord> virousList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		original = new int[N][N];
		virousList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int n = Integer.parseInt(st.nextToken());

				if (n == 2) {
					virousList.add(new Coord(r, c));
					original[r][c] = 0;
				} else
					original[r][c] = n;

			}
		}

		result = Integer.MAX_VALUE;
		pickCoord(0, 0, new ArrayList<>());
		
		System.out.println(result == INF ? -1 : result);
	}

	static void pickCoord(int depth, int start, List<Coord> coordList) {
		if (depth == M) {
			result = Math.min(result, simulation(coordList));

			return;
		}

		for (int i = start; i < virousList.size(); i++) {
			coordList.add(virousList.get(i));
			pickCoord(depth + 1, i + 1, coordList);
			coordList.remove(coordList.size() - 1);
		}
	}

	static int simulation(List<Coord> coordList) {
		int[][] map = copyMap();
		int[][] minTime = new int[N][N];
		for (int r = 0; r < N; r++)
			Arrays.fill(minTime[r], INF);
		Queue<Coord> dq = new ArrayDeque<>();

		// 바이러스
		for (Coord coord : coordList) {
			int r = coord.r;
			int c = coord.c;

			minTime[r][c] = 0;
			dq.offer(new Coord(r, c));
		}

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || minTime[nr][nc] != INF || map[nr][nc] == 1)
					continue;

				minTime[nr][nc] = minTime[r][c] + 1;
				dq.offer(new Coord(nr, nc));
			}
		}

		return calc(map, minTime);
	}

	static int calc(int[][] map, int[][] minTime) {
		int time = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 모든 빈 칸에 퍼뜨릴 수 없는 경우
				if (map[r][c] == 0 && minTime[r][c] == INF)
					return INF;

				// 벽은 넘겨
				if (map[r][c] == 1)
					continue;

				time = Math.max(time, minTime[r][c]);
			}
		}

		return time;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < N;
	}

	static int[][] copyMap() {
		int[][] map = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++)
				map[r][c] = original[r][c];
		}

		return map;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
