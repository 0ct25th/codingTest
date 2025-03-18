import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] map;
	static final int INF = 100000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 첫째 줄에 도시의 개수 n이 주어지고
		n = Integer.parseInt(br.readLine());

		// 둘째 줄에는 버스의 개수 m이 주어진다.
		m = Integer.parseInt(br.readLine());

		// 초기화
		map = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(map[i], INF);

		// 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
			if (map[a - 1][b - 1] > c)
				map[a - 1][b - 1] = c;
		}

		///////////////// end of Input

		// 플로이드워셜 실행
		floyd();

		// n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다.
		// 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] >= INF)
					map[i][j] = 0;
				
				if (i == j)
					map[i][i] = 0;

				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}

		// 결과 출력
		bw.write(sb.toString());

		// close
		br.close();
		bw.close();
	}

	static void floyd() {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
	}
}
