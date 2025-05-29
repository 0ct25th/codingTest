import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Edge> edgeList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		edgeList = new ArrayList<>();
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int w = Integer.parseInt(st.nextToken());

				if (r <= c)
					continue;

				edgeList.add(new Edge(r, c, w));
			}
		}

		Collections.sort(edgeList, (o1, o2) -> (o1.w - o2.w));
		init();

		long result = 0;
		int cnt = 0;
		for (Edge e : edgeList) {
			if (cnt == N)
				break;

			if (union(e.a, e.b)) {
				cnt++; // 연결된 행성 수
				result += e.w; // 관리 비용
			}
		}

		System.out.println(result);
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
		int a, b; // 행성
		int w; // 비용

		Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
	}
}
