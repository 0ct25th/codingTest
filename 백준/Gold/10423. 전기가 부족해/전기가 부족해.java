import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, result;
	static boolean[] isPower;
	static int[] p;
	static List<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 수
		M = Integer.parseInt(st.nextToken()); // 케이블 수
		K = Integer.parseInt(st.nextToken()); // 발전소 수

		// 전처리
		make();

		// 발전소 위치 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			p[Integer.parseInt(st.nextToken())] = -1;
		}

		edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(u, v, w));
		}

		kruskal();

		System.out.println(result);
	}

	static void kruskal() {
		Collections.sort(edgeList);

		for (Edge e : edgeList) {
			if (union(e.from, e.to))
				continue;

			result += e.weight;

			// 모든 도시 발전소 연결된 경우
			if (isAllConnect())
				return;
		}
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return true;
		else if (aRoot == -1)
			p[bRoot] = -1;
		else if (bRoot == -1)
			p[aRoot] = -1;
		else
			p[bRoot] = aRoot;

		return false;
	}

	static int find(int x) {
		if (p[x] == -1)
			return -1;
		else if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static boolean isAllConnect() {
		for (int i = 1; i <= N; i++)
			if (p[i] != -1)
				return false;

		return true;
	}

	static void make() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight; // 비용

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
