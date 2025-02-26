import java.io.*;
import java.util.*;

public class Main {

	static int N, p[], result;
	static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		edgeList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			edgeList.add(new Edge(0, i, Integer.parseInt(br.readLine())));
		}

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int P = Integer.parseInt(st.nextToken());

				if (P == 0 || r >= c)
					continue;

				edgeList.add(new Edge(r, c, P));
			}
		}

		kruskal();

		System.out.println(result);
	}

	static void kruskal() {
		// 전처리
		make();

		// 비용 오름차순 정렬
		Collections.sort(edgeList, (o1, o2) -> (o1.weight - o2.weight));

		int cnt = 0;
		for (Edge e : edgeList) {
			if (union(e.from, e.to))
				continue;

			result += e.weight;
			if (++cnt == N)
				return;
		}
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return true;

		p[bRoot] = aRoot;
		return false;
	}

	static int find(int x) {
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static void make() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static class Edge {
		int from, to, weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
}
