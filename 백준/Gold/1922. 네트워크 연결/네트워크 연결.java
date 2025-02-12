import java.io.*;
import java.util.*;

public class Main {

	static int N, M, cnt;
	static long result;
	static List<Edge> edgeList;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수 = 정점의 수
		M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수 = 간선의 수

		edgeList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == b)
				continue;

			edgeList.add(new Edge(a, b, c));
		}

		// 크루스칼 알고리즘 수행
		kruskal();

		System.out.println(result);
	}

	static void kruskal() {
		// 간선 비용 오름차순 정렬
		Collections.sort(edgeList);

		// 전처리
		make();

		// 정렬된 간선을 하나씩 꺼내 신장 트리 생성
		for (Edge edge : edgeList) {
			if (!union(edge.from, edge.to))
				continue; // 넘기기

			// 간선 비용 더하기
			result += edge.weight;

			// 모든 정점 선택한 경우
			if (++cnt == N - 1)
				break;
		}
	}

	static boolean union(int a, int b) {
		// 각 요소의 루트를 찾음
		int aRoot = find(a);
		int bRoot = find(b);

		// 두 요소가 같은 트리에 있는 경우
		if (aRoot == bRoot)
			return false;

		// 두 요소 합치기
		parent[bRoot] = aRoot;
		return true;
	}

	static int find(int x) {
		if (x == parent[x])
			return x;

		return parent[x] = find(parent[x]);
	}

	static void make() {
		parent = new int[N + 1]; // 부모 또는 루트를 담은 배열

		for (int i = 1; i <= N; i++)
			parent[i] = i; // 자기 자신을 루트로 설정
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

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
