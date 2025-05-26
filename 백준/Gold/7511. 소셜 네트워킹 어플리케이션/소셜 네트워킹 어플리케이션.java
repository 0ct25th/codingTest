import java.io.*;
import java.util.*;

public class Main {

	static int n, k, m;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("Scenario ").append(t).append(":\n");

			n = Integer.parseInt(br.readLine()); // 유저의 수
			k = Integer.parseInt(br.readLine()); // 친구 관계의 수

			init();

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(a, b);
			}

			m = Integer.parseInt(br.readLine()); // 미리 구할 쌍의 수
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				int uRoot = find(u);
				int vRoot = find(v);

				if (uRoot == vRoot)
					sb.append("1\n");
				else
					sb.append("0\n");
			}

			sb.append("\n");
		} // end of TestCase

		System.out.println(sb);
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return;

		p[bRoot] = aRoot;
	}

	static int find(int x) {
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[n];

		for (int i = 0; i < n; i++)
			p[i] = i;
	}
}
