import java.io.*;
import java.util.*;

public class Main {

	static int N, M, sr, sc;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c);

				// 민식이의 현재 위치
				if (map[r][c] == '0') {
					sr = r;
					sc = c;

					map[r][c] = '.'; // 빈칸으로 변경
				}
			}
		}

		System.out.println(bfs(sr, sc));
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		int[][][] isVisited = new int[N][M][64]; // 방문 체크 배열, 열쇠 6개 => 2^6

		isVisited[sr][sc][0] = 1; // 시작점 방문 체크
		dq.offer(new Coord(sr, sc, 0)); // 시작점 덱 삽입

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int key = cur.key;

			if (map[r][c] == '1') // 출구에 도착한 경우
				return isVisited[r][c][key] - 1;

			for (int d = 0; d < 4; d++) { // 4방향 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 유효범위 외 || 이미 방문 || 벽
				if (!isValidCoord(nr, nc) || isVisited[nr][nc][key] != 0 || map[nr][nc] == '#')
					continue; // 넘기기

				if (map[nr][nc] == '.' || map[nr][nc] == '1') { // 빈칸과 문인 경우
					isVisited[nr][nc][key] = isVisited[r][c][key] + 1;
					dq.offer(new Coord(nr, nc, key));
				} else if (isKey(nr, nc)) { // 열쇠인 경우
					int nKey = key | (1 << map[nr][nc] - 'a'); // 열쇠 획득

					isVisited[nr][nc][nKey] = isVisited[r][c][key] + 1;
					dq.offer(new Coord(nr, nc, nKey));
				} else if (isDoor(nr, nc)) { // 문인 경우
					if ((key & (1 << (map[nr][nc] - 'A'))) == 0) // 열쇠가 없는 경우
						continue;

					isVisited[nr][nc][key] = isVisited[r][c][key] + 1;
					dq.offer(new Coord(nr, nc, key));
				}
			}
		}

		return -1; // 탈출 못한 경우
	}

	static boolean isDoor(int r, int c) {
		return 'A' <= map[r][c] && map[r][c] <= 'Z';
	}

	static boolean isKey(int r, int c) {
		return 'a' <= map[r][c] && map[r][c] <= 'z';
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r, c; // 좌표
		int key; // 지참 열쇠

		Coord(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}
}
