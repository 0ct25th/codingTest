import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Edge> edgeList;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 논의 수

		edgeList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			int w = Integer.parseInt(br.readLine());

			edgeList.add(new Edge(0, i, w));
		}

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

		int result = 0;
		for (Edge e : edgeList) {
			if (union(e.i, e.j))
				result += e.w;
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
		int i, j; // 논의 번호
		int w; // 연결 비용

		Edge(int i, int j, int w) {
			this.i = i;
			this.j = j;
			this.w = w;
		}
	}
}
