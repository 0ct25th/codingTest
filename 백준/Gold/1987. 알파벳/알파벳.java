import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C; // 보드 가로, 세로
	static char[][] map; // 보드
	static boolean[] visited; // 알파벳 방문 여부 체크
	static int result; // 최대로 갈 수 있는 칸 수

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20)
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		visited = new boolean[26]; // 알파벳 방문 여부를 저장할 배열

		// DFS 탐색
		dfs(0, 0, 1);
		System.out.println(result);
	}

	static void dfs(int r, int c, int cnt) {
		visited[map[r][c] - 'A'] = true; // 현재 알파벳 방문 표시

		// 4방향 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			// 유효범위 내 && 알파벳 미사용
			if (isValidCoord(nr, nc) && !visited[map[nr][nc] - 'A']) {
				dfs(nr, nc, cnt + 1); // 다음 위치로 이동
			}
		}

		visited[map[r][c] - 'A'] = false; // 현재 알파벳 방문 표시 제거

		result = Math.max(result, cnt); // 최대 칸 수 업데이트
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < R && -1 < c && c < C;
	}
}
