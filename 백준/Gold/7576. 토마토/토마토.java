import java.io.*;
import java.util.*;

public class Main {

	static int N, M, cnt, result;
	static int[][] map;
	static boolean[][] isVisited;
	static Queue<Coord> dq = new ArrayDeque<>();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		isVisited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 1) { // 익은 토마토
					isVisited[r][c] = true;
					dq.offer(new Coord(r, c));
				} else if(map[r][c] == 0) // 안익은 토마토
					cnt++; 

			}
		}

		while (cnt > 0 && !dq.isEmpty()) {
			bfs();
			result++; // 날짜 증가
		}

		if (cnt != 0)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	static void bfs() {
		int size = dq.size();

		for (int i = 0; i < size; i++) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] != 0)
					continue;

				cnt--; // 안익은 토마토 개수 감소
				map[nr][nc] = 1; // 익은 토마토로 변경
				isVisited[nr][nc] = true; // 방문 체크
				dq.offer(new Coord(nr, nc)); // 덱 삽입
			}
		}
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
