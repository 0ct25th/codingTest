import java.io.*;
import java.util.*;

public class Main {

	static int N, M, worst, best;
	static int[] p;
	static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력의 첫 번째 줄에는 건물의 개수 N(1 ≤ N ≤ 1,000)과 도로의 개수 M(1 ≤ M ≤ N(N-1)/2) 이 주어진다.
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수

		// 입력의 두 번째 줄부터 M+1개의 줄에는 A, B(1 ≤ A, B ≤ N), C 가 주어진다.
		// 이는 A와 B 건물에 연결된 도로가 있다는 뜻이며, C는 0(오르막길) 또는 1(내리막길)의 값을 가진다.
		edgeList = new ArrayList<>();

		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(A, B, C));
		}

		// 오르막순 정렬
		Collections.sort(edgeList, (o1, o2) -> (o1.sort - o2.sort));
		worst = kruskal();

		// 내리막 순 정렬
		Collections.sort(edgeList, (o1, o2) -> -(o1.sort - o2.sort));
		best = kruskal();

		System.out.println(worst - best);
	}

	static int kruskal() {
		int asc = 0; // 오르막 수

		// 전처리
		make();

		// union-find
		int cnt = 0;
		for (Edge e : edgeList) {
			if (union(e.from, e.to))
				continue;

			if (e.sort == 0)
				asc++;

			if (++cnt == N + 1)
				break;
		}

		return asc * asc;
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

		for (int i = 0; i <= N; i++)
			p[i] = i;
	}

	static class Edge {
		int from, to;
		int sort;

		Edge(int from, int to, int sort) {
			this.from = from;
			this.to = to;
			this.sort = sort;
		}
	}
}
