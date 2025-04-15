import java.io.*;
import java.util.*;

public class Main {
	static int h, w, result;
	static char[][] map;
	static boolean[][] isVisited;
	static Set<Character> keys;
	static Queue<Coord> dq;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			// 맵 외곽에 패딩 추가
			map = new char[h + 2][w + 2];
			for (int i = 0; i < h + 2; i++)
				Arrays.fill(map[i], '.');

			for (int r = 1; r <= h; r++) {
				String str = br.readLine();
				for (int c = 1; c <= w; c++)
					map[r][c] = str.charAt(c - 1);
			}

			keys = new HashSet<>();
			String str = br.readLine();
			if (!str.equals("0")) {
				for (char key : str.toCharArray())
					keys.add(key);
			}

			result = 0;
			int prevKeyCount = -1;
			// 열쇠 개수가 늘어날 때마다 BFS를 반복
			while (prevKeyCount != keys.size()) {
				prevKeyCount = keys.size();
				bfs();
			}
			System.out.println(result);
		}
	}

	static void bfs() {
		dq = new ArrayDeque<>();
		isVisited = new boolean[h + 2][w + 2];

		// 외곽에서 시작
		for (int r = 0; r < h + 2; r++) {
			for (int c = 0; c < w + 2; c++) {
				if (isValidSide(r, c)) {
					dq.offer(new Coord(r, c));
					isVisited[r][c] = true;
				}
			}
		}

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] == '*')
					continue;

				isVisited[nr][nc] = true;

				// 빈 공간
				if (map[nr][nc] == '.') {
					dq.offer(new Coord(nr, nc));
				}
				// 문서
				else if (map[nr][nc] == '$') {
					map[nr][nc] = '.';
					result++;
					dq.offer(new Coord(nr, nc));
				}
				// 열쇠
				else if ('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
					char key = map[nr][nc];
					map[nr][nc] = '.';
					if (!keys.contains(key)) {
						keys.add(key);
					}
					dq.offer(new Coord(nr, nc));
				}
				// 문
				else if ('A' <= map[nr][nc] && map[nr][nc] <= 'Z') {
					char door = map[nr][nc];
					char key = Character.toLowerCase(door);
					if (keys.contains(key)) {
						map[nr][nc] = '.';
						dq.offer(new Coord(nr, nc));
					}
				}
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r < h + 1 && 0 < c && c < w + 1;
	}

	static boolean isValidSide(int r, int c) {
		return r == 0 || r == h + 1 || c == 0 || c == w + 1;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
