import java.io.*;
import java.util.*;

public class Main {
	static int N, M, result;
	static int[][] map;
	static List<Coord> cctvs;

	// 각 CCTV 타입별 가능한 방향들
	static int[][][] directions = { {}, // 0번 사용안함
			{ { 0 }, { 1 }, { 2 }, { 3 } }, // 1번 CCTV: 한 방향
			{ { 0, 1 }, { 2, 3 } }, // 2번 CCTV: 상하, 좌우
			{ { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } }, // 3번 CCTV: 상좌, 상우, 하좌, 하우
			{ { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3 } }, // 4번 CCTV: 세 방향
			{ { 0, 1, 2, 3 } } // 5번 CCTV: 모든 방향
	};

	// 상, 하, 좌, 우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cctvs = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (0 < map[r][c] && map[r][c] < 6)
					cctvs.add(new Coord(map[r][c], r, c));
			}
		}

		result = Integer.MAX_VALUE;
		recursion(0, copyMap(map));
		System.out.println(result);
	}

	static void recursion(int cctvIndex, int[][] currentMap) {
		if (cctvIndex == cctvs.size()) {
			int cnt = countBlindSpot(currentMap);
			result = Math.min(result, cnt);
			
			return;
		}

		Coord cctv = cctvs.get(cctvIndex);
		int cctvType = cctv.n;

		// 해당 CCTV 타입의 모든 가능한 방향 조합 시도
		for (int[] dirs : directions[cctvType]) {
			int[][] newMap = copyMap(currentMap);

			// 현재 방향 조합으로 감시 영역 표시
			for (int dir : dirs)
				watch(newMap, cctv.r, cctv.c, dir);

			// 다음 CCTV로 재귀 호출
			recursion(cctvIndex + 1, newMap);
		}
	}

	static void watch(int[][] watchMap, int r, int c, int d) {
		int nr = r + dr[d];
		int nc = c + dc[d];

		while (isValidCoord(nr, nc)) {
			if (watchMap[nr][nc] == 6)
				break; // 벽 만나면 중단

			if (watchMap[nr][nc] == 0) {
				watchMap[nr][nc] = -1; // 감시됨 표시
			}

			nr += dr[d];
			nc += dc[d];
		}
	}

	static int countBlindSpot(int[][] countMap) {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (countMap[r][c] == 0)
					count++;
			}
		}
		return count;
	}

	static int[][] copyMap(int[][] original) {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++)
			copy[i] = original[i].clone();

		return copy;
	}

	static boolean isValidCoord(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}

	static class Coord {
		int n, r, c;

		Coord(int n, int r, int c) {
			this.n = n;
			this.r = r;
			this.c = c;
		}
	}
}
