import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, sr, sc, er, ec;
	static char[][] map;
	static int[][] water; // 물 퍼지는 시간 저장
	static Queue<int[]> dq;
	static boolean[][] isVisited; // 방문 체크 배열
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다.
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 다음 R개 줄에는 티떱숲의 지도가 주어지며, 문제에서 설명한 문자만 주어진다. 'D'와 'S'는 하나씩만 주어진다.
		map = new char[R][];
		dq = new ArrayDeque<int[]>();
		isVisited = new boolean[R][C];
		water = new int[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				water[i][j] = 987654321;
				// 물
				if (map[i][j] == '*') {
					isVisited[i][j] = true;
					water[i][j] = 0;
					dq.offer(new int[] { i, j, 0 });
				} else if (map[i][j] == 'D') { // 도착지
					er = i;
					ec = j;
					water[i][j] = 987654321;	// 물도 비버의 소굴로 이동할 수 없다.
				} else if (map[i][j] == 'S') { // 출발지
					sr = i;
					sc = j;
				}

			}
		}

		// 물이 퍼지는 시간 체크
		flood();

		// 첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 
		// 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.
		System.out.println(bfs(sr, sc));
	}

	// 유효범위 체크 함수
	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < R && -1 < c && c < C;
	}

	// 물이 퍼지는 시간 체크하는 함수
	static void flood() {
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				// 유효범위 && 미방문 && 물이 아닌 경우  && 돌이 아닌 경우 && 비버 굴이 아닌 경우
				if (isValidCoord(nr, nc) && !isVisited[nr][nc] && map[nr][nc] != '*' && map[nr][nc] != 'X' && map[nr][nc] != 'D') {
					isVisited[nr][nc] = true;
					water[nr][nc] = t + 1;
					dq.offer(new int[] { nr, nc, t + 1 });
				}
			}
		}
	}

	// 고슴도치 이동 함수
	static String bfs(int sr, int sc) {
		isVisited = new boolean[R][C];

		isVisited[sr][sc] = true;
		dq.offer(new int[] { sr, sc, 0 });

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];

			// 목적지에 도착한 경우
			if (r == er && c == ec)
				return t + "";

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				// 유효범위 && 미방문 && 물X && 돌 X && 현재 시간 + 1 < 물 퍼진 시간(다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다.)
				if (isValidCoord(nr, nc) && !isVisited[nr][nc] && map[nr][nc] != '*' && map[nr][nc] != 'X' && t + 1 < water[nr][nc]) {
					isVisited[nr][nc] = true;
					dq.offer(new int[] { nr, nc, t + 1 });
				}
			}
		}

		return "KAKTUS";
	}
}
