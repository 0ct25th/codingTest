import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static boolean[][] map, isVisited;
	static List<Coord>[][] graph;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫 번째 줄에는 N(2 ≤ N ≤ 100)과, M(1 ≤ M ≤ 20,000)이 정수로 주어진다.
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 각 방에서 켤 수 있는 다른 방들의 정보를 저장
		graph = new ArrayList[N + 1][N + 1];
		for (int r = 1; r <= N; r++)
			for (int c = 1; c <= N; c++)
				graph[r][c] = new ArrayList<>();

		// 다음 M줄에는 네 개의 정수 x, y, a, b가 주어진다.
		// (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[x][y].add(new Coord(a, b));
		}

		// // 각 방에서 켤 수 있는 다른 방들의 정보를 저장
		map = new boolean[N + 1][N + 1];
		map[1][1] = true; // 시작점(1,1)의 불은 켜져있음
		result = bfs() + 1; // BFS 실행 (시작점은 이미 켜져있으므로 +1)

		System.out.println(result);
	}

	static int bfs() {
		int cnt = 0; // 새로 켠 불의 개수
		boolean isSwitchOn = false; // 이번 BFS에서 새로운 불을 켰는지 여부
		Queue<Coord> dq = new ArrayDeque<>();
		isVisited = new boolean[N + 1][N + 1]; // 방문 배열 초기화

		dq.offer(new Coord(1, 1)); // 시작점 큐에 삽입

		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			// 4방향 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 유효범위 밖 || 불이 꺼진 곳 || 이미 방문한 경우
				if (!isValidCoord(nr, nc) || !map[nr][nc] || isVisited[nr][nc])
					continue; // 넘기기

				isVisited[nr][nc] = true; // 방문 체크
				dq.offer(new Coord(nr, nc)); // 덱에 새로운 위치 삽입
			}

			// 현재 위치에서 켤 수 있는 모든 방의 불을 켬
			for (Coord now : graph[r][c]) {
				// 이미 불이 켜진 방인 경우
				if (map[now.r][now.c])
					continue; // 넘기기

				cnt++; // 새로 켠 방 개수 증가
				isSwitchOn = true; // 새로운 불을 켰다고 불 킨 여부 변경
				map[now.r][now.c] = true; // 불키기
			}
		} // end of while

		// 이번 BFS에서 새로운 불을 켰다면 새로 켜진 불로 인해 갈 수 있는 방이 생길 수 있으므로
		// BFS를 다시 실행하고 결과를 누적
		if (isSwitchOn)
			return cnt + bfs();

		return cnt; // 더 이상 켤 수 있는 불이 없으면 종료
	}

	static boolean isValidCoord(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= N;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
