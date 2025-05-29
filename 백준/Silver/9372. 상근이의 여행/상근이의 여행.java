import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Edge> edgeList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 국가의 수
			M = Integer.parseInt(st.nextToken()); // 비행기의 종류

			edgeList = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				edgeList.add(new Edge(a, b));
			}

			init();
			int result = 0;
			for (Edge e : edgeList) {
				if (union(e.a, e.b))
					result++;
			}

			sb.append(result).append("\n");
		} // end of TestCase

		System.out.println(sb);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		p[bRoot] = aRoot;
		return true;
	}

	static int find(int x) {
		if (x == p[x])
			return p[x];

		return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static class Edge {
		int a, b; // 국가

		Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
