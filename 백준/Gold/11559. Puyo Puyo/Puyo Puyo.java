import java.io.*;
import java.util.*;

public class Main {

	static int result;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		for (int r = 0; r < 12; r++) {
			String st = br.readLine();
			for (int c = 0; c < 6; c++) {
				map[r][c] = st.charAt(c);
			}
		}
		/////////// end of Input

		game();

		System.out.println(result);
	}

	static void game() {
		while (true) {
			boolean chkPuyo = false; // 4개 이상 연결된 뿌요가 있는 경우 체크
			boolean[][] isVisited = new boolean[12][6]; // 방문 체크 배열

			// 모든 그룹을 찾아 한번에 제거
			for (int r = 0; r < 12; r++) {
				for (int c = 0; c < 6; c++) {
					// 빈 공간이거나 이미 방문한 경우
					if (map[r][c] == '.' || isVisited[r][c])
						continue; // 넘기기

					// (r, c)에서 같은 색 뿌요 찾기
					List<Coord> samePuyo = bfs(r, c, isVisited);

					// 같은 색 뿌요들이 4개 이상인 경우
					if (samePuyo.size() >= 4) {
						chkPuyo = true;
						// 같은 뿌요들 빈 공간으로 변경하기
						for (Coord coord : samePuyo)
							map[coord.r][coord.c] = '.';
					}
				}
			}

			// 4개 이상 연결된 뿌요가 없는 경우
			if (!chkPuyo)
				break; // 반복 정지

			// 중력의 영향을 받아 아래로 떨어뜨리기
			down();

			// 연쇄 카운트 증가
			result++;
		}
	}

	static void down() {
		for (int c = 0; c < 6; c++) {
			for (int r = 10; r >= 0; r--) {
				// (r, c)가 빈 공간인 경우
				if (map[r][c] == '.')
					continue; // 넘기기

				char ch = map[r][c];
				map[r][c] = '.';

				// 맨 밑까지 내리기
				int nr = r;
				while (nr + 1 < 12 && map[nr + 1][c] == '.')
					nr++;

				map[nr][c] = ch;
			}
		}
	}

	static List<Coord> bfs(int sr, int sc, boolean[][] isVisited) {
		Queue<Coord> dq = new ArrayDeque<>();
		List<Coord> samePuyo = new ArrayList<>();

		char color = map[sr][sc]; // 현재 뿌요 색상
		isVisited[sr][sc] = true; // 방문 체크
		dq.offer(new Coord(sr, sc));
		samePuyo.add(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 유효범위 밖 || 이미 방문 || 같은 컬러가 아닌 경우
				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] != color)
					continue;

				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
				samePuyo.add(new Coord(nr, nc));
			}
		}

		return samePuyo;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < 12 && -1 < c && c < 6;
	}

	static class Coord {
		int r, c; // 좌표

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
