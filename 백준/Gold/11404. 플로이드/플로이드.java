import java.io.*;
import java.util.*;

public class Main {

	static final int INF = 10000001;
	static int n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine()); // 도시 개수
		m = Integer.parseInt(br.readLine()); // 버스 개수

		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			Arrays.fill(map[i], INF);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작 도시
			int b = Integer.parseInt(st.nextToken()); // 도착 도시
			int c = Integer.parseInt(st.nextToken()); // 필요 비용

			// 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
			if (map[a][b] > c)
				map[a][b] = c;
		}

		floydWarshall();

		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				if (map[r][c] >= INF || r == c)
					map[r][c] = 0;

				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void floydWarshall() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++)
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
			}
		}
	}
}
