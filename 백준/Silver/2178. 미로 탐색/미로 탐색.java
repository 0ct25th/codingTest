import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static boolean[][] map, isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				if (str.charAt(c) == '1')
					map[r][c] = true; // 이동할 수 있는 칸
				else
					map[r][c] = false; // 이동할 수 없는 칸
			}
		}
		////////////////////// end of input

		System.out.println(bfs(0, 0) + 1);
	}

	static int bfs(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		isVisited = new boolean[N][M];

		// 시작점: 0, 0
		dq.offer(new Coord(sr, sc, 0));
		isVisited[sr][sc] = true;

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int cell = cur.cell;

			// 도착점(N-1, M-1)에 도착한 경우
			if (r == N - 1 && c == M - 1)
				return cell;

			// 4방향 탐색: 서로 인접한 칸으로만 이동 가능
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 유효범위를 벗어난 경우 || 이미 방문한 경우 || 이동할 수 없는 칸인 경우
				if(!isValidCoord(nr, nc) || isVisited[nr][nc] || !map[nr][nc])
					continue; // 넘김
				
				isVisited[nr][nc] = true; // 방문 체크
				dq.offer(new Coord(nr, nc, cell + 1)); // 덱 삽입
			}
		}

		return -1; // 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r;
		int c;
		int cell;

		Coord(int r, int c, int cell) {
			this.r = r;
			this.c = c;
			this.cell = cell;
		}
	}
}
