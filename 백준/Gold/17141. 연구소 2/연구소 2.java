import java.io.*;
import java.util.*;

public class Main {

	static int N, M, cnt, result;
	static int[][] map;
	static int[] virous;
	static List<Coord> virousSpace;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		virousSpace = new ArrayList<>(); // 바이러스 놓을 수 있는 공간을 저장할 리스트
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				// 빈칸 개수 카운트
				if (map[r][c] == 0)
					cnt++;
				// 바이러스 가능 위치
				else if (map[r][c] == 2) {
					cnt++;
					virousSpace.add(new Coord(r, c));
				}
			}
		}

		cnt -= M; // 빈칸 개수(바이러스 있는 공간도 놓을 공간을 제외하고 빈칸임)
		result = Integer.MAX_VALUE; // 연구소의 모든 빈 칸에 바이러스가 있게 되는 최소 시간
		virous = new int[M]; // 바이러스 공간 인덱스를 저장할 배열
		dfs(0, 0);

		// 모든 빈 칸에 바이러스 퍼뜨릴 수 없는 경우 -1 출력
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	static void dfs(int depth, int start) {
		// 기저조건: 바이러스 M개를 모두 놓은 경우
		if (depth == M) {
			// 바이러스를 놓았을 때 최소 시간
			result = Math.min(result, bfs());

			return;
		}

		// 바이러스 놓을 공간 선택
		for (int i = start; i < virousSpace.size(); i++) {
			virous[depth] = i;
			dfs(depth + 1, i + 1);
		}
	}

	static int bfs() {
		int tmpCnt = cnt; // 현재 바이러스 놓았을 때 빈칸 갯수
		int time = 0; // 바이러스 퍼진 시간초
		Queue<Coord> dq = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][N];

		// 바이러스 놓은 위치를 덱에 삽입
		for (int i = 0; i < M; i++) {
			int r = virousSpace.get(virous[i]).r;
			int c = virousSpace.get(virous[i]).c;

			isVisited[r][c] = true;
			dq.offer(new Coord(r, c, 0));
		}

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			// 4방향 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 유효범위 밖 || 이미 방문 || 벽인 경우
				if (!isValidCoord(nr, nc) || isVisited[nr][nc] || map[nr][nc] == 1)
					continue;

				tmpCnt--; // 빈칸 개수 감소
				time = Math.max(time, t + 1); // 시간 초 갱신
				isVisited[nr][nc] = true; // 방문 체크
				dq.offer(new Coord(nr, nc, t + 1)); // 덱 삽입
			}
		}

		// 바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우
		return tmpCnt > 0 ? Integer.MAX_VALUE : time;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < N;
	}

	static class Coord {
		int r, c;
		int t; // 시간

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}

		Coord(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
