import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static char map[][];
	static int sr, sc, er, ec;
	static boolean[][][] isVisited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 첫째 줄에 미로의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 50)
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 둘째 줄부터 N개의 줄에 미로의 모양이 주어진다.
		// 같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 마찬가지이다.
		// 그리고, 문에 대응하는 열쇠가 없을 수도 있다. '0'은 한 개, '1'은 적어도 한 개 있다. 열쇠는 여러 번 사용할 수 있다.
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();

			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') { // 민식이의 현재 위치
					sr = i;
					sc = j;
				}
			}
		}
		////////////////////// end of Input

		isVisited = new boolean[N][M][64];
		System.out.println(bfs(sr, sc));
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < M;
	}

	static int bfs(int sr, int sc) {
		Queue<int[]> dq = new ArrayDeque<>();

		// 시작점
		isVisited[sr][sc][0] = true;
		dq.offer(new int[] { sr, sc, 0, 0 });

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			int key = cur[2];
			int time = cur[3];

			// 출구에 도착한 경우
			if (map[r][c] == '1')
				return time;

			// 4방향 탐색 실행
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				// 유효범위 밖 || 방문 || 벽
				if (!isValidCoord(nr, nc) || isVisited[nr][nc][key] || map[nr][nc] == '#')
					continue;
                
                // 빈 칸인 경우
                if(map[nr][nc] == '.' || map[nr][nc] == '0' || map[nr][nc] == '1') {
					isVisited[nr][nc][key] = true;
					dq.offer(new int[] { nr, nc, key, time + 1 });
				}
                
				// 열쇠를 발견한 경우
				else if ('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
					int nKey = key | (1 << (map[nr][nc] - 'a')); // 새로운 열쇠를 획득
					isVisited[nr][nc][nKey] = true;
					dq.offer(new int[] { nr, nc, nKey, time + 1 });
				}

				// 문을 발견한 경우
				else if ('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
					if ((key & (1 << (map[nr][nc] - 'A'))) != 0) { // 열쇠가 있으면
						isVisited[nr][nc][key] = true;
						dq.offer(new int[] { nr, nc, key, time + 1 });
					}
				}
			}
		}
		return -1; // 민식이가 미로를 탈출 할 수 없는 경우
	}
}
