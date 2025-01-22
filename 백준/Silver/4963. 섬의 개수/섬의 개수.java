import java.io.*;
import java.util.*;

public class Main {

	static int w, h, result;
	static boolean[][] map, isVisited;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			map = new boolean[h][w];
			for (int r = 0; r < h; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < w; c++)
					if (Integer.parseInt(st.nextToken()) == 1)
						map[r][c] = true;
					else
						map[r][c] = false;

			}
			///////////////////////////// end of Input

			result = 0;
			isVisited = new boolean[h][w];
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					if (isVisited[r][c] || !map[r][c])
						continue;

					dfs(r, c);
					result++;

				}
			}

			System.out.println(result);
		} // end of TestCase
	}

	static void dfs(int r, int c) {
		isVisited[r][c] = true;

		// 8방향 탐색
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 유효범위 벗어남 || 이미 방문한 경우 || 바다인 경우
			if (!isValidCoord(nr, nc) || isVisited[nr][nc] || !map[nr][nc])
				continue;

			isVisited[nr][nc] = true;
			dfs(nr, nc);
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < h && -1 < c && c < w;
	}
}
