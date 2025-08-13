import java.io.*;
import java.util.*;

public class Main {

	static final int INF = 1_000_000_000;

	static int n, m;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine()); // 도시의 개수
		m = Integer.parseInt(br.readLine()); // 버스의 개수

		map = new int[n + 1][n + 1];

		// 초기화: 자기 자신 0, 나머지 INF
		for (int i = 1; i <= n; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[a][b] = Math.min(map[a][b], c);
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (map[k][j] == INF || map[i][j] <= map[i][k] + map[k][j])
						continue;

					map[i][j] = map[i][k] + map[k][j];
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				sb.append(map[i][j] == INF ? 0 : map[i][j]).append(' ');

			sb.append('\n');
		}

		System.out.print(sb);
	}
}
