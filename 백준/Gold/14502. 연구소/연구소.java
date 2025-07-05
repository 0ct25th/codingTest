import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[][] original;
	static List<Coord> emptyList, virousList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		original = new int[N][M];
		emptyList = new ArrayList<>();
		virousList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				original[r][c] = Integer.parseInt(st.nextToken());

				if (original[r][c] == 0) // 빈 칸
					emptyList.add(new Coord(r, c));
				else if (original[r][c] == 2) // 바이러스
					virousList.add(new Coord(r, c));
			}
		}

		pickCoord(0, 0, new ArrayList<>());

		System.out.println(result);
	}

	static void pickCoord(int depth, int start, List<Coord> coordList) {
		if (depth == 3) {
			result = Math.max(result, simulation(coordList));

			return;
		}

		for (int i = start; i < emptyList.size(); i++) {
			coordList.add(emptyList.get(i));
			pickCoord(depth + 1, i + 1, coordList);
			coordList.remove(coordList.size() - 1);
		}
	}

	static int simulation(List<Coord> coordList) {
		int cnt = 0;
		int[][] map = copyMap();
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][M];

		// 벽 세우기
		for (Coord coord : coordList)
			map[coord.r][coord.c] = 1;

		// 바이러스
		for (Coord coord : virousList) {
			isVisited[coord.r][coord.c] = true;
			dq.offer(coord);
		}

		// 바이러스 퍼뜨리기
		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] != 0)
					continue;

				map[nr][nc] = 2;
				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
			}
		}

		// 안전영역 카운트
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] != 0)
					continue;

				cnt++;
			}
		}

		return cnt;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static int[][] copyMap() {
		int[][] map = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++)
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
