import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, result;
	static boolean[][] trash, isVisited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		trash = new boolean[N + 1][M + 1];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			trash[r][c] = true; // 쓰레기
		}
		///////////////////////////// end of Input

		result = 0;
		isVisited = new boolean[N + 1][M + 1];
		for (int r = 1; r < N + 1; r++)
			for (int c = 1; c < M + 1; c++)
				// 쓰레기 && 방문하지 않은 쓰레기
				if (trash[r][c] && !isVisited[r][c])
					result = Math.max(result, bfs(r, c));

		System.out.println(result);
	}

	static int bfs(int sr, int sc) {
		int count = 1;
		Queue<Coord> dq = new ArrayDeque<>();
		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc));

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			// 4방향 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 유효범위 벗어남 || 이미 방문 || 쓰레기 아닌 경우
				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || !trash[nr][nc])
					continue; // 넘김

				isVisited[nr][nc] = true;
				count++; // 개수 카운트
				dq.offer(new Coord(nr, nc));
			}
		}

		return count;
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r < N + 1 && 0 < c && c < M + 1;
	}

	static class Coord {
		int r;
		int c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
